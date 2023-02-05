package servlet;

import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Annotation: Tên định danh
 * Ký hiệu: @
 */

    //name: tên Servlet
    //urlPatterns: đường dẫn Servlet
@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/"})
public class LoginServlet extends HttpServlet {
    /*
    * - executeQuery: Select
    * - executeUpdate: Create, Update, Delete...
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie cookie = new Cookie("email", URLEncoder.encode("Nguyen Van A", "UTF-8"));
//        cookie.setMaxAge(8 * 60 * 60);
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
//        for(Cookie cookie : cookies) {
//            String name = cookie.getName();
//            if (name.equals("email")){
//                System.out.println(name + ": " + URLDecoder.decode(cookie.getValue(), "UTF-8"));
//            }
//        }
//        HttpSession httpSession = req.getSession();
//        httpSession.setAttribute("email", "Nguyen Van A");
//        String email = String.valueOf(httpSession.getAttribute("email"));
//
//        System.out.println("email: " + email);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginService loginService = new LoginService();
        boolean isSuccess = loginService.checkLogin(email, password);

        if(isSuccess){
            Cookie cookie = new Cookie("isLogon", "true");
            cookie.setMaxAge(8 * 60 * 60);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/roles");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
