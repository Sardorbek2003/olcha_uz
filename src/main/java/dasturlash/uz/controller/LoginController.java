package dasturlash.uz.controller;

import dasturlash.uz.dao.*;
import dasturlash.uz.entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        System.out.println("saassaas");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = UserDao.login(username, password);
        System.out.println(login);
        if (login != null) {
            System.out.println("ds");
            if (login.getRole().toString().equals("ADMIN")){
                System.out.println("admin");
                 resp.sendRedirect("/admin");
            }else {
                System.out.println("user");
                resp.sendRedirect("/user");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}