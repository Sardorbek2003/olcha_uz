package dasturlash.uz.adminController.product;

import dasturlash.uz.dao.ProductDAO;
import dasturlash.uz.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/product")
public class ProductController extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addProduct(request, response);
        } else if ("update".equals(action)) {
            updateProduct(request, response);
        } else if ("delete".equals(action)) {
            deleteProduct(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("product/product.jsp").forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        String createdBy = request.getParameter("authentication");

        // Description JSON
        String[] descriptionNames = request.getParameterValues("descriptionName[]");
        String[] descriptionTypes = request.getParameterValues("descriptionType[]");
        JSONArray descriptionArray = new JSONArray();
        for (int i = 0; i < descriptionNames.length; i++) {
            JSONObject descObject = new JSONObject();
            descObject.put("name", descriptionNames[i]);
            descObject.put("type", descriptionTypes[i]);
            descriptionArray.put(descObject);
        }

        // Images JSON
        String[] imageUrls = request.getParameterValues("image");
        JSONArray imageArray = new JSONArray();
        for (String imageUrl : imageUrls) {
            imageArray.put(imageUrl);
        }

        Product product = new Product(name, price, active, createdBy, descriptionArray.toString(), imageArray.toString());
        productDAO.addProduct(product);

        response.sendRedirect("/admin/product");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .active(active)
                .description(descriptionArray.toString())
                .updatedBy(request.getParameter("authentication"))
                .build();

        productDAO.updateProduct(product);
        response.sendRedirect("/admin/product");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.sendRedirect("/admin/product");
    }
}
