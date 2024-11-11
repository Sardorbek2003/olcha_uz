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

@WebServlet("/category_list")
public class CategoryListController extends HttpServlet {

    private final CategoryDAO categoryDao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println( "CategoryListController");
        List<Category_> categories = categoryDao.getAllCategory();
        System.out.println(categories);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("category/category_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            categoryDao.deleteCategoryById(categoryId);
            resp.sendRedirect("/category");
        }
    }
}
