package camelot.players;

import game.core.Move;

public class Lancelot extends CamelotPlayer {
    @Override
    public String getName() {
        return "Ланселот";
    }

    @Override
    public String getAuthorName() {
        return "Сучков Александр";
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