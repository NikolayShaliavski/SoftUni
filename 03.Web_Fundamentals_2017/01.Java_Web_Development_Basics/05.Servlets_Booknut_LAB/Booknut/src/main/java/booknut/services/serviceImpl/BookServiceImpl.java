package booknut.services.serviceImpl;

import booknut.entities.Book;
import booknut.models.bindingModels.AddBookModel;
import booknut.models.viewModels.ViewBookModel;
import booknut.repositories.BookRepository;
import booknut.repositories.repositoriesImpl.BookRepositoryImpl;
import booknut.services.BookService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private static final BookRepository bookRepository =
            BookRepositoryImpl.getInstance();
    public static final ModelMapper mapper = new ModelMapper();

    @Override
    public void saveBook(AddBookModel bookModel) {
        Book book = mapper.map(bookModel, Book.class);
        bookRepository.saveBook(book);
    }

    @Override
    public List<ViewBookModel> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        List<ViewBookModel> bookViews = new ArrayList<>();
        for (Book book : books) {
            ViewBookModel bookModel = mapper.map(book, ViewBookModel.class);
            bookViews.add(bookModel);
        }
        return bookViews;
    }

    @Override
    public ViewBookModel findBookByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title);
        ViewBookModel bookView = null;
        if (book != null) {
            bookView = mapper.map(book, ViewBookModel.class);
        }
        return bookView;
    }

    @Override
    public void deleteBookByTitle(String title) {
        bookRepository.deleteBookByTitle(title);
    }
}
