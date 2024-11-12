package dasturlash.uz.controller.product;

import dasturlash.uz.dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/delete_product")
public class ProductDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProduct(id);

        resp.sendRedirect("/product_list");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
