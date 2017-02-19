package booknut.controllers;

import booknut.models.bindingModels.AddBookModel;
import booknut.models.viewModels.ViewBookModel;
import booknut.services.BookService;
import booknut.services.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {

    private static final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] tokens = request.getRequestURI().split("/");
        try {
            String title = URLDecoder.decode(tokens[3], "UTF-8");
            ViewBookModel bookView = bookService.findBookByTitle(title);
            if (bookView != null) {
                request.setAttribute("BOOK_VIEW", bookView);
            }
            request.getRequestDispatcher("/templates/edit.jsp").forward(request, response);
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            response.sendRedirect("/shelves");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String edit = request.getParameter("edit");
        if (edit != null) {
            Map<String ,String[]> params = request.getParameterMap();
            if (params != null) {
                try {
                    String title = params.get("title")[0];
                    String author = params.get("author")[0];
                    Integer pages = Integer.parseInt(params.get("pages")[0]);
                    //delete book which was edited
                    bookService.deleteBookByTitle(title);
                    //rewrite edited book
                    AddBookModel bookModel = new AddBookModel(title, author, pages);
                    bookService.saveBook(bookModel);
                    response.sendRedirect("/shelves");
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    response.sendRedirect("/shelves");
                }
            } else {
                response.sendRedirect("/shelves");
            }
        } else {
            response.sendRedirect("/shelves");
        }
    }
}
