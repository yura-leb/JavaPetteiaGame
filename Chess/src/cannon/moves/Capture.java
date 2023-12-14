package cannon.moves;

import game.core.GameOver;
import game.core.Piece;
import game.core.Square;

public class Capture extends SimpleMove {
	public Capture(Square[] squares) {
		super(squares);
	}
	
	@Override
	public void doMove() throws GameOver {
	}

	@Override
	public void undoMove() {
	}
	
	@Override
	public String toString() {
		return "" + piece + source + "x" + target;
	}
}
