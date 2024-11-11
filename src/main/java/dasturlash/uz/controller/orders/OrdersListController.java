package dasturlash.uz.controller.orders;

import dasturlash.uz.dao.OrdersDAO;
import dasturlash.uz.entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/order_list")
public class OrdersListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrdersDAO ordersDAO = new OrdersDAO();
        List<Orders> orders = ordersDAO.getAllOrders();
        req.setAttribute("orders", orders);
        System.out.println(orders);
        req.getRequestDispatcher("orders/orders_list.jsp").forward(req, resp);
    }
}
