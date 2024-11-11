package dasturlash.uz.controller;

import dasturlash.uz.dao.UserDao;
import dasturlash.uz.enam.UserRole;
import dasturlash.uz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
