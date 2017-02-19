package booknut.controllers;

import booknut.models.bindingModels.AddBookModel;
import booknut.services.BookService;
import booknut.services.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/addbook")
public class AddBookController extends HttpServlet {

    private static final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/templates/add-book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        if (params != null) {
            try {
                String title = params.get("title")[0];
                String author = params.get("author")[0];
                Integer pages = Integer.parseInt(params.get("pages")[0]);

                if (title != null && !title.equals("") &&
                        author != null && !author.equals("") &&
                        pages != null && !pages.equals("")) {
                    AddBookModel bookModel = new AddBookModel(title, author, pages);
                    bookService.saveBook(bookModel);
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/addbook");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                response.sendRedirect("/addbook");
            }
        }
    }
}
