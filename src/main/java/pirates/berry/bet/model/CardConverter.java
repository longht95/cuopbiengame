package pirates.berry.bet.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

import pirates.berry.bet.ENUM.CardEnum;

public class CardConverter implements AttributeConverter<Set<Card>, String> {

	@Override
	public String convertToDatabaseColumn(Set<Card> attribute) {
		// TODO Auto-generated method stub
		if (attribute.isEmpty()) return null;
		return attribute.stream().map(x -> x.getRank()+"-"+x.getSuit()).collect(Collectors.joining(","));
	}

	@Override
	public Set<Card> convertToEntityAttribute(String dbData) {
		Set<Card> cards = new HashSet<Card>();
		// TODO Auto-generated method stub
		if (dbData == null) return null;
		String list[] = dbData.split(",");
		for (String s : list) {
			Card c = new Card();
			String x[] = s.split("-");
			c.setRank(CardEnum.Rank.valueOf(x[0]));
			c.setSuit(CardEnum.Suit.valueOf(x[1]));
			cards.add(c);
		}
		return cards;
	}


}
