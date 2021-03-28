var stompClient = null;
var idClient = null;
var sessionId = null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	console.log('session', stompClient.ws._transport.url)
    	var url = stompClient.ws._transport.url;
    url = url.replace(
      "ws://localhost:8080/gs-guide-websocket/",  "");
    url = url.replace("/websocket", "");
    url = url.replace(/^[0-9]+\//, "");
    sessionId = url;
    console.log("Your current session is: " + url);
        setConnected(true);
        idClient = $("#name").val();
        stompClient.send("/app/joinRoom", {}, JSON.stringify({'id': $("#name").val(), 'sessionId' : sessionId}));
        stompClient.subscribe('/blackjack/room', function (player) {
        	$("#namePlayer1").empty();
        	$("#namePlayer2").empty();
        	$("#namePlayer3").empty();
        	JSON.parse(player.body).players.forEach(showPlayer);
            
            showBanker(JSON.parse(player.body).banker);
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function showPlayer(item) {
	console.log('cccc' + item);
	
    $("#namePlayer"+item.location).append("<span>" + item.id + "<span>");
}

function showBanker(message) {
	$("#banker").empty();
	if(message) {
		$("#banker").append("<span>" + message.id + "<span>");
	}
}


function swapLocation(location) {
	stompClient.send("/app/swapLocation", {}, JSON.stringify({'id': idClient, 'location' : location}));
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $("#banker").click(function() { swapLocation(0); });
    $("#namePlayer1").click(function() { swapLocation(1); });
    $("#namePlayer2").click(function() { swapLocation(2); });
    $("#namePlayer3").click(function() { swapLocation(3); });
});