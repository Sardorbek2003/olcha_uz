package dasturlash.uz.controller.product;

import dasturlash.uz.dao.*;
import dasturlash.uz.entity.*;
import org.json.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/product_list")
public class ProductListController extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();

        // Process description JSONB or JSON string using org.json
        for (Product product : products) {
            if (product.getDescription() != null) {
                try {
                    // Parse the description string as a JSON array
                    JSONArray descriptionJsonArray = new JSONArray(product.getDescription());
                    StringBuilder descriptionText = new StringBuilder();
                    for (int i = 0; i < descriptionJsonArray.length(); i++) {
                        JSONObject descriptionObj = descriptionJsonArray.getJSONObject(i);
                        String name = descriptionObj.getString("name");
                        String type = descriptionObj.getString("type");
                        descriptionText.append(name).append(" (").append(type).append(") ");
                    }
                    product.setDescription(descriptionText.toString()); // Convert to plain string
                } catch (Exception e) {
                    // Handle JSON parsing error, leave description as-is if invalid
                    e.printStackTrace();
                }
            }
        }

        req.setAttribute("products", products);
        req.getRequestDispatcher("product/product_list.jsp").forward(req, resp);
    }
}
