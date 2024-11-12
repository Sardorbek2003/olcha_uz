package dasturlash.uz.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class BaseController extends HttpServlet {
    protected String getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("username")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
