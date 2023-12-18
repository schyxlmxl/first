<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/12/5
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.jdbc.goods" import="com.jdbc.goodsserver"%>

<html>
<head>
    <title>后台管理</title>
  <link rel="stylesheet" href="css/menu.css" />
  <link rel="stylesheet" href="css/first-page.css" />
  <link rel="stylesheet" href="css/card.css" />
  <link rel="stylesheet" href="css/search.css" />
</head>
<script>
  function  s(){
  var s=document.getElementById("s")
  window.location.href='./Search?name='+encodeURI(s.value);
  return false;
  }
</script>
<body>

<div class="shell">
  <c:if test="${empty goal}">
    <div class="a">
      <input type="text" class="b" id="s">
      <a href="#" class="c" onclick="return s()">
        <img src="img/first-page/s.png" style="width: 30px;" alt="">
      </a>
    </div>
  </c:if>
  <div class="navbar">
    <div class="nav">
      <ul>
        <li>
          <a href="${pageContext.request.contextPath}/View">所有商品</a>
        </li>
        <li>
          <a href="javascript:;">商品分类</a>
          <ol>
            <li><a href="${pageContext.request.contextPath}/View?sort=food">食品</a></li>
            <li><a href="${pageContext.request.contextPath}/View?sort=cloth">服饰</a></li>
            <li><a href="${pageContext.request.contextPath}/View?sort=elec">电器</a></li>
            <li><a href="${pageContext.request.contextPath}/View?sort=book">图书</a></li>
          </ol>
        </li>
        <li>
          <a href="about-goods.jsp">添加商品</a>
        </li>
        <li>
          <a href="javascript:;">用户日志</a>
          <ol>
            <li><a href="${pageContext.request.contextPath}/View2?goal=log">浏览记录</a></li>
            <li><a href="${pageContext.request.contextPath}/View2?goal=pay">购买记录</a></li>
          </ol>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/Logout">退出登录</a>
        </li>
        <!-- 这个元素来定义滑动的线条 -->
        <li class="underline"></li>
      </ul>
    </div>
  </div>

  <div class="container">

    <c:choose>
      <c:when test="${empty goal}">
        <c:forEach  var="g" items="${goods}" >
          <div class="card">
            <div class="imgBx">
              <img src="${g.img}" alt="">
            </div>
            <div class="content">
              <p>商品名：${g.name}<br/>价格：${g.price}<br/>剩余数量：${g.number}<br/>分类:${g.sort}<br/>商品介绍：${g.introduction}<br/></p>
              <a href="${pageContext.request.contextPath}/Deletegoods?id=${g.id}">
                <button type="button" name="delete" class="btn" >删除商品</button>
              </a>
              <a href="${pageContext.request.contextPath}/Updategoods?id=${g.id}">
                <button type="button" name="delete" class="btn" >修改商品</button>
              </a>
            </div>
          </div>
        </c:forEach>
      </c:when>
      <c:when test="${goal=='log'}">
        <table>
          <c:forEach var="l" items="${loglist}">
            <tr>
              <td>用户：${l[0]}</td>
              <td>访问网站时间：${l[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:when test="${goal=='pay'}">
        <table>
          <c:forEach var="p" items="${paylist}">
            <tr>
              <td>用户：${p.payer}</td>
              <td>购买物品：${p.goods}</td>
              <td>物品id：${p.id}</td>>
              <td>购买数量：${p.number}</td>
              <td>物品单价：${p.price}</td>
              <td>购买时间：${p.time}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
    </c:choose>
  </div>

</div>
</body>
</html>
