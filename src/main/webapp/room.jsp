<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String user = (String) session.getAttribute("user");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
<script type="text/javascript"
	src="/imgr?src=http%3A%2F%2Fwww.ineeke.com%2Farchives%2F1486%2Fjquery-1.10.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		(function getMessages() {
			$.ajax(
					{
						dataType : "json",
						url : 'getMessages.do',
						cache : false,
						success : function(data) {
							var v = $('#text').val();
							v += '\r\n' + data.date + ' ' + data.user + '：'
									+ data.content;
							$('#text').val(v);
						}
					}).always(function() {
				getMessages();
			});
		})();
		$('#form').submit(function(event) {
			event.preventDefault();
			var values = $(this).serialize();
			$.post('setMessage.do', values, function(data) {
				$('#form>[name=content]').val('');
			}, 'json');
		});
		$('#logout').click(function() {
			$.ajax({
				dataType : "json",
				url : 'logout',
				cache : false,
				success : function(data) {
					window.location.href = 'index.jsp';
				}
			});
		});
	});
</script>
</head>
<body>
	欢迎：<%=user%><br />
	<textarea id="text" rows="20" style="width: 500;"></textarea>
	<form id="form" action="sendMessage.do" method="post">
		<input type="text" name="content" />
		<input value="发送" type="submit" />
		<input id="logout" value="离开" type="button" />
	</form>
</body>
</html>