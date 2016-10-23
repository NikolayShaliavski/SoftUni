package orm;

import orm.interfaces.DbContext;
import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class EntityManager implements DbContext, AutoCloseable {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private Statement statement;

    private ResultSet rs;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public <E> boolean persist(E entity) throws SQLException, IllegalAccessException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);

        this.doCreate(entity, primary);

        Object value = primary.get(entity);

        if (value == null || (int) value <= 0) {
            return this.doInsert(entity, primary);
        }
        return this.doUpdate(entity, primary);
    }

    private <E> boolean doCreate(E entity, Field primary) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlCreateQuery = "CREATE TABLE IF NOT EXISTS " + tableName + "(";

        Field[] entityFields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            String fieldName = this.getFieldName(field);
            String dbType = this.getDbType(field, primary);
            sqlCreateQuery += (fieldName + " ");
            sqlCreateQuery += dbType;

            if (i < entityFields.length - 1) {
                sqlCreateQuery += ",";
            }
        }
        sqlCreateQuery += ")";
        this.preparedStatement = this.connection.prepareStatement(sqlCreateQuery);

        return this.preparedStatement.execute();
    }

    private <E> boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlUpdateQuery = "UPDATE " + tableName + " SET ";
        String where = " WHERE ";

        Field[] entityFields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            field.setAccessible(true);

            if (field.getName().equals(primary.getName())) {
                String primaryColumnName = this.getFieldName(primary);
                int primaryColumnValue = (int) primary.get(entity);

                where += (primaryColumnName + " = " + primaryColumnValue);
            } else {
                Object value = field.get(entity);
                if (value instanceof Date) {
                    sqlUpdateQuery += (this.getFieldName(field) + " = "
                            + new SimpleDateFormat("yyyyMMdd").format(value));
                } else {
                    sqlUpdateQuery += (this.getFieldName(field) + " = "
                            + "'" + value + "'");
                }

                if (i < entityFields.length - 1) {
                    sqlUpdateQuery += ",";
                }
            }
        }
        sqlUpdateQuery += where;

        this.preparedStatement = this.connection.prepareStatement(sqlUpdateQuery);
        return this.preparedStatement.execute();
    }

    private <E> boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlInsert = "INSERT INTO " + tableName;
        String columnsNames = " (";
        String values = "VALUES (";

        Field[] entityFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            field.setAccessible(true);

            if (field.getName().equals(primary.getName())) {
                continue;
            }
            String columnName = this.getFieldName(field);
            columnsNames += columnName;
            Object value = field.get(entity);

            if (value instanceof Date) {
                values += new SimpleDateFormat("yyyyMMdd").format(value);
            } else {
                values += ("'" + value + "'");
            }

            if (i < entityFields.length - 1) {
                columnsNames += ", ";
                values += ", ";
            }
        }
        columnsNames += ")";
        values += ")";
        sqlInsert += (columnsNames + values);

        this.preparedStatement = this.connection.prepareStatement(sqlInsert);
        return this.preparedStatement.execute();
    }

    public <E> Iterable<E> find(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException {
        Iterable<E> entities;
        try {
            entities = this.find(table, null);
        } catch (SQLException sqlEx) {
            throw new SQLException("The table is empty.");
        }
        return entities;
    }

    public <E> Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException,
            InstantiationException {
        String tableName = this.getTableName(table);
        String sqlSelectQuery = "SELECT * FROM " + tableName + " WHERE " +
                (where != null ? where : "1 = 1");
        this.statement = this.connection.createStatement();
        this.rs = this.statement.executeQuery(sqlSelectQuery);

        E entity;
        List<E> entities = new ArrayList<>();

        while (this.rs.next()) {
            entity = this.fillEntity(table);
            entities.add(entity);
        }
        if (entities.size() == 0) {
            throw new SQLException("There aren't such entities passed to the given criteria.");
        }
        return Collections.unmodifiableCollection(entities);
    }

    public <E> E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException {
        E entity;
        try {
            entity = this.findFirst(table, null);
        } catch (SQLException sqlEx) {
            throw new SQLException("The table is empty.");
        }
        return entity;
    }

    public <E> E findFirst(Class<E> table, String where) throws SQLException, InstantiationException,
            IllegalAccessException {
        String tableName = this.getTableName(table);
        String sqlSelectQuery = "SELECT * FROM " + tableName + " WHERE " +
                (where != null ? where : "1 = 1") + " LIMIT 1";
        this.statement = this.connection.createStatement();
        this.rs = this.statement.executeQuery(sqlSelectQuery);

        if (!this.rs.isBeforeFirst()) {
            throw new SQLException("There is no such entity in the table.");
        }
        this.rs.next();
        E entity = this.fillEntity(table);
        return entity;
    }

    @Override
    public <E> int deleteById(Class<E> table, int id) throws SQLException {
        String tableName = this.getTableName(table);
        String sqlDeleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";

        this.preparedStatement = this.connection.prepareStatement(sqlDeleteQuery);
        this.preparedStatement.setInt(1, id);

        return this.preparedStatement.executeUpdate();
    }

    @Override
    public void close() throws Exception {
        if (this.rs != null) {
            this.rs.close();
        }
        if (this.statement != null) {
            this.statement.close();
        }
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }

    private <E> E fillEntity(Class<E> table) throws SQLException, IllegalAccessException,
            InstantiationException {
        E entity = table.newInstance();
        Field[] entityFields = table.getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            field.setAccessible(true);

            field.set(entity, this.rs.getObject(i + 1));
        }
        return entity;
    }

    private String getDbType(Field field, Field primary) {
        field.setAccessible(true);
        if (field.getName().equals(primary.getName())) {
            return "INT PRIMARY KEY AUTO_INCREMENT";
        }
        switch (field.getType().getSimpleName().toLowerCase()) {
            case "int":
                return "INT";
            case "long":
                return "LONG";
            case "string":
                return "VARCHAR(50)";
            case "date":
                return "DATE";
        }
        return null;
    }

    private <E> String getTableName(Class<E> entity) {
        String tableName = "";

        if (entity.isAnnotationPresent(Entity.class)) {
            Entity entityAnnotation = entity.getAnnotation(Entity.class);
            tableName = entityAnnotation.name();
        }
        if (tableName.equals("")) {
            tableName = entity.getSimpleName();
        }
        return tableName;
    }

    private String getFieldName(Field field) {
        String fieldName = "";

        if (field.isAnnotationPresent(Column.class)) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            fieldName = columnAnnotation.name();
        }
        if (fieldName.equals("")) {
            fieldName = field.getName();
        }
        return fieldName;
    }

    private Field getId(Class c) {
        return Arrays.stream(c.getDeclaredFields()).
                filter(f -> f.isAnnotationPresent(Id.class)).
                findFirst().
                orElseThrow(() -> new IllegalAccessError(""));
    }
}
