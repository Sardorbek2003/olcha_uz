package dasturlash.uz.controller.cart;

import dasturlash.uz.dao.CartDAO;
import dasturlash.uz.entity.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private final CartDAO cartDAO = new CartDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            cartDAO.deleteCart(id);
            resp.sendRedirect(req.getContextPath() + "/cart");
        } else {
            List<Cart> cartList = cartDAO.getAllCarts();
            req.setAttribute("carts", cartList);
            req.getRequestDispatcher("cart/cart_list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            Cart cart = new Cart();
            cart.setUser_id(Integer.parseInt(req.getParameter("user_id")));
            cart.setProduct_id(Integer.parseInt(req.getParameter("product_id")));
            cart.setActiv(Boolean.parseBoolean(req.getParameter("activ")));
            cart.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            cartDAO.createCart(cart);
        } else if ("update".equals(action)) {
            Cart cart = new Cart();
            cart.setId(Integer.parseInt(req.getParameter("id")));
            cart.setUser_id(Integer.parseInt(req.getParameter("user_id")));
            cart.setProduct_id(Integer.parseInt(req.getParameter("product_id")));
            cart.setActiv(Boolean.parseBoolean(req.getParameter("activ")));
            cart.setQuantity(Integer.parseInt(req.getParameter("quantity")));

            cartDAO.updateCart(cart);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}