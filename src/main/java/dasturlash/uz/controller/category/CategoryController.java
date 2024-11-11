package dasturlash.uz.controller.category;

import dasturlash.uz.dao.CategoryDAO;
import dasturlash.uz.entity.Category_;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category_> categories = categoryDAO.getAllCategory();
        req.setAttribute("categories", categories);
        System.out.println(categories.toString());
        req.getRequestDispatcher("category/category_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.addCategory(new Category_(0, name, parentId, active, null, null));
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.updateCategory(new Category_(id, name, parentId, active, null, null));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("categoryId"));
            categoryDAO.deleteCategoryById(id);
        }
        resp.sendRedirect(req.getContextPath() + "/category");
    }
}
