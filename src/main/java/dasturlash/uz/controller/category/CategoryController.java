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
public class CategoryController extends BaseCategory_Controller {
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
            String createBy = getUsername(req);
            if (createBy == null) {
                resp.sendRedirect("/login");
            }
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.addCategory(new Category_(name, parentId, active,createBy));
        } else if ("update".equals(action)) {
            String updateBy = getUsername(req);
            if (updateBy == null) {
                resp.sendRedirect("/login");
            }
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            categoryDAO.updateCategory(new Category_(name, parentId, active,updateBy));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("categoryId"));
            categoryDAO.deleteCategoryById(id);
        }
        resp.sendRedirect(req.getContextPath() + "/category");
    }
}
