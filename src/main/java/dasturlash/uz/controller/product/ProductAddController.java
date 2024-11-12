package dasturlash.uz.controller.product;

import org.json.JSONArray;
import org.json.JSONObject;
import dasturlash.uz.dao.ProductDAO;
import dasturlash.uz.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add_product")
public class ProductAddController extends BaseProductController {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        String createdBy = getUsername(request);
               if (createdBy == null) {
            response.sendRedirect("/login");
            return;
        }

        String[] descriptionNames = request.getParameterValues("descriptionName[]");
        String[] descriptionTypes = request.getParameterValues("descriptionType[]");
        JSONArray descriptionArray = new JSONArray();
        for (int i = 0; i < descriptionNames.length; i++) {
            JSONObject descriptionObject = new JSONObject();
            descriptionObject.put("name", descriptionNames[i]);
            descriptionObject.put("type", descriptionTypes[i]);
            descriptionArray.put(descriptionObject);
        }


        Product product = new Product(name, price, active,createdBy, descriptionArray.toString());

        // Mahsulotni qo'shish
        productDAO.addProduct(product);
        response.setStatus(HttpServletResponse.SC_OK); // Muvaffaqiyatli status
        response.sendRedirect("/product_list");
    }
}
