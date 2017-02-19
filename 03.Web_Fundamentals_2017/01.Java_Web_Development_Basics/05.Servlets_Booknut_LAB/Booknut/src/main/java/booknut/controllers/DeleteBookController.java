package booknut.controllers;

import booknut.services.BookService;
import booknut.services.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/shelves/delete/*")
public class DeleteBookController extends HttpServlet {

    private static final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] tokens = request.getRequestURI().split("/");
        try {
            String title = URLDecoder.decode(tokens[3], "UTF-8");
            bookService.deleteBookByTitle(title);
            response.sendRedirect("/shelves");
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            response.sendRedirect("/shelves");
        }
    }
}
