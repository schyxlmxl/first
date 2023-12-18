<%--
  Created by IntelliJ IDEA.
  User: 24871
  Date: 2023/11/30
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录与注册</title>
    <style type="text/css">
        *{
            margin:0;
            padding:0;
        }
        body{
            height:100vh;
            background:#e7e7e7 url("img/load-page/a.jpg")
            center no-repeat fixed;
            background-size: cover;
            backdrop-filter: blur(5px);
        }
        .container{
            background-color: #e7e7e7;
            border-radius: 0.7rem;
            box-shadow:
                    0 0.0rem 1.7rem rgba(0,0,0,0.25),
                    0 0.7rem 0.7rem rgba(0,0,0,0.22);
            height: 420px;
            max-width: 750px;
            overflow: hidden;
            position: relative;
            width: 100%;
        }
        body{
            display:flex;
            justify-content: center;
            align-items: center;
        }
        .container-form{
            height: 100%;
            position: absolute;
            top: 0 ;
            transition: all 0.6s ease-in-out;
        }
        .container-signin{
            left:0;
            width:50%;
            z-index: 2;
        }
        .container-signup{
            left:0;
            opacity: 0;
            width: 50%;
            z-index: 1;
        }
        .form{
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 0 3rem;
            height: 100%;
            text-align: center;
            background-color: #e7e7e7;
        }
        .form-title{
            font-weight: 300;
            margin: 0;
            margin-bottom: 1.25rem;
        }
        .link{
            color:#333;
            font-size: 0.9rem;
            margin:1.5rem 0;
            text-decoration: none;
        }
        .input{
            width: 100%;
            background-color: #fff;
            padding: 0.9rem 0.9rem;
            margin: 0.5rem 0;
            border:none;
            outline: none;
        }
        .btn{
            background-color: #f25d8e;
            box-shadow: 0 4px 4px rgba(255,112,159,.3);
            border-radius: 5px;
            color: #e7e7e7;
            border: none;
            cursor: pointer;
            font-size: 0.8rem;
            font-weight: bold;
            letter-spacing: 0.1rem;
            padding: 0.9rem 4rem;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
        }
        .form>.btn{
            margin-top: 1.5rem;
        }
        .btn:active{
            transform: scale(0.95);
        }
        .container-overlay{
            height: 100%;
            left:50%;
            overflow: hidden;
            position: absolute;
            top: 0;
            transition: transform 0.6s ease-in-out;
            width: 50%;
            z-index: 100;
        }
        .overlay{
            width: 200%;
            height: 100%;
            position: relative;
            left: -100%;
            background: url("img/load-page/a.jpg")
            no-repeat center fixed;
            background-size: cover;
            transition: transform 0.6s ease-in-out;
            transform: translateX(0);
        }
        .overlay-panel{
            height: 100%;
            width: 50%;
            position: absolute;
            top: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
        }
        .overlay-left{
            transform: translateX(-20%);
        }
        .overlay-right{
            right: 0;
            transform: translateX(0);
        }
        .panel-active .overlay-left{
            transform: translateX(0);
        }
        .panel-active .container-overlay{
            transform: translateX(-100%);
        }
        .panel-active .overlay{
            transform: translateX(50%);
        }
        .panel-active .container-signin{
            transform: translateX(100%);
        }
        .panel-active .container-signup{
            opacity: 1;
            z-index: 5;
            transform: translateX(100%);
        }
    </style>

</head>
<body>
<div class="container">
    <!--register-->
    <div class="container-form container-signup">
        <form action="${pageContext.request.contextPath}/Register" class="form" id="form1" method="post">
            <h2 class="form-title">注册账号</h2>
            <input type="text" placeholder="User" class="input" name="name">
            <input type="email" placeholder="Email" class="input" name="email">
            <input type="password" placeholder="Password" class="input" name="password">
            <button type="submit" class="btn">注册</button>
        </form>
    </div>
    <!--sign up-->
    <div class="container-form container-signin" >
        <form action="${pageContext.request.contextPath}/Login" class="form" id="form2" method="post">
            <h2 class="form-title">欢迎登录</h2>
            <input type="text" placeholder="user" class="input" name="name">
            <input type="password" placeholder="Password" class="input" name="password">
            <a href="#" class="link">忘记密码?</a>
            <button type="submit" class="btn">登录</button>
        </form>
    </div>
    <!--part-->
    <div class="container-overlay">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <button class="btn" id="signIn">
                    已有账号，直接登录
                </button>
            </div>
            <div class="overlay-panel overlay-right">
                <button class="btn" id="signUp">
                    没有账号，点击注册
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    const signInBtn=document.querySelector("#signIn")
    const signUpBtn=document.querySelector("#signUp")
    const container=document.querySelector(".container")
    signInBtn.addEventListener("click",()=>{container.classList.remove("panel-active")})
    signUpBtn.addEventListener("click",()=>{container.classList.add("panel-active")})
</script>
</html>
