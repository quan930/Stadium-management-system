package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/clientReserve")
public class ClientReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 预定
         */
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String numbers[] = request.getParameterValues("number");//获取复选框选中内容
        if (numbers!=null){
            List<Order> orders = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            boolean yOrN = true;//判断是否有时间错误
            for (String num:numbers) {
                String date = request.getParameter(num+"date");
                String startTime = request.getParameter(num+"startTime");//开始时间
                String endTime = request.getParameter(num+"endTime");//结束
                try {
                    Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+startTime);
                    Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+endTime);
                    if (start.getTime()<end.getTime()){
                        /*
                         *  创造订单
                         *  订单编号，预定日期，预定场地编号，租借场地日期，租借场地开始时间，租借场地结束时间,顾客id
                         */
                        Order order = new Order();
                        calendar.add(Calendar.MINUTE,1);//订单编号时间+1 防止订单重复
                        order.setSerialNumber(num+new SimpleDateFormat("yyyyMMddHHmm").format(calendar.getTime()));//订单编号
                        order.setReservationDate(new Date());//预定日期
                        order.setReservationStadiumSerialNumber(num);
                        order.setLoanDate( new SimpleDateFormat("yyyy-MM-dd").parse(date));
                        order.setStartTime(start);//开始时间
                        order.setEndTime(end);//结束时间
                        order.setId("a00002");//顾客id
                        orders.add(order);
                    }else {
                        yOrN = false;
                    }
                } catch (ParseException e) {
                    out.print("<script language='javascript'>alert('时间错误');window.location.href='clientReserve.jsp';</script>");
                    e.printStackTrace();
                }
            }
            if (yOrN){
                try {
                    if (ServiceFactory.getIClientServiceInstance().reserve(orders,"a00002")){
                        out.print("<script language='javascript'>alert('预定成功');window.location.href='clientReserve.jsp';</script>");
                    }else {
                        out.print("<script language='javascript'>alert('预定失败');window.location.href='clientReserve.jsp';</script>");
                    }
                } catch (SQLException e) {
                    out.print("<script language='javascript'>alert('预定失败');window.location.href='clientReserve.jsp';</script>");
                    e.printStackTrace();
                }
            }else {
                out.print("<script language='javascript'>alert('时间错误');window.location.href='clientReserve.jsp';</script>");
            }
        }else {
            out.print("<script language='javascript'>alert('没有预定');window.location.href='clientReserve.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 查询场地
         */
        String menu= request.getParameter("menu");
        switch (menu){
            case "sportName":
                String name = request.getParameter("name");
//                System.out.println(name);
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportByName(name));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "stadium":
                String stadium = request.getParameter("stadium");
//                System.out.println(stadium);
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportsByStadium(stadium));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "typeDistrict":
                String type = request.getParameter("type");
                String district = request.getParameter("district");
//                System.out.println(type);
//                System.out.println(district);
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportsByTypeAndDistrict(type,district));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "reserveJudge":
                String reserve = request.getParameter("reserve");
//                System.out.println(reserve);
                boolean yOrN = false;
                if ("true".equals(reserve)){
                    yOrN = true;
                }
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportsByReserve(yOrN));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "rent":
//                System.out.println("租金排序");
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().listSportsByRent());
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "reserveNumber":
//                System.out.println("预定量排序");
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().listSportsByReserve());
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
