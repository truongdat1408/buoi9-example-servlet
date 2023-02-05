package servlet;

import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoleTable", urlPatterns = {"/roles", "/role-add"})
public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RoleService roleService = new RoleService();
        String url = req.getServletPath();

        switch (url) {
            case "/roles":
                req.setAttribute("roles", roleService.getAllRoles());
                req.getRequestDispatcher("role-table.jsp").forward(req, resp);
                break;
            case "/role-add":
                req.getRequestDispatcher("role-add.jsp").forward(req, resp);
                break;
            default:
                break;
        }

    }
}
