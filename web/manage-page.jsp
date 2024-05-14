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
          <a href="loadseller-page.jsp">账号管理</a>
        </li>
        <li>
          <a href="javascript:;">销售管理</a>
          <ol>
            <li><a href="${pageContext.request.contextPath}/Performance?goal=seller">人员业绩</a></li>
            <li><a href="${pageContext.request.contextPath}/Performance?goal=goods">销售统计</a></li>
          </ol>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/View">库存管理</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/View2?goal=log">用户日志</a>
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
              <td>操作：${l[2]}</td>
              <td>操作时间：${l[1]}</td>
              <td>ip:${l[3]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:when test="${goal=='seller'}">
        <table>
          <c:forEach var="p" items="${loglist}">
            <tr>
              <td>用户：${p.payer}</td>
              <td>购买物品：${p.goods}</td>
              <td>物品id：${p.id}</td>>
              <td>购买数量：${p.number}</td>
              <td>物品单价：${p.price}</td>
              <td>购买时间：${p.time}</td>
              <td>销售人：${p.belong}</td>
            </tr>
          </c:forEach>
        </table>
        <table>
          <c:forEach var="u" items="${userlist}" varStatus="status">
            <tr>
              <td>销售人：${u.name}</td>
              <td>销售总额：${profit[status.index]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:when test="${goal=='goods'}">
        <table>
          <c:forEach var="p" items="${loglist}">
            <tr>
              <td>用户：${p.payer}</td>
              <td>购买物品：${p.goods}</td>
              <td>物品id：${p.id}</td>>
              <td>购买数量：${p.number}</td>
              <td>物品单价：${p.price}</td>
              <td>购买时间：${p.time}</td>
              <td>销售人：${p.belong}</td>
            </tr>
          </c:forEach>
        </table>
        <table>
          <c:forEach var="s" items="${sortlist}" varStatus="status">
            <tr>
              <td>商品分类：${s}</td>>
              <td>销售总额：${profit[status.index]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
    </c:choose>
  </div>

</div>
</body>
</html>
