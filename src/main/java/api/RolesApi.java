package api;

import com.google.gson.Gson;
import model.RoleModel;
import payload.BasicResponse;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RolesApi", urlPatterns = {"/api/roles", "/api/roles/delete", "/api/roles/add"})
public class RolesApi extends HttpServlet {

    //GET: Khi người dùng lấy dữ liệu không có tính bảo mật (Các câu liên quan đến Select)
    //POST: Insert, Update, Delete...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath(); //Trả ra urlPattern mà user đang gọi
        BasicResponse basicResponse = new BasicResponse();

        switch (url) {
            case "/api/roles":
                basicResponse = getAllRoles();
                break;
            case "/api/roles/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse = deleteRoleById(id);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Duong dan khong ton tai");
                break;
        }

        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(dataJson);
        out.flush();
        out.close();

    }

    private BasicResponse deleteRoleById(int id) {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("Xoa Thanh Cong!");
        response.setData(roleService.deleteRoleById(id));

        return response;
    }

    private BasicResponse getAllRoles() {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        List<RoleModel> list = roleService.getAllRoles();
        response.setStatusCode(200);
        response.setData(list);

        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/roles/add":
                String name = req.getParameter("name");
                String desc = req.getParameter("desc");
                basicResponse = addNewRole(name, desc);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Duong dan khong ton tai");
                break;
        }
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(dataJson);
        out.flush();
        out.close();
    }

    private BasicResponse addNewRole(String name, String description) {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("Them Role Thanh Cong!");
        response.setData(roleService.addNewRole(name, description));

        return response;
    }
}
