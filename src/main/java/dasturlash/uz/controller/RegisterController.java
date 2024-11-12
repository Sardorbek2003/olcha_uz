package dasturlash.uz.controller;

import dasturlash.uz.dao.*;
import dasturlash.uz.enam.*;
import dasturlash.uz.entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        UserRole userRole = UserRole.valueOf(role);
        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setRole(userRole);
        newUser.setPassword(password);

        UserDao.createUser(newUser);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Registration Successful!</h2>");
        out.println("<p>Welcome, " + name + "!</p>");
        resp.sendRedirect("/login");
    }
}
