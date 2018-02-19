<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
  </head>
  <body>
<div class="main">

  <div class="content">
    <p class="title"><span class="test">
    <img src="image/bib.png" width="76" height="77" hspace="10" vspace="10" align="middle">
  </span>
    </p>
    <p>Добро пожаловать в онлайн библиотеку!</p>
  </div>

  <div>
    <p class="title">Для входа введите свои данные!</p>
    <form class="form" action="pages/main.jsp" method="post">
      Login <input type="text" name="login"/><br/>
      Password <input type="password" name="password"><br/>
      <input type="submit" value="Войти">
    </form>
  </div>

</div>
  </body>
</html>