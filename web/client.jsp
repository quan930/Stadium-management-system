<%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/9/20
  Time: 下午1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Insert title here</title>
    <style>
        html,
        body { height: 100%; padding: 0; margin: 0; }
        .outer { height: calc(100% - 100px); padding:  0 0; box-sizing: border-box ; position: relative; }
        .outer_1{height: 100px;padding: 0;margin: 0}
        .left {
            float: left;
            width: 300px;
            background-color: red;
            height: 100%;
        }
        .right {
            background-color: orange;
            margin-left: 310px;
            height: 100%;
        }
    </style>
</head>
<body class="body">
<div class="outer_1">
    <iframe src="mrquan.html" width="100%" height="100%" frameborder="0">
    </iframe>
</div>
<div class="outer">
    <div class="left">
        <%--&lt;%&ndash;<iframe src="clientMenu.html" width="100%" height="100%" frameborder="0">&ndash;%&gt;--%>
        <%--&lt;%&ndash;</iframe>&ndash;%&gt;--%>
        <%--<%@include file="clientMenu.html"%>--%>
            <form action="client" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
                <br><input type="submit" name="menu" value="预订"><br>
                <br><input type="submit" name="menu" value="个人"><br>
                <br><input type="submit" name="menu" value="历史订单"><br>
            </form>
    </div>
    <div class="right">
        <%--<iframe src="clientOrder.jsp" width="100%" height="100%" frameborder="0">--%>
        <%
            String fun =  request.getParameter("menu");
            if (fun==null){
                fun="1";
            }
            switch (fun){
                case "预订":
                    out.print("<iframe src=\"clientReserve.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "个人":
                    out.print("<iframe src=\"clientOneself.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "历史订单":
                    out.print("<iframe src=\"clientOrder.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "1" :
                    out.print("<iframe src=\"\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
            }

        %>
        </iframe>
    </div>
</div>
</body>
</html>