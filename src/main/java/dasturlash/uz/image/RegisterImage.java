package dasturlash.uz.image;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image")
public class RegisterImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Tasvirning yo'li
        String imagePath = getServletContext().getRealPath("/images/a.png");  // Bu yerda serverdagi `images` papkasi ko'rsatiladi

        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            // Tasvirning kengaytmasini tekshirib, Content-Type ni to'g'ri belgilash
            String fileExtension = imagePath.substring(imagePath.lastIndexOf(".") + 1).toLowerCase();
            switch (fileExtension) {
                case "jpg":
                case "jpeg":
                    resp.setContentType("image/jpeg");
                    break;
                case "png":
                    resp.setContentType("image/png");
                    break;
                case "gif":
                    resp.setContentType("image/gif");
                    break;
                case "webp":
                    resp.setContentType("image/webp");
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                    return;
            }

            // Tasvirni uzatish
            try (FileInputStream imageInputStream = new FileInputStream(imageFile);
                 OutputStream out = resp.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = imageInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // Agar tasvir topilmasa
        }
    }
}
