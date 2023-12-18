<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/12/3
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/file-upload" method="post" enctype="multipart/form-data">
  <table>
    <tr>
      <td>上传者：</td>
      <td><input type="text" name="name"></td>
    </tr>
    <tr>
      <td>上传文件：</td>
      <td><input type="file" name="myfile"></td>
    </tr>
    <tr>
      <td ><input type="submit" value="上传"></td>
    </tr>
  </table>
</form>
</body>
</html>
