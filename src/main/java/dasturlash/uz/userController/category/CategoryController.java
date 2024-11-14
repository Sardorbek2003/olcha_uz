package dasturlash.uz.userController.category;

import dasturlash.uz.dao.CategoryDAO;
import dasturlash.uz.entity.Category_;
import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/user/category")
public class CategoryController extends HttpServlet {
CategoryDAO categoryDAO = new CategoryDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category_> categories = categoryDAO.getAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/user/category/category.jsp").forward(req, resp);
    }
}
