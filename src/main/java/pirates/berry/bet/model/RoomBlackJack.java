package pirates.berry.bet.model;

import java.util.HashSet;
import java.util.Set;

public class RoomBlackJack {
	private int roomId;
	private Player banker;
	private Set<Player> players = new HashSet<Player>();
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
	public RoomBlackJack() {
		
	}
	public RoomBlackJack(int roomId, Player banker, Set<Player> players) {
		this.roomId = roomId;
		this.banker = banker;
		this.players = players;
	}
	
}
