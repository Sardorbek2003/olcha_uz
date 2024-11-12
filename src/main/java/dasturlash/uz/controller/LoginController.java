package dasturlash.uz.controller;

import dasturlash.uz.dao.UserDao;
import dasturlash.uz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        addCookie(resp,username);
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

    private void addCookie(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie("username", username);
        System.out.println("Cookie name: " + cookie.getName());
        System.out.println("Cookie value: " + cookie.getValue());
        cookie.setMaxAge(60);
        resp.addCookie(cookie);
    }

}