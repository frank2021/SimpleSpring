<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%
	String name = request.getParameter("name");
%>
<%=name%>

</head>
<body>

	<%
		String a1 = "";
		String a2 = "";
		long s = 0;
		a1 = request.getParameter("a1");
		a2 = request.getParameter("a2");
		String outs = "";
		if (a1 != null && !a1.trim().equals("") && a2 != null && !a2.trim().equals("")) {

			try {
				s = Long.parseLong(a1) + Long.parseLong(a2);
				outs = String.valueOf(s);
			} catch (Exception ex) {
				outs = "您输入的不是有效数字!";
			}
		}
		if (a1 == null || a2 == null) {
			a1 = "";
			a2 = "";
		}
	%>

	<form name="form1" action="" method="post">
		<input type="text" name="a1" id="a1" value="<%=a1%>" />+ <input
			type="text" name="a2" id="a2" value="<%=a2%>" />= <input type="text"
			name="s" id="s" value="<%=outs%>" /> <input type="submit"
			name="submit1" value="计算" />
	</form>
</body>
</html>