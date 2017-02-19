package booknut.controllers;

import booknut.models.bindingModels.LoginModel;
import booknut.services.UserService;
import booknut.services.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/signup")
public class SIgnUpController extends HttpServlet {

    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/templates/sign-up.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel loginModel;
//        String signUpText = request.getParameter("signup");
        Map<String, String[]> params = request.getParameterMap();

        if (params != null) {
            String userName = params.get("username")[0];
            String password = params.get("password")[0];

            if (userName != null && !userName.equals("") &&
                    password != null && !password.equals("")) {

                loginModel = new LoginModel(userName, password);
                userService.createUser(loginModel);
                response.sendRedirect("/signin");
            } else {
                request.getRequestDispatcher("/templates/sign-up.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/templates/sign-up.jsp").forward(request, response);
        }
    }
}
