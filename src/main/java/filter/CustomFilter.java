package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//urlPatterns: Những link nào khi người dùng gọi sẽ kích hoạt filter
@WebFilter(urlPatterns = {"/roles"})
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        boolean isLogin = false;

        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies){
                if("isLogon".equals(cookie.getName())) {
                    isLogin = true;
                    break;
                }
            }
        }

        if(isLogin) {
            //Cho phép truy cập vào servlet được chỉ định ở urlPattern tương ứng
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
