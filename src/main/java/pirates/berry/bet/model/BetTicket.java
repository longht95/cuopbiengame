package pirates.berry.bet.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pirates.berry.bet.ENUM.GameEnum;
import pirates.berry.bet.ENUM.GameEnum.Pick;
import pirates.berry.bet.ENUM.TicketEnum;

@Entity
@Table(name = "BetTicket")
public class BetTicket {
	@Id
	private Long id;
	@Enumerated(EnumType.STRING)
	private GameEnum.Pick pick;
	private Long amountBet;
	private String username;
	private TicketEnum.STATUS status;
	private Long amountWon;
	@ManyToOne
	private GameOverUnder gameOverUnder;
	
	public GameOverUnder getGameOverUnder() {
		return gameOverUnder;
	}
	public void setGameOverUnder(GameOverUnder gameOverUnder) {
		this.gameOverUnder = gameOverUnder;
	}
	
	public TicketEnum.STATUS getStatus() {
		return status;
	}
	public void setStatus(TicketEnum.STATUS status) {
		this.status = status;
	}
	public Long getAmountWon() {
		return amountWon;
	}
	public void setAmountWon(int score) {
		if (GameEnum.Pick.OVER.equals(this.pick) && score > 9) {
			this.amountWon = this.amountBet;
		}
		if (GameEnum.Pick.UNDER.equals(this.pick) && score <= 9) {
			this.amountWon = this.amountBet;
		}
		this.amountWon = this.amountBet * (-1);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GameEnum.Pick getPick() {
		return pick;
	}
	public void setPick(GameEnum.Pick pick) {
		this.pick = pick;
	}
	public Long getAmountBet() {
		return amountBet;
	}
	public void setAmountBet(Long amountBet) {
		this.amountBet = amountBet;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BetTicket() {
		
	}
}
