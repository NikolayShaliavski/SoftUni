package booknut.services;

import booknut.models.bindingModels.AddBookModel;
import booknut.models.viewModels.ViewBookModel;

import java.util.List;

public interface BookService {

    void saveBook(AddBookModel bookModel);

    List<ViewBookModel> getAllBooks();

    ViewBookModel findBookByTitle(String title);

    void deleteBookByTitle(String title);
}
