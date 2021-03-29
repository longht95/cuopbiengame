var img = new Image();
var shoot = new Image();
img.src = "/skull.png";
shoot.src = "/shoot.png";
var stompClient = null;
var scale = 1;
var iw, ih, cw, ch, mg;
var myReq;
var c;
var ctx;
var num = 10;
var totalBetOver = 0;
var totalBetUnder = 0;
var intvl;
var score;
function connect() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {

		stompClient.subscribe('/user/queue/joinGame', function(game) {

			let gameDTO = JSON.parse(game.body);
			updateView(gameDTO);

		});
		stompClient.send("/app/joinGame", {}, JSON.stringify({}));
		stompClient.subscribe('/overandunder/gameOver', function(game) {

			let gameDTO = JSON.parse(game.body);
			updateView(gameDTO);
		});
	});
}

function updateView(gameDTO) {
	if (gameDTO.type == 'START') {
		iw = 200;
		ih = 170;
		mg = (c.width / 2) - 100;
		scale = 1;
		if (intvl) {
			clearInterval(intvl);
		}
		totalBetOver = gameDTO.totalBetOver;
		totalBetUnder = gameDTO.totalBetUnder;
		console.log('new date', new Date().getTime());
		console.log('ex', gameDTO.create);
		console.log('tru', gameDTO.create - new Date().getTime());
		console.log('time', parseInt((gameDTO.create - new Date().getTime()) / 10000));
		num = parseInt((gameDTO.create - new Date().getTime()) / 10000);
		intvl = setInterval(function() { counter(num--); }, 1000);
	}

	if (gameDTO.type == 'INFORMATION') {
		totalBetOver = gameDTO.totalBetOver;
		totalBetUnder = gameDTO.totalBetUnder;
	}
	if (gameDTO.type == 'RESULT') {
		if (intvl) {
			clearInterval(intvl);
		}
		score = gameDTO.score;
		requestAnimationFrame(animate);
	}
}
function shootDraw() {
	ctx.clearRect(0, 0, cw, ch);
	ctx.beginPath();
	ctx.fillStyle = "#083e4c";
	ctx.fillRect(0, 50, c.width, 50);
	ctx.fillStyle = "#1c7a6e";
	ctx.fillRect(0, 100, c.width, c.height - 50);
	ctx.drawImage(img, mg, 10, iw, ih);

	for (let i = 1; i <= score; i++) {
		var rWS = Math.random() * (220 - 100) + 100;
		var rHS = Math.random() * (100 - 40) + 40;
		ctx.drawImage(shoot, rWS, rHS, 50, 50);
	}

	ctx.stroke();

}
window.onload = function() {
	connect();
	c = document.getElementById("myCanvas");
	ctx = c.getContext("2d");
	iw = 200;
	ih = 170;
	cw = c.width;
	ch = c.height;
	mg = (c.width / 2) - 100;


}
function animate() {
	if (scale > 0) {
		requestAnimationFrame(animate);
	}
	ctx.clearRect(0, 0, cw, ch);
	ctx.beginPath();
	ctx.fillStyle = "#083e4c";
	ctx.fillRect(0, 50, c.width, 50);
	ctx.fillStyle = "#1c7a6e";
	ctx.fillRect(0, 100, c.width, c.height - 50);
	ctx.drawImage(img, mg, 10, iw, ih);
	ctx.stroke();
	if (iw < 350) {
		iw += 2;
		ih += 2;
		mg--;

	} else {
		scale = 0;

		shootDraw();
	}
}

function counter(num) {

	ctx.clearRect(0, 0, cw, ch);
	ctx.beginPath();
	ctx.fillStyle = "#083e4c";
	ctx.fillRect(0, 50, c.width, 50);
	ctx.fillStyle = "#1c7a6e";
	ctx.fillRect(0, 100, c.width, c.height - 50);
	ctx.drawImage(img, mg, 10, iw, ih);
	ctx.fillStyle = "#B22222";
	ctx.font = "50px Chango";
	ctx.textAlign = "center";
	ctx.fillText("" + num, c.width / 2, 70);
	ctx.fillText("TÀI", 100, 250);
	ctx.fillText("XỈU", 250, 250);
	ctx.fillStyle = "#14595b";
	ctx.fillRect(45, 270, 120, 20);
	//button
	ctx.fillRect(45, 320, 50, 20);



	ctx.fillRect(185, 270, 120, 20);

	//button
	ctx.fillRect(185, 320, 50, 20);

	c.addEventListener('click', function(event) {
		if (event.offsetX >= 45 && event.offsetX <= 60 && event.offsetY >= 320 && event.offsetX <= 340) {
			console.log('okkkk');
		} else {
			console.log('nooooo');
		}

	});
	ctx.fillStyle = "#FFFFFF";
	ctx.font = "12px Arial";
	ctx.fillText("" + totalBetOver, 70, 285);
	ctx.fillText("" + totalBetUnder, 200, 285);
	ctx.stroke();
	if (num == 0) {
		//clearInterval(intvl);
		//requestAnimationFrame(animate);
	}

}

