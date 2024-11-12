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
import java.sql.SQLException;

@WebServlet("/update_product")
public class ProductUpdateController extends BaseProductController {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        String[] descriptionNames = request.getParameterValues("descriptionName[]");
        String[] descriptionTypes = request.getParameterValues("descriptionType[]");
        JSONArray descriptionArray = new JSONArray();
        for (int i = 0; i < descriptionNames.length; i++) {
            JSONObject descriptionObject = new JSONObject();
            descriptionObject.put("name", descriptionNames[i]);
            descriptionObject.put("type", descriptionTypes[i]);
            descriptionArray.put(descriptionObject);
        }
        String updateBy = getUsername(request);
        if (updateBy == null) {
            response.sendRedirect("/login");
            return;
        }

        // Mahsulotni yangilash
        Product product = new Product(id, name, price, descriptionArray.toString(), active, updateBy);
        System.out.println(product);
        productDAO.updateProduct(product);


        response.setStatus(HttpServletResponse.SC_OK); // Muvaffaqiyatli status
        response.sendRedirect("/product_list");
    }
}
