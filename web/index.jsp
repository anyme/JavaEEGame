<%--
  Created by IntelliJ IDEA.
  User: anastasia
  Date: 26/02/15
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <h2>Choisissez un ficher avec les positions de test </h2> <br/>
    <form method="post" action="UploadFileServlet" enctype="multipart/form-data">
        <input type="file" name="file"/> <br/><br/>
        <input type="submit" value="Go!"/>
    </form>
  </body>
</html>
