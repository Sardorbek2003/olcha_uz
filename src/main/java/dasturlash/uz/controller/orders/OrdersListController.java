package dasturlash.uz.controller.orders;

import dasturlash.uz.dao.*;
import dasturlash.uz.entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;
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
