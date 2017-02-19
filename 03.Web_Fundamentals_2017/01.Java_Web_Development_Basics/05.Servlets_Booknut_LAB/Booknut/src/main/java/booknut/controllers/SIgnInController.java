package booknut.controllers;

import booknut.models.bindingModels.LoginModel;
import booknut.services.UserService;
import booknut.services.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/signin")
public class SIgnInController extends HttpServlet {

    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/templates/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LoginModel loginModel;
        Map<String, String[]> params = request.getParameterMap();

        if (params != null) {
            String userName = params.get("username")[0];
            String password = params.get("password")[0];

            if (userName != null && !userName.equals("") &&
                    password != null && !password.equals("")) {

                loginModel = userService.findByUsernameAndPassword(userName, password);

                if (loginModel != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_MODEL", loginModel);
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/signin");
                }
            } else {
                response.sendRedirect("/signin");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}
