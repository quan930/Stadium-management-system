package app.mrquan.selvect;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 取消
         */
        String number = request.getParameter("number");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try {
            boolean m = ServiceFactory.getIClientServiceInstance().cancelReserve(number);
            if (m){
                out.print("<script language='javascript'>alert('取消成功');window.location.href='clientOrder.jsp';</script>");
            }else {
                out.print("<script language='javascript'>alert('不可取消');window.location.href='clientOrder.jsp';</script>");
            }
        } catch (SQLException e) {
            out.print("<script language='javascript'>alert('不可取消');window.location.href='clientOrder.jsp';</script>");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 查询订单
         */
        String id = (String) request.getSession().getAttribute("id");
        String select = request.getParameter("select");
        if ("allList".equals(select)){
            try {
                request.setAttribute("fun","allList");
                request.setAttribute("lists",ServiceFactory.getIClientServiceInstance().findAllOrdersByPersonnel(id));
                request.getRequestDispatcher("clientOrder.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            //待使用订单
            if ("list".equals(select)){
                try {
                    request.setAttribute("fun","list");
                    request.setAttribute("lists",ServiceFactory.getIClientServiceInstance().findOrdersByPersonnel(id));
                    request.getRequestDispatcher("clientOrder.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
