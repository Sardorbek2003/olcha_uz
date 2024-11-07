package dasturlash.uz.controller;

import dasturlash.uz.dao.UserDao;
import dasturlash.uz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserDao.allUsers();

        req.setAttribute("users", users);

        req.getRequestDispatcher("/userList.jsp").forward(req, resp);
    }
}
