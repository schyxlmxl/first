<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/12/8
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.jdbc.goods" import="com.jdbc.goodsserver"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="css/menu.css" />
    <link rel="stylesheet" href="css/first-page.css" />
    <link rel="stylesheet" href="css/card.css" />
</head>
<script>
    function cleanconfirm(){
        if(confirm("确定要清空购物车吗？")){
            window.location.href='./Buy?goal=clean&payer=${cookie.name.value}';
            return false;
        }
    }
    function buyconfirm(){
        if(confirm("确定要结算购买吗？")){
            window.location.href='./Buy?goal=buy&payer=${cookie.name.value}';
            return false;
        }
    }
    function up(id) {
        const number=(prompt("请输入打算购买该商品的数量："));
        const num=number.toString();
        window.location.href='./Updatecart?payer=${cookie.name.value}&id='+encodeURI(id)+'&number='+encodeURI(num);
        return false;
    }
</script>
<body>
<div class="shell">
    <div class="navbar">
        <div class="nav">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/View">主页</a>
                </li>
                <li>
                    <a href="javascript:;" onclick="return prompt('请输入收货地址：')">更改地址</a>
                </li>
                <li>
                    <a href="javascript:;" onclick="return confirm('暂无优惠券可用')">优惠券</a>
                </li>
                <li>
                    <a href="#" onclick="return buyconfirm()">结算购买</a>
                </li>
                <li>
                    <a href="#" onclick="return cleanconfirm()">清空购物车</a>
                </li>
                <!-- 这个元素来定义滑动的线条 -->
                <li class="underline"></li>
            </ul>
        </div>
    </div>

    <div class="container">
        <c:forEach  var="g" items="${cartlist}" >
            <div class="card">
                <div class="imgBx">
                    <img src="${g.img}" alt="">
                </div>
                <div class="content">
                    <p>商品名：${g.goods}<br/>价格：${g.price}<br/>购买数量：${g.number}<br/></p>
                    <a href="${pageContext.request.contextPath}/Updatecart?payer=${cookie.name.value}&id=${g.id}&number=0">
                        <button type="button" name="delete" class="btn" >移出</button>
                    </a>
                    <button type="button" name="delete" class="btn" onclick="up(${g.id})" >修改数量</button>
                </div>
            </div>
        </c:forEach>

    </div>

</div>
</body>
</html>
