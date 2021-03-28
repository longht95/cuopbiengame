package pirates.berry.bet.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pirates.berry.bet.ENUM.GameEnum;
import pirates.berry.bet.ENUM.GameEnum.STATUS;

@Entity
@Table(name = "GameOverUnder")
public class GameOverUnder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int score;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gameOverUnder")
	private List<BetTicket> overTickets = new LinkedList<BetTicket>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gameOverUnder")
	private List<BetTicket> underTickets = new LinkedList<BetTicket>();
	@Enumerated(EnumType.STRING)
	private GameEnum.STATUS status = GameEnum.STATUS.START;
	
	private long create;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<BetTicket> getOverTickets() {
		return overTickets;
	}
	public void setOverTickets(List<BetTicket> overTickets) {
		this.overTickets = overTickets;
	}
	public List<BetTicket> getUnderTickets() {
		return underTickets;
	}
	public void setUnderTickets(List<BetTicket> underTickets) {
		this.underTickets = underTickets;
	}
	public long getCreate() {
		return create;
	}
	public void setCreate(long create) {
		this.create = create;
	}
	public GameEnum.STATUS getStatus() {
		return status;
	}
	public void setStatus(GameEnum.STATUS status) {
		this.status = status;
	}
	public GameOverUnder(Long id, LinkedList<BetTicket> overTickets, LinkedList<BetTicket> underTickets,
			STATUS status) {
		this.id = id;
		this.overTickets = overTickets;
		this.underTickets = underTickets;
		this.status = status;
	}
	
	public GameOverUnder() {
		
	}
}
