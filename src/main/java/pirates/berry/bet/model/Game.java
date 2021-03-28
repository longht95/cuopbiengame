package pirates.berry.bet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import pirates.berry.bet.ENUM.CardEnum;
import pirates.berry.bet.ENUM.GameEnum;

@Entity
@Table(name="Game")
public class Game {
	@Id
	@GeneratedValue
	private Long id;
	
	@Transient
	private final List<Card> cards = new ArrayList<Card>(52);
	
	@OneToMany(mappedBy="game")
	private Set<Player> players;
	
	@OneToOne
	private Player banker;
	
	@Enumerated(EnumType.STRING)
	private GameEnum.STATUS status;
	
	
	public Game() {
		cards.add(new Card(CardEnum.Rank.A, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.A, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.A, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.A, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.TWO, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.TWO, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.TWO, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.TWO, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.THREE, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.THREE, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.THREE, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.THREE, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.FOUR, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.FOUR, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.FOUR, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.FOUR, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.FIVE, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.FIVE, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.FIVE, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.FIVE, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.SIX, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.SIX, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.SIX, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.SIX, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.SEVEN, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.SEVEN, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.SEVEN, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.SEVEN, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.EIGHT, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.EIGHT, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.EIGHT, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.EIGHT, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.NINE, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.NINE, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.NINE, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.NINE, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.TEN, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.TEN, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.TEN, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.TEN, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.J, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.J, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.J, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.J, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.Q, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.Q, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.Q, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.Q, CardEnum.Suit.SPADES));
		
		cards.add(new Card(CardEnum.Rank.K, CardEnum.Suit.CLUBS));
		cards.add(new Card(CardEnum.Rank.K, CardEnum.Suit.DIAMONDS));
		cards.add(new Card(CardEnum.Rank.K, CardEnum.Suit.HEARTS));
		cards.add(new Card(CardEnum.Rank.K, CardEnum.Suit.SPADES));
		
		Collections.shuffle(cards);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Card> getCards() {
		return cards;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Player getBanker() {
		return banker;
	}

	public void setBanker(Player banker) {
		this.banker = banker;
	}

	public GameEnum.STATUS getStatus() {
		return status;
	}

	public void setStatus(GameEnum.STATUS status) {
		this.status = status;
	}
	
}
