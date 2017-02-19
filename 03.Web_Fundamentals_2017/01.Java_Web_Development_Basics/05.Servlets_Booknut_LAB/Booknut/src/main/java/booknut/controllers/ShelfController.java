package booknut.controllers;

import booknut.models.viewModels.ViewBookModel;
import booknut.services.BookService;
import booknut.services.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shelves")
public class ShelfController extends HttpServlet {

    private static final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ViewBookModel> bookViews = bookService.getAllBooks();
        request.setAttribute("BOOK_VIEWS", bookViews);
        request.getRequestDispatcher("/templates/shelves.jsp").forward(request, response);
    }
}
