package pirates.berry.bet.controller;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import pirates.berry.bet.model.Game;
import pirates.berry.bet.model.Player;
import pirates.berry.bet.model.RoomBlackJack;
import pirates.berry.bet.service.GameService;

@Controller
public class BlackJackController extends TextWebSocketHandler implements WebSocketHandler{

	private static RoomBlackJack room = new RoomBlackJack();

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private GameService gameService;
	
	@GetMapping("/blackjack")
	public String index() {
		
		return "blackjack";
	}

	@MessageMapping("/joinRoom")
	@SendTo("/blackjack/room")
	public RoomBlackJack joinRoom(Player player) throws Exception {
		// Get location and set location for player
		// Add player to room
		
		player.setLocation(room.getPlayers().isEmpty() ? 1 : getLocationInRoom());
		room.getPlayers().add(player);

		return room;
	}

	@MessageMapping("/swapLocation")
	public void swapPlayer(Player player) throws Exception {
		// Get location and set location for player
		// Add player to room
//		if (player.getLocation() == 0) {
//			// is swap player to banker
//			room.getBanker().ifPresent(x -> {
//				room.setBanker(Optional.ofNullable(player));
//				room.getPlayers().removeIf(p -> p.getId().equals(player.getId()));
//				simpMessagingTemplate.convertAndSend("/blackjack/room", room);
//			});
//		}
		if (player.getLocation() > 0) {
			// check location exist
			long count = room.getPlayers().stream().filter(x -> x.getLocation() == player.getLocation()).count();
			
			// is location not exist
			if (count == 0) {
				// is swap banker to player
				if (room.getBanker() != null && room.getBanker().getId().equals(player.getId())) {
					room.setPlayers(null);
				}
				
				//remove player location old
				room.getPlayers().removeIf(x -> x.getId().equals(player.getId()));
				
				//add to player location new
				room.getPlayers().add(player);
				
				simpMessagingTemplate.convertAndSend("/blackjack/room", room);
			}

		}
	}

	@MessageMapping("/startGame")
	@SendTo("/blackjack/startgame")
	public Game startGame(Set<Player> players, Player banker) throws Exception {
		Game game = new Game();
		game.setPlayers(room.getPlayers());
		game.setBanker(room.getBanker());

		gameService.save(game);

		simpMessagingTemplate.convertAndSend("/blackjack/room", room);

		return game;
	}

	private int getLocationInRoom() {
		int[] locations = room.getPlayers().stream().mapToInt(x -> x.getLocation()).toArray();
		System.out.println("length" + locations.length);
		Arrays.sort(locations);
		int i = 0;
		for (; i < locations.length; i++) {
			System.out.println("zzz" + locations[i]);
			if (i + 1 != locations[i]) {
				return i + 1;
			}
			i++;
		}
		return i;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("22222222222222222");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1111111111111111111");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
