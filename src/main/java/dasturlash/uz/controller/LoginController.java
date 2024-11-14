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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = UserDao.login(username, password);
        if (login != null) {
            addUsernameCookie(resp, username);
            if (login.getRole().toString().equals("ADMIN")){
                 resp.sendRedirect("/admin");
            }else {
                resp.sendRedirect("/user");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

private void addUsernameCookie(HttpServletResponse resp, String username) {
       Cookie usernameCookie = new Cookie("username", username);
       resp.addCookie(usernameCookie);
}


}