<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/12/1
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.jdbc.goods" %>

<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=, initial-scale=1.0">
    <title>主页</title>
  <link rel="stylesheet" href="css/menu.css" />
  <link rel="stylesheet" href="css/first-page.css" />
  <link rel="stylesheet" href="css/card.css" />
  <link rel="stylesheet" href="css/search.css" />
</head>
<script >
  function da(){
    if(confirm("确定要注销此用户吗？")){
      window.location.href='./DeleteAccount';
      return false;
    }

  }
  function  s(){
    var s=document.getElementById("s")
    window.location.href='./Search?name='+encodeURI(s.value);
    return false;
  }

</script>
<body>


<div class="shell">


  <div class="image" style="background-image: url(img/first-page/1.png);"></div>
  <div class="heading">
    <h1 class="shadow-text">欢迎访问SCH电子商务网站</h1>
  </div>

  <div class="a">
    <input type="text" class="b" id="s">
    <a href="JAVASCRIPT:;" class="c" onclick="return s()">
      <img src="img/first-page/s.png" style="width: 30px;" alt="">
    </a>
  </div>

  <div class="navbar">
    <div class="nav">
      <ul>
        <li>
          <a href="${pageContext.request.contextPath}/View">主页</a>
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
          <c:choose>
            <c:when test="${empty cookie.name.value}">
              <a href="JAVASCRIPT:;">↓搜索↓</a>
            </c:when>
            <c:otherwise>
              <a href="${pageContext.request.contextPath}/View2?goal=cart">购物车</a>
            </c:otherwise>
          </c:choose>
        </li>
        <li>
          <c:choose>
            <c:when test="${empty cookie.name.value}">
              <a href="javascript:;">----</a>
            </c:when>
            <c:otherwise>
              <a href="${pageContext.request.contextPath}/Recommand?name=${cookie.name.value}">推荐商品</a>
            </c:otherwise>
          </c:choose>
        </li>
        <li>
          <c:choose>
            <c:when test="${empty cookie.name.value}">
            <a href="load-page.jsp">登录/注册</a>
            </c:when>
            <c:otherwise>
              <a href="javascript:;">用户：${cookie.name.value}</a>
              <ol>
                <li><a href="${pageContext.request.contextPath}/Logout">退出登录</a></li>
                <li><a href="javscript:;" onclick="return da()">注销账号</a></li>
              </ol>
            </c:otherwise>
          </c:choose>
        </li>
        <!-- 这个元素来定义滑动的线条 -->
        <li class="underline"></li>
      </ul>
    </div>
    </div>




    <div class="container">

      <c:forEach  var="g" items="${goods}" >
        <div class="card">
          <div class="imgBx">
            <img src="${g.img}" alt="">
        </div>
        <div class="content">
          <p>商品名：${g.name}<br/>价格：${g.price}<br/>剩余数量：${g.number}<br/>分类:${g.sort}<br/>商品介绍：${g.introduction}<br/></p>
          <c:if test="${not empty cookie.name.value}">
            <button type="button" name="add" class="btn" onclick="window.location.href='./Addcart?payer=${cookie.name.value}&id=${g.id}'">点击加入购物车</button>
          </c:if>
        </div>
        </div>
      </c:forEach>

    </div>

</div>
</body>

</html>
