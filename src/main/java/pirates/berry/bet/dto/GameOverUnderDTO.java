package pirates.berry.bet.dto;

import pirates.berry.bet.ENUM.GameEnum;
import pirates.berry.bet.ENUM.GameEnum.TYPE_MESSAGE;

public class GameOverUnderDTO {
	private Long id;
	private int score;
	private GameEnum.TYPE_MESSAGE type;
	private Long totalBetOver;
	private Long totalBetUnder;
	private long countBetOver;
	private long countBetUnder;
	private long create;
	public long getCreate() {
		return create;
	}
	public void setCreate(long create) {
		this.create = create;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public GameEnum.TYPE_MESSAGE getType() {
		return type;
	}
	public void setType(GameEnum.TYPE_MESSAGE type) {
		this.type = type;
	}
	
	
	public Long getTotalBetOver() {
		return totalBetOver;
	}
	public void setTotalBetOver(Long totalBetOver) {
		this.totalBetOver = totalBetOver;
	}
	public Long getTotalBetUnder() {
		return totalBetUnder;
	}
	public void setTotalBetUnder(Long totalBetUnder) {
		this.totalBetUnder = totalBetUnder;
	}
	public long getCountBetOver() {
		return countBetOver;
	}
	public void setCountBetOver(long countBetOver) {
		this.countBetOver = countBetOver;
	}
	public long getCountBetUnder() {
		return countBetUnder;
	}
	public void setCountBetUnder(long countBetUnder) {
		this.countBetUnder = countBetUnder;
	}
	public GameOverUnderDTO(Long id, int score, TYPE_MESSAGE type) {
		this.id = id;
		this.score = score;
		this.type = type;
	}
	public GameOverUnderDTO() {
		
	}
}
