package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/clientReserve")
public class ClientReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menu= request.getParameter("menu");
        switch (menu){
            case "sportName":
                String name = request.getParameter("name");
                System.out.println(name);
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportByName(name));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "stadium":
                String stadium = request.getParameter("stadium");
                System.out.println(stadium);
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
                System.out.println(type);
                System.out.println(district);
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSportsByTypeAndDistrict(type,district));
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "reserveJudge":
                String reserve = request.getParameter("reserve");
                System.out.println(reserve);
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
                System.out.println("租金排序");
                try {
                    request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().listSportsByRent());
                    request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "reserveNumber":
                System.out.println("预定量排序");
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
