<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/11/30
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ page import="com.jdbc.goods" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <script>
    <%ArrayList<goods> goodslist=new ArrayList<goods>();
    goodslist=(ArrayList<goods>) request.getAttribute("goods");
    %>
  </script>
  $END$
  b
<c:forEach var="g" items="${goods}">
  aaa${g.sort}
</c:forEach>
  </body>

</html>
