package camelot.players;

import game.core.Move;

public class Arthur extends CamelotPlayer {
    @Override
    public String getName() {
        return "Артур";
    }

    @Override
    public String getAuthorName() {
        return "Ковтун Данила";
    }

    @Override
    public String toString() {
        return getName();
    }

	@Override
	protected int getWeight(Move m) {
		return 0;
	}
}