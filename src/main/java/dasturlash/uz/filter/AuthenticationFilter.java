package dasturlash.uz.filter;

import jakarta.servlet.*;
        import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Cookie[] cookies = httpServletRequest.getCookies();

        String username = null;
        for (int i = 0; i < cookies.length && username == null; i++) {
            if (cookies[i].getName().equals("username")) {
                username = cookies[i].getValue();
            }
        }

        if (username == null) {
            httpServletResponse.sendRedirect("/login");
            return;
        }
        addUsernameToCookie(httpServletResponse, username);

        httpServletRequest.setAttribute("authentication", username);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void addUsernameToCookie(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(30);
        response.addCookie(cookie);
    }
}