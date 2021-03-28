package pirates.berry.bet.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import pirates.berry.bet.model.Game;

@Service
public interface GameService {
	public void save(Game game);
	public Optional<Game> getById(Long id);
}
