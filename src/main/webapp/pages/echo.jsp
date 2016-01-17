<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html> 
<head> 
<title>Web Socket Echo Test</title> 
</head> 
<body> 
    <div> 
        <input id="content" type="text" size="40" style="width: 350px" /> 
    </div> 
    <div> 
        <button id="sendName" onclick="sendName();">sendName</button> 
    </div> 
    <div> 
        <textarea id="message" style="width: 350px">Here is a message!</textarea> 
    </div> 
    <div> 
        <button id="echo" onclick="echo();" disabled="disabled">Echo message</button> 
    </div> 
    <div id="echomsg"> 
    </div> 
</body>
<script type="text/javascript" src="./lib/jquery-2.2.0.js"></script> 
<script type="text/javascript" src="./lib/sockjs-1.0.3.min.js"></script> 
<script type="text/javascript" src="./lib/stomp.min.js"></script> 
<script type="text/javascript" src="./js/coordination.js"></script> 
</html>