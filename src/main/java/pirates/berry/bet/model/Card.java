package pirates.berry.bet.model;

import pirates.berry.bet.ENUM.CardEnum;
import pirates.berry.bet.ENUM.CardEnum.Rank;
import pirates.berry.bet.ENUM.CardEnum.Suit;

public class Card {
	private CardEnum.Rank rank;
	private CardEnum.Suit suit;
	public CardEnum.Rank getRank() {
		return rank;
	}
	public void setRank(CardEnum.Rank rank) {
		this.rank = rank;
	}
	public CardEnum.Suit getSuit() {
		return suit;
	}
	public void setSuit(CardEnum.Suit suit) {
		this.suit = suit;
	}
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	public Card() {
	}
	
}
