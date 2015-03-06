<html>
  <head>
      <link rel="stylesheet" type="text/css" href="css/validation.css ">
      <script type="text/javascript" src="jquery/jquery-2.1.3.min.js"></script>
      <script type="text/javascript" src="jquery/jquery.validate.min.js "></script>
      <script type="text/javascript" src="js/ajaxRequests.js" ></script>


  </head>
  <div id="outsideContainer">
    <div id="header"></div>
    <div id="insideContainer">
      <h2 id="headerTitle">Jeu des tondeuses a gazon</h2> <br/>
      <form id="form" method="post" onsubmit="" enctype="multipart/form-data">
        <input id="file" type="file" name="file"/> <br/><br/>
        <input id="submit" type="submit" value="Envoyer"/>
      </form>
      <div id="status">
      </div>
      <div>
          <button id="start" type="button" onclick="startGameRequest();">Lancer Jeu</button>
      </div>
    </div>
  </div>
  
</html>