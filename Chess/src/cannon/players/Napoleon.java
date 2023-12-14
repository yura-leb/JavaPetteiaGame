package cannon.players;

import game.core.Move;

/*
* TODO Егорова Елена
 */
public class Napoleon extends CannonPlayer {
	@Override
	public String getName() {
		return "Наполеон";
	}

	@Override
	public String getAuthorName() {
		return "Егорова Елена";
	}

	protected int getWeight(Move m2) {
		// TODO  
		return 0;
	}
}
