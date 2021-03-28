package pirates.berry.bet.repository;

import org.springframework.data.repository.CrudRepository;

import pirates.berry.bet.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
	
}
