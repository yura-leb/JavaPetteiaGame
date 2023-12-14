package cannon.players;

import game.core.Move;

/**
 * TODO Тихонов Максим 
 */
public class Kutuzov extends CannonPlayer {
	@Override
	public String getName() {
		return "Кутузов";
	}

	@Override
	public String getAuthorName() {
		return "Тихонов Максим";
	}

	protected int getWeight(Move m2) {
		// TODO  
		return 0;
	}
}
