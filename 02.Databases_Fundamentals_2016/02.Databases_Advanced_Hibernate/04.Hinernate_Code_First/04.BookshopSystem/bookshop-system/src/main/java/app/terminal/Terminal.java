package app.terminal;

import app.domain.entities.Author;
import app.domain.entities.Book;
import app.domain.entities.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.services.AuthorService;
import app.services.BookService;
import app.services.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Terminal implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public Terminal(BookService bookService,
                    AuthorService authorService,
                    CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.seedData();
        //this.hibernateCodeFirstExerciseQueries();

        //this.exportToJson();

        //this.importFromJson();
        
        //this.exportToXml();
        
        this.importFromXml();
    }

    private void importFromXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File outputXMLFile = new File("src/main/resources/files/input/xml/author.xml");
        Author authorFromXML = (Author) unmarshaller.unmarshal(outputXMLFile);

        for (Book book : authorFromXML.getBooks()) {
            Book currBook = this.bookService.findByTitle(book.getTitle());
            book = currBook;
        }
        authorFromXML.getBooks().stream().forEach(b -> b.setAuthor(authorFromXML));
        this.authorService.save(authorFromXML);

    }

    private void exportToXml() throws JAXBException {
        Author author = this.authorService.findOne(6L);
        JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File outputXMLFile = new File("src/main/resources/files/output/xml/author.xml");
        jaxbMarshaller.marshal(author, outputXMLFile);

    }

    private void importFromJson() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().
                setPrettyPrinting().create();
        InputStream inputStream =
                this.getClass().getResourceAsStream("/files/input/json/vazov.json");
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Author vazov = gson.fromJson(jsonData.toString(), Author.class);
        vazov.getBooks().stream().forEach(b -> b.setAuthor(vazov));
        this.authorService.save(vazov);

    }

    private void exportToJson() {
        Author author = this.authorService.findOne(4L);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().
                setPrettyPrinting().create();
        String authorJson = gson.toJson(author);
        File outputFile = new File("src/main/resources/files/output/json/author.json");
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        ) {
            bufferedWriter.write(authorJson);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hibernateCodeFirstExerciseQueries() {
        //Queries
        Calendar calendar;
        Date date;
        //8.1
//        calendar = new GregorianCalendar(2000, 1, GregorianCalendar.JANUARY);
//        date = calendar.getTime();
//        List<Book> booksAfterDate = this.bookService.findBooksAfterDate(date);
//        for (Book book : booksAfterDate) {
//            System.out.println(book.getTitle());
//        }
        //8.2
//        calendar = new GregorianCalendar(1990, 1, GregorianCalendar.JANUARY);
//        date = calendar.getTime();
//        List<Author> authors = this.authorService.findByBooksAfter(date);
//        for (Author author : authors) {
//            System.out.printf("%s %s%n", author.getFirstName(), author.getLastName());
//        }
        //8.3
//        List<Object[]> objects = this.authorService.findAuthorsAndBooks();
//        for (Object[] obj : objects) {
//            System.out.printf("%s %s %d%n",
//                    obj[0],
//                    obj[1],
//                    (Long) obj[2]);
//        }
        //8.4
//        List<Book> booksFromPowell = this.bookService.findGeorgePowellBooks();
//        for (Book book : booksFromPowell) {
//            String title = book.getTitle();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            String releaseDate = sdf.format(book.getReleaseDate());
//            Integer copies = book.getCopies();
//            System.out.printf("%s %s %d%n",
//                    title, releaseDate, copies);
//        }
    }

    private void seedData() throws IOException, ParseException {
        this.seedAuthors();
        this.seedCategories();
        this.seedBooks();
        this.seedRelatedBooks();
    }

    private void seedRelatedBooks() {
        List<Book> books = bookService.findAll();
        List<Book> threeBooks = books.subList(0, 3);

        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(1));
        threeBooks.get(1).getRelatedBooks().add(threeBooks.get(0));
        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(2));
        threeBooks.get(2).getRelatedBooks().add(threeBooks.get(0));

        for (Book book : threeBooks) {
            bookService.save(book);
        }

        for (Book book : threeBooks) {
            System.out.printf("--%s\n", book.getTitle());
            for (Book relatedBook : book.getRelatedBooks()) {
                System.out.println(relatedBook.getTitle());
            }
        }

    }

    private void seedBooks() throws IOException, ParseException {
        Random random = new Random();
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = this.categoryService.findAll();

        BufferedReader booksReader =
                new BufferedReader(new FileReader("src/main/java/res/books.txt"));
        String line = booksReader.readLine();
        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            int categoryIndex = random.nextInt(categories.size());

            Author author = authors.get(authorIndex);
            Category category = categories.get(categoryIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setCategory(category);
            book.setEditonType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            this.bookService.save(book);
        }
    }

    private void seedCategories() throws IOException {
        BufferedReader categoryReader =
                new BufferedReader(new FileReader("src/main/java/res/categories.txt"));
        String categoryName = categoryReader.readLine();
        while ((categoryName = categoryReader.readLine()) != null) {

            Category category = new Category();
            category.setName(categoryName);
            this.categoryService.save(category);
        }
    }

    private void seedAuthors() throws IOException {
        BufferedReader authorsReader =
                new BufferedReader(new FileReader("src/main/java/res/authors.txt"));
        String line = authorsReader.readLine();
        while ((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            this.authorService.save(author);
        }
    }
}
