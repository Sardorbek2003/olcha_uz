package dasturlash.uz.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "category":
                resp.sendRedirect("/category_list");
                break;
            case "product":
                resp.sendRedirect( "/product_list");
                break;
            case "cart":
                resp.sendRedirect( "/cart_list");
                break;
            case "orders":
                resp.sendRedirect( "/order_list");
                break;
            default:
                resp.sendRedirect( "/admin");
        }
    }
}
