package dasturlash.uz.controller.orders;

import java.io.*;
import dasturlash.uz.dao.*;
import dasturlash.uz.entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.util.List;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    private OrdersDAO ordersDAO = new OrdersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            ordersDAO.deleteOrderById(id);
            resp.sendRedirect(req.getContextPath() + "/order"); // O'chirgandan so'ng ro'yxatga qaytish
        } else {
            List<Orders> orders = ordersDAO.getAllOrders();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("orders/orders_list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            Orders order = new Orders(
                    0,
                    Integer.parseInt(req.getParameter("user_id")),
                    req.getParameter("status"),
                    Boolean.parseBoolean(req.getParameter("activ")),
                    req.getParameter("created_date"),
                    req.getParameter("updated_date")
            );
            ordersDAO.addOrder(order);
            resp.sendRedirect(req.getContextPath() + "/order");
        } else if ("update".equals(action)) {
            Orders order = new Orders(
                    Integer.parseInt(req.getParameter("id")),
                    Integer.parseInt(req.getParameter("user_id")),
                    req.getParameter("status"),
                    Boolean.parseBoolean(req.getParameter("activ")),
                    req.getParameter("created_date"),
                    req.getParameter("updated_date")
            );
            ordersDAO.updateOrder(order);
            resp.sendRedirect(req.getContextPath() + "/order");
        }
    }
}
