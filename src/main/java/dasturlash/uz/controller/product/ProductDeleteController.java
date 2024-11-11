package dasturlash.uz.controller.product;

import dasturlash.uz.dao.ProductDAO;
import dasturlash.uz.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Product;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
