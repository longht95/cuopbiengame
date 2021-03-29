package pirates.berry.bet.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pirates.berry.bet.ENUM.GameEnum;
import pirates.berry.bet.dto.GameOverUnderDTO;
import pirates.berry.bet.model.BetTicket;
import pirates.berry.bet.model.GameOverUnder;
import pirates.berry.bet.model.Player;

@Controller
@EnableScheduling
public class OverUnderController {
	private static GameOverUnder roomOverUnder = new GameOverUnder();

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@MessageMapping("/joinGame")
	@SendToUser(value = "/queue/joinGame", broadcast = false)
	public GameOverUnderDTO joinGame(@Header("simpSessionId") String sessionId) throws Exception {
		System.out.println("22222222222222222222222" + roomOverUnder.getStatus());
		GameOverUnderDTO dto = new GameOverUnderDTO();
		dto.setCreate(roomOverUnder.getCreate());
		if (GameEnum.STATUS.START.equals(roomOverUnder.getStatus())) {
			dto.setType(GameEnum.TYPE_MESSAGE.START);
		} else {
			dto.setScore(roomOverUnder.getScore());
			dto.setType(GameEnum.TYPE_MESSAGE.RESULT);
		}
		
		long dif = roomOverUnder.getCreate() - System.currentTimeMillis();
		System.out.println("time:"+(int)((dif / 10000) % 60));
		return dto;
	}

	@Scheduled(fixedDelay = 20000)
	public void gameOver() throws InterruptedException {
		roomOverUnder.setStatus(GameEnum.STATUS.INPROGESS);
		// Send message stop betting
		GameOverUnderDTO dto = new GameOverUnderDTO();
		dto.setType(GameEnum.TYPE_MESSAGE.STOP_BETTING);
		simpMessagingTemplate.convertAndSend("/overandunder/gameOver", dto);
		// Progress game
		roomOverUnder.setScore((int) (Math.random() * (4 - 1)) + 1);
		roomOverUnder.setStatus(GameEnum.STATUS.OK);
		long totalBetUnder = roomOverUnder.getUnderTickets().stream().mapToLong(t -> t.getAmountBet()).sum();
		long totalBetOver = roomOverUnder.getOverTickets().stream().mapToLong(t -> t.getAmountBet()).sum();
		System.out.println("Total bet over:" + totalBetUnder);
		System.out.println("Total bet under:" + totalBetOver);
		System.out.println(roomOverUnder.getScore());
		System.out.println("Size after : " + roomOverUnder.getOverTickets().size());
		if (totalBetOver > totalBetUnder) {
			for (int i = 0; i < roomOverUnder.getOverTickets().size(); i++) {
				long l = roomOverUnder.getOverTickets().stream().limit(i).mapToLong(t -> t.getAmountBet()).sum();
				if (l - totalBetUnder == 0) {

				}
				if (l - totalBetUnder < 0) {
					long amountOld = roomOverUnder.getOverTickets().get(i).getAmountBet();
					long a = l - amountOld;
					roomOverUnder.getOverTickets().get(i).setAmountBet(totalBetUnder - a);
					roomOverUnder.getOverTickets().subList(i, roomOverUnder.getOverTickets().size()).clear();

				}
			}
		}
		roomOverUnder.getOverTickets().parallelStream().peek(t -> t.setAmountWon(roomOverUnder.getScore()))
				.collect(Collectors.toList());
		roomOverUnder.getUnderTickets().parallelStream().peek(t -> t.setAmountWon(roomOverUnder.getScore()))
				.collect(Collectors.toList());
		// send to all user subscribe result game
		dto.setType(GameEnum.TYPE_MESSAGE.RESULT);
		dto.setScore(roomOverUnder.getScore());
		simpMessagingTemplate.convertAndSend("/overandunder/gameOver", dto);
		System.out.println("Size before : " + roomOverUnder.getOverTickets().size());
		Thread.sleep(10000);
		roomOverUnder = new GameOverUnder();
		roomOverUnder.setCreate(System.currentTimeMillis() + 200 * 1000);
		dto.setCreate(roomOverUnder.getCreate());
		// send start game
		dto.setType(GameEnum.TYPE_MESSAGE.START);
		simpMessagingTemplate.convertAndSend("/overandunder/gameOver", dto);
	}

//	@Scheduled(fixedDelay = 1000)
//	public void sendInformation() {
//		if (GameEnum.STATUS.START.equals(roomOverUnder.getStatus())) {
//			GameOverUnderDTO dto = new GameOverUnderDTO();
//			dto.setType(GameEnum.TYPE_MESSAGE.INFORMATION);
//			dto.setTotalBetOver(roomOverUnder.getOverTickets().stream().mapToLong(t -> t.getAmountBet()).sum());
//			dto.setTotalBetUnder(roomOverUnder.getUnderTickets().stream().mapToLong(t -> t.getAmountBet()).sum());
//			dto.setCountBetOver(roomOverUnder.getOverTickets().stream().map(t -> t.getUsername()).distinct().count());
//			dto.setCountBetUnder(roomOverUnder.getUnderTickets().stream().map(t -> t.getUsername()).distinct().count());
//			dto.setCreate(roomOverUnder.getCreate());
//			//send message update total bet
//			simpMessagingTemplate.convertAndSend("/overandunder/gameOver", dto);
//		}
//	}

	@MessageMapping("/betOver")
	@SendToUser
	public void betOver(@Payload Player player, @Header("simpSessionId") String sessionId, Principal principal)
			throws Exception {
		// Get location and set location for player
		// Add player to room
		if (GameEnum.STATUS.START.equals(roomOverUnder.getStatus())) {
			BetTicket betTicket = new BetTicket();
			betTicket.setPick(GameEnum.Pick.OVER);
			betTicket.setAmountBet(player.getBetAmount());
			betTicket.setUsername(principal.getName());
			roomOverUnder.getOverTickets().add(betTicket);
			simpMessagingTemplate.convertAndSendToUser(sessionId, "/queue/betOver/changes", betTicket);
			simpMessagingTemplate.convertAndSend("/overandunder/gameOver", betTicket);
		}

	}

	@MessageMapping("/betUnder")
	@SendToUser
	public void betUnder(@Payload Player player, @Header("simpSessionId") String sessionId) throws Exception {
		// Get location and set location for player
		// Add player to room
		if (GameEnum.STATUS.START.equals(roomOverUnder.getStatus())) {
			SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
			headerAccessor.setSessionId(sessionId);
			headerAccessor.setLeaveMutable(true);
			BetTicket betTicket = new BetTicket();
			betTicket.setPick(GameEnum.Pick.UNDER);
			betTicket.setAmountBet(player.getBetAmount());
			roomOverUnder.getUnderTickets().add(betTicket);
			simpMessagingTemplate.convertAndSendToUser(sessionId, "/queue/betOver/changes", betTicket,
					headerAccessor.getMessageHeaders());
			simpMessagingTemplate.convertAndSend("/overandunder/gameOver", betTicket);
		}

	}

}
