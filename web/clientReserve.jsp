<%@ page import="app.mrquan.factory.ServiceFactory" %>
<%@ page import="app.mrquan.pojo.SportVenue" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/9/20
  Time: 下午6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<h1>预定</h1>

<%
    out.print("<form action=\"clientReserve\">");
    Map<String,Set<String>> map = ServiceFactory.getIClientServiceInstance().listSportsInit();
    Set<String> name = map.get("name");
    out.print("<select name=\"name\">");
    for (String str:name) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"sportName\" checked=\"checked\"/>场地&emsp;");

    Set<String> stadium = map.get("stadium");
    out.print("<select name=\"stadium\">");
    for (String str:stadium) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"stadium\"/>场馆&emsp;");

    Set<String> type = map.get("type");
    out.print("<select name=\"type\">");
    for (String str:type) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    Set<String> district = map.get("district");
    out.print("<select name=\"district\">");
    for (String str:district) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"typeDistrict\"/>类别/区域&emsp;");

    out.print("<select name=\"reserve\">");
    out.print("<option value=\"true\">有预定</option>");
    out.print("<option value=\"false\">无预定</option>");
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"reserveJudge\"/>是否预定&emsp;");

    out.print("<input type=\"radio\" name=\"menu\" value=\"rent\"/>租金排序&emsp;");

    out.print("<input type=\"radio\" name=\"menu\" value=\"reserveNumber\"/>预定量排序&emsp;");

    out.print("<input type=\"submit\" value=\"查询\">");
    out.print("<br>");
    out.print("</form>");
    List<SportVenue> pojos = (List<SportVenue>) request.getAttribute("listSport");
    if (pojos!=null){//有查询内容
        //显示查询后表格
        out.print("<form action=\"clientReserve\" method=\"post\">");
        out.print("<table border=\"1\" align=\"center\">");
        out.print("<tr><td>场地名称</td><td>所属场馆</td><td>区域</td><td>运动类型</td><td>允许年龄</td><td>租金</td><td>预定</td><td align=\"center\">租借时间</td></tr>");
        for (int i = 0; i < pojos.size(); i++) {
            out.print("<tr><td>"+pojos.get(i).getSerialName()+"</td><td>"+pojos.get(i).getStadium()+"</td><td>"
                    +pojos.get(i).getDistrict()+"</td><td>"+pojos.get(i).getMotionType()+
                    "</td><td>"+pojos.get(i).getAgeLowerLimit()+"~"+pojos.get(i).getAgeUpperLimit()+"</td><td>"+pojos.get(i).getRent()+"</td>");
            //是否租借
            out.print("<td><input type=\"checkbox\" name=\"number\" value=\""+pojos.get(i).getSerialNumber()+"\"></td>");
            //租借日期
            out.print("<td>");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR,7);
            String minDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR,1);
            String maxTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            out.print("<input type=\"date\" name=\""+pojos.get(i).getSerialNumber()+"date\" value=\""+minDate+"\" min=\""+minDate+"\" max=\""+maxTime+"\">");
            //租借时间
            out.print("<select name=\""+pojos.get(i).getSerialNumber()+"startTime"+"\"><option value=\"06:00\">06:00</option><option value=\"07:00\">07:00</option><option value=\"08:00\">08:00</option><option value=\"09:00\">09:00</option><option value=\"10:00\">10:00</option><option value=\"11:00\">11:00</option><option value=\"12:00\">12:00</option><option value=\"13:00\">13:00</option><option value=\"14:00\">14:00</option><option value=\"15:00\">15:00</option><option value=\"16:00\">16:00</option><option value=\"17:00\">17:00</option><option value=\"18:00\">18:00</option><option value=\"19:00\">19:00</option><option value=\"20:00\">20:00</option><option value=\"21:00\">21:00</option><option value=\"22:00\">22:00</option></select>");
            out.print("～<select name=\""+pojos.get(i).getSerialNumber()+"endTime"+"\"><option value=\"06:00\">06:00</option><option value=\"07:00\">07:00</option><option value=\"08:00\">08:00</option><option value=\"09:00\">09:00</option><option value=\"10:00\">10:00</option><option value=\"11:00\">11:00</option><option value=\"12:00\">12:00</option><option value=\"13:00\">13:00</option><option value=\"14:00\">14:00</option><option value=\"15:00\">15:00</option><option value=\"16:00\">16:00</option><option value=\"17:00\">17:00</option><option value=\"18:00\">18:00</option><option value=\"19:00\">19:00</option><option value=\"20:00\">20:00</option><option value=\"21:00\">21:00</option><option value=\"22:00\">22:00</option></select>");
            out.print("</td>");
            out.print("</tr>");
        }
        out.print("<tr><td colspan=\"8\" align=\"center\"><input type=\"submit\" value=\"预定\"></td></tr>");
        out.print("</table>");
    }
%>
<%--<form action="clientReserve" method="post">--%>
    <%--<table border="1" align="center">--%>
        <%--<tr>--%>
            <%--<td>场地名称</td>--%>
            <%--<td>所属场馆</td>--%>
            <%--<td>区域</td>--%>
            <%--<td>运动类型</td>--%>
            <%--<td>允许年龄</td>--%>
            <%--<td>租金</td>--%>
            <%--<td>预定</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>场地名称</td>--%>
            <%--<td>所属场馆</td>--%>
            <%--<td>区域</td>--%>
            <%--<td>运动类型</td>--%>
            <%--<td>允许年龄</td>--%>
            <%--<td>租金</td>--%>
            <%--<td>1<input type="checkbox" name="number" value="1"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>场地名称</td>--%>
            <%--<td>所属场馆</td>--%>
            <%--<td>区域</td>--%>
            <%--<td>运动类型</td>--%>
            <%--<td>允许年龄</td>--%>
            <%--<td>租金</td>--%>
            <%--<td>2<input type="checkbox" name="number" value="2"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>场地名称</td>--%>
            <%--<td>所属场馆</td>--%>
            <%--<td>区域</td>--%>
            <%--<td>运动类型</td>--%>
            <%--<td>允许年龄</td>--%>
            <%--<td>租金</td>--%>
            <%--<td>3<input type="checkbox" name="number" value="3"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="7"><input type="submit" value="预定"></td>--%>

        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>


<%--<td>--%>
    <%--<input type="date" name="编号date" value="2018-09-30" min="2018-01-01" max="2020-01-01">--%>
    <%--开始时间--%>
    <%--<select name="name">--%>
        <%--<option value="06:00">06:00</option>--%>
        <%--<option value="07:00">07:00</option>--%>
        <%--<option value="08:00">08:00</option>--%>
        <%--<option value="09:00">09:00</option>--%>
        <%--<option value="10:00">10:00</option>--%>
        <%--<option value="11:00">11:00</option>--%>
        <%--<option value="12:00">12:00</option>--%>
        <%--<option value="13:00">13:00</option>--%>
        <%--<option value="14:00">14:00</option>--%>
        <%--<option value="15:00">15:00</option>--%>
        <%--<option value="16:00">16:00</option>--%>
        <%--<option value="17:00">17:00</option>--%>
        <%--<option value="18:00">18:00</option>--%>
        <%--<option value="19:00">19:00</option>--%>
        <%--<option value="20:00">20:00</option>--%>
        <%--<option value="21:00">21:00</option>--%>
        <%--<option value="22:00">22:00</option>--%>
    <%--</select>--%>
    <%--结束时间--%>
    <%--<select name="name">--%>
        <%--<option value="06:00">06:00</option>--%>
        <%--<option value="07:00">07:00</option>--%>
        <%--<option value="08:00">08:00</option>--%>
        <%--<option value="09:00">09:00</option>--%>
        <%--<option value="10:00">10:00</option>--%>
        <%--<option value="11:00">11:00</option>--%>
        <%--<option value="12:00">12:00</option>--%>
        <%--<option value="13:00">13:00</option>--%>
        <%--<option value="14:00">14:00</option>--%>
        <%--<option value="15:00">15:00</option>--%>
        <%--<option value="16:00">16:00</option>--%>
        <%--<option value="17:00">17:00</option>--%>
        <%--<option value="18:00">18:00</option>--%>
        <%--<option value="19:00">19:00</option>--%>
        <%--<option value="20:00">20:00</option>--%>
        <%--<option value="21:00">21:00</option>--%>
        <%--<option value="22:00">22:00</option>--%>
    <%--</select>--%>
<%--</td>--%>

<%--<select name="name"><option value="1">1</option><option value="2">2</option></select>--%>
</body>
</html>