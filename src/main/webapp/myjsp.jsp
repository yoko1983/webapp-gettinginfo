<%@ page contentType="text/html; charset=utf-8" %>
<html><body>
はろーHello JSP World!<br>
<%= pageContext.getServletContext().getServerInfo() %><br>
java.vm.name: <%= System.getProperty("java.vm.name") %><br>
java.vm.vendor: <%= System.getProperty("java.vm.vendor") %><br>
java.vm.version: <%= System.getProperty("java.vm.version") %><br>
user.name: <%= System.getProperty("user.name") %><br>
user.dir: <%= System.getProperty("user.dir") %><br>
</body></html>