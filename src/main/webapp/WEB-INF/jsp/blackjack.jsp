<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/app.js"></script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Join Room</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Out Room
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">id?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
        </div>
    </div>
   <div class="row">
    <div class="col-md-12" style="margin-bottom:100px;">
    	<div id="banker" class="banker" style="border-radius:50%;width:70px;height:70px;background-color:grey;margin:0 auto;">
    	</div>
    	
    </div>
    <div class="col-md-4">
   		<div id="namePlayer1" class="namePlayer1" style="border-radius:50%;width:70px;height:70px;background-color:grey;">
    	</div>
   		
    </div>
     <div class="col-md-4">
   		<div id="namePlayer2" class="namePlayer2" style="border-radius:50%;width:70px;height:70px;background-color:grey;">
    	</div>
   		
    	
    </div>
     <div class="col-md-4">
   		<div id="namePlayer3" class="namePlayer3" style="border-radius:50%;width:70px;height:70px;background-color:grey;">
    	</div>
   		
    </div>
    </div>
</div>
</body>
</html>