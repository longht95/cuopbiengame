package pirates.berry.bet.model;

import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Player")
public class Player {
	@Id
	private Long id;
	
	private String userId;
	
	@Convert(converter = CardConverter.class)
	private Set<Card> cards;
	
	private Long betAmount;

	@ManyToOne
	private Game game;
	
	@Transient
	private int location;
	
	@Transient
	private String sessionId;
	

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public Long getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Long betAmount) {
		this.betAmount = betAmount;
	}

	public Player(Long id, String userId, Set<Card> cards, Long betAmount) {
		this.id = id;
		this.userId = userId;
		this.cards = cards;
		this.betAmount = betAmount;
	}
	
	public Player() {
		
	}
	
}
