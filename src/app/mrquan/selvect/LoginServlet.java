package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Personnel;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if ("".equals(id)||"".equals(password)){
            out.print("<script language='javascript'>alert('不可为空');window.location.href='login';</script>");
            return;
        }
        try {
            Personnel personnel = ServiceFactory.getILoginServiceInstance().login(id);
            if (personnel==null){
                out.print("<script language='javascript'>alert('账号错误');window.location.href='login';</script>");
            }else {
                if (personnel.getPassword().equals(password)){
                    if (Boolean.TRUE.equals(personnel.getAdministrator())){
                        request.getSession().setAttribute("id",id);
                        request.getSession().setAttribute("admin",Boolean.TRUE);
                        response.sendRedirect("admin");
                    }else {
                        request.getSession().setAttribute("id",id);
                        request.getSession().setAttribute("admin",Boolean.FALSE);
                        response.sendRedirect("client");
                    }
                }else {
                    out.print("<script language='javascript'>alert('密码错误');window.location.href='login';</script>");
                }
            }
        } catch (SQLException e) {
            out.print("<script language='javascript'>alert('错误');window.location.href='login';</script>");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    }
}
