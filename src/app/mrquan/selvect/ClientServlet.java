package app.mrquan.selvect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = (String) request.getSession().getAttribute("id");
        Boolean admin =(Boolean) request.getSession().getAttribute("admin");
        if (id==null){
            out.print("<script language='javascript'>alert('请登录');window.location.href='login';</script>");
//            response.sendRedirect("login");
        }else {
            if (Boolean.FALSE.equals(admin)){
                String name = request.getParameter("menu");
//                System.out.println("查询"+name);
                request.setAttribute("menu",name);
                request.getRequestDispatcher("client.jsp").forward(request, response);
            }else {
                response.sendRedirect("admin");
            }
        }



    }
}
