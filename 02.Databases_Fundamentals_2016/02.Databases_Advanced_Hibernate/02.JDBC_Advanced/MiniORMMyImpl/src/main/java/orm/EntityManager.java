package orm;

import orm.interfaces.DbContext;
import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntityManager implements DbContext {

    private Connection connection;

    private Set<Object> persistedEntities;

    public EntityManager(Connection connection) {
        this.connection = connection;
        this.persistedEntities = new HashSet<>();
    }

    public <E> boolean persist(E entity) throws SQLException, IllegalAccessException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);

        this.doCreate(entity, primary);

        Object value = primary.get(entity);

//        if (value == null || (long) value <= 0) {
//            return false;
//        }
        if (!this.doUpdate(entity, primary)) {
            return this.doInsert(entity);
        } else {
            return true;
        }
    }

    private <E> boolean doCreate(E entity, Field primary) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + "(";

        Field[] entityFields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            String fieldName = this.getFieldName(field);
            String dbType = this.getDbType(field, primary);
            query += (fieldName + " ");
            query += dbType;

            if (i < entityFields.length - 1) {
                query += ",";
            }
        }
        query += ")";
        return this.connection.prepareStatement(query).execute();
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
        int resultAfterUpdate =  this.connection.prepareStatement(sqlUpdateQuery).executeUpdate();
        if (resultAfterUpdate > 0) {
            return true;
        }
        return false;
    }

    private <E> boolean doInsert(E entity) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlInsert = "INSERT INTO " + tableName;
        String columnsNames = " (";
        String values = "VALUES (";

        Field[] entityFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < entityFields.length; i++) {
            Field field = entityFields[i];
            field.setAccessible(true);

//            if (field.getName().equals(primary.getName())) {
//                continue;
//            }
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
        return this.connection.prepareStatement(sqlInsert).execute();
    }

    private String getDbType(Field field, Field primary) {
        field.setAccessible(true);
        if (field.getName().equals(primary.getName())) {
            //return "BIGINT PRIMARY KEY AUTO_INCREMENT";
            return "INT PRIMARY KEY";
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

    public <E> Iterable<E> find(Class<E> table) {
        return null;
    }

    public <E> Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    public <E> E findFirst(Class<E> table) {
        return null;
    }

    public <E> E findFirst(Class<E> table, String where) throws SQLException {
        String tableName = this.getTableName(table.getClass());
        String sqlSelectQuery = "SELECT * FROM " + tableName + "WHERE " +
                (where != null ? where : "1 = 1");
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelectQuery);

        //E entity = this.fillEntity(table, rs);
        return null;
    }

//    private <E> E fillEntity(Class<E> table, ResultSet rs) throws SQLException {
//        E entity = null;
//        Field[] entityFields = table.getDeclaredFields();
//        rs.next();
//
//        for (int i = 0; i < entityFields.length; i++) {
//            Field field = entityFields[i];
//            field.setAccessible(true);
//
//            Class fieldClass = field.getClass();
//
//            Integer test = (fieldClass)rs.getObject(1);
//        }
//    }

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
