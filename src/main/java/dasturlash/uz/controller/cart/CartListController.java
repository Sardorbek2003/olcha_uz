package dasturlash.uz.controller.cart;

import dasturlash.uz.dao.CartDAO;
import dasturlash.uz.dao.ProductDAO;
import dasturlash.uz.entity.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
@WebServlet("/cart_list")
public class CartListController extends HttpServlet {
    private CartDAO cartDAO;

    @Override
    public void init() {
        cartDAO = new CartDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cart> cards = cartDAO.getAllCarts();
        System.out.println(cards);
        req.setAttribute("carts", cards);
        req.getRequestDispatcher("cart/cart_list.jsp").forward(req, resp);
    }
}
