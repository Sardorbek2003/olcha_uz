package dasturlash.uz.adminController.orders;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dasturlash.uz.dao.OrdersDAO;
import dasturlash.uz.entity.OrderUser;
import dasturlash.uz.entity.Orders;

@WebServlet("/admin/orders")
public class OrderController extends HttpServlet {

    private OrdersDAO ordersDAO = new OrdersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            ordersDAO.deleteOrderById(id);
            resp.sendRedirect(req.getContextPath() + "/admin/orders");  // Delete tugmasi ishlaganda shu yo'nalishga qaytadi
        } else {
            List<OrderUser> orders = ordersDAO.getAllOrders();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/admin/orders/orders.jsp").forward(req, resp);  // Barcha buyurtmalarni yuklash uchun
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
            resp.sendRedirect(req.getContextPath() + "/admin/orders");  // Yangi buyurtma qo'shilgandan keyin yo'nalishga qaytadi
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
            resp.sendRedirect(req.getContextPath() + "/admin/orders");  // Buyurtma yangilanganidan keyin qaytadi
        }
    }
}
