<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/12/5
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>

<html>
<head>
    <title>添加商品</title>
</head>
<style>
    .div{
        position: fixed;
        width: 100%;
        height: 100%;
        background-color:#AEDDFF;
    }
    .goods{
        position: absolute;
        top: 30%;
        left: 0;
        right: 0;
        margin: 0 auto;
        border: 1px solid blue ;
        width: 350px;
        padding: 5px;
    }
</style>

<body >

<div  class="div">
    <form action="${pageContext.request.contextPath}/Updategoods?second=t&id=${id}"  method="post" class="goods" >
        <table >
            <tr>
                <td><label >商品名:</label></td>
                <td><input type="text" name="name" required="required" value="${name}" /></td>
            </tr>
            <tr>
                <td><label >价格:</label></td>
                <td><input type="number" name="price"  required="required" value="${price}" />(整数)</td>
            </tr>
            <tr>
                <td><label >数量:</label></td>
                <td><input type="number" name="number" required="required" value="${number}" />（整数）</td>
            </tr>
            <tr>
                <td><label >商品介绍:</label></td>
                <td><input type="text" name="introduction" required="required" value="${introduction}" /></td>
            </tr>
            <tr>
                <p>欲修改图片和分类请删除商品重新添加</p>
            </tr>

        </table>
        <input type="submit"  value="提交" align="center"/>

    </form>
</div>
</body>
</html>
