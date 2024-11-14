package dasturlash.uz.adminController.category;

import dasturlash.uz.dao.CategoryDAO;
import dasturlash.uz.entity.Category_;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class CategoryController extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category_> categories = categoryDAO.getAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/admin/category/category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String createBy = req.getParameter("authentication");

        if ("add".equals(action)) {
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.addCategory(new Category_(name, parentId, active, createBy));
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.updateCategory(new Category_(id, name, parentId, active, createBy));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("categoryId"));
            categoryDAO.deleteCategoryById(id);
        }
        resp.sendRedirect("/admin/category");
    }
}
