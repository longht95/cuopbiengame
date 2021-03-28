package pirates.berry.bet.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pirates.berry.bet.model.Game;
import pirates.berry.bet.repository.GameRepository;
import pirates.berry.bet.service.GameService;

@Repository
public class GameServiceImpl implements GameService{

	@Autowired
	GameRepository gameRepository;
	@Override
	public void save(Game game) {
		// TODO Auto-generated method stub
		gameRepository.save(game);
	}

	@Override
	public Optional<Game> getById(Long id) {
		// TODO Auto-generated method stub
		return gameRepository.findById(id);
	}
	

}
