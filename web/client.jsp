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
    <title>顾客端</title>

    <link rel="shortcut icon" href="assets/ico/apple-touch-icon-114-precomposed.png">

    <style>
        .content{
            background: url("background.jpg") no-repeat center 0;
            opacity: 0.5;
            position:fixed;
            top: 100px;
            left: 0;
            width:100%;
            height:100%;
            min-width:100px;
            z-index:-10;
            zoom: 1;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
        }
        .top{
            text-align:center;
        }
        .p{
            position: absolute;
            left: 10px;
            top: 2px;
            color:snow;
        }
        .a{text-align: center;letter-spacing: -2px;font-size: 40px;color: #ffffff;font-style: inherit;}
        .b{letter-spacing: -2px;font-size: 40px;color: #c61800;font-style: italic;}
        .c{letter-spacing: -2px;font-size: 40px;color: #efba00;font-style: normal;}
        .d{letter-spacing: -2px;font-size: 40px;color: #42c34a;font-style: oblique;}
        html,
        body { height: 100%; padding: 0; margin: 0; }
        .outer {
            height: calc(100% - 100px);
            padding:  0 0;
            box-sizing: border-box ;
            position: relative;
        }
        .outer_1{
            height: 100px;
            padding: 0;
            margin: 0;
            background-color: #b1d5d6;
        }
        .left {
            float: left;
            width: 300px;
            height: 100%;
        }
        .right {
            /*background-color: orange;*/
            margin-left: 300px;
            height: 100%;
        }
        .button {
            background:linear-gradient(to right ,paleturquoise,aquamarine);
            width: 100%;
            height: 50px;
            text-align: center;
            line-height: 36px;
            cursor: pointer;
            font-family: Microsoft YaHei;
            font:17px/1.5 tahoma,arial,\5b8b\4f53;
            margin:1px;padding:0;border:0;vertical-align:baseline;

        }
    </style>
</head>
<body class="body">
<div class="outer_1">
    <th class="p">客户端</th>
    <div class="top">
        <span class="a">综</span>
        <span class="b">合</span>
        <span class="c">体</span>
        <span class="a">育</span>
        <span class="d">管</span>
        <span class="b">理</span>
        <span class="b">系</span>
        <span class="b">统</span>
    </div>
</div>
<div class="content"></div>
<div class="outer">
    <div class="left">
        <form action="client" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input class="button" type="submit" name="menu" value="预订"><br>
            <input class="button" type="submit" name="menu" value="个人"><br>
            <input class="button" type="submit" name="menu" value="历史订单"><br>
        </form>
    </div>
    <div class="right">
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
    </div>
</div>
</body>
</html>