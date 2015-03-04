
<html>
  <head>
      <link rel="stylesheet" type="text/css" href="css/validation.css ">
      <script type="text/javascript" src="jquery/jquery-2.1.3.min.js"></script>
      <script type="text/javascript" src="jquery/jquery.validate.min.js "></script>
      <script type="text/javascript">
        $(function() {
          $("#form").validate({
                rules : {
                        file : {
                                required : true
                        }
                },
                messages : {
                        file : {
                                required : "Choisissez un fichier s'il vous plait",
                        }
                }
          });
        });
      </script>

    <title></title>



  </head>
  <div id="outsideContainer">
    <div id="header"></div>
    <div id="insideContainer">
      <h2 id="headerTitle">Choisissez un ficher avec les positions des tondeuses </h2> <br/>
      <form id="form" method="post" action="UploadFileServlet" enctype="multipart/form-data">
        <input id="file" type="file" name="file"/> <br/><br/>
        <input id="submit" type="submit" value="Upload"/>
    </form>
    </div>
  </div>
  
</html>