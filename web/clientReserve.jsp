<%@ page import="app.mrquan.factory.ServiceFactory" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="app.mrquan.pojo.SportVenue" %><%--
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
<body bgcolor="#ffe4c4">

<h1>顾客预定界面</h1>

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
        out.print("<table border=\"1\" align=\"center\">");
        out.print("<tr><td>场地名称</td><td>所属场馆</td><td>区域</td><td>运动类型</td><td>允许年龄</td><td>租金</td></tr>");
        for (int i = 0; i < pojos.size(); i++) {
            out.print("<tr><td>"+pojos.get(i).getSerialName()+"</td><td>"+pojos.get(i).getStadium()+"</td><td>"
                    +pojos.get(i).getDistrict()+"</td><td>"+pojos.get(i).getMotionType()+
                    "</td><td>"+pojos.get(i).getAgeLowerLimit()+"~"+pojos.get(i).getAgeUpperLimit()+"</td><td>"+pojos.get(i).getRent()+"</td></tr>");
        }
        out.print("</table>");
    }
%>
<%--<table border="1" align="center">--%>
    <%--<tr>--%>
        <%--<td>场地名称</td>--%>
        <%--<td>所属场馆</td>--%>
        <%--<td>区域</td>--%>
        <%--<td>运动类型</td>--%>
        <%--<td>允许年龄</td>--%>
        <%--<td>租金</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>场地名称</td>--%>
        <%--<td>所属场馆</td>--%>
        <%--<td>区域</td>--%>
        <%--<td>运动类型</td>--%>
        <%--<td>允许年龄</td>--%>
        <%--<td>租金</td>--%>
    <%--</tr>--%>
<%--</table>--%>
<%--<select name="name"><option value="1">1</option><option value="2">2</option></select>--%>
</body>
</html>