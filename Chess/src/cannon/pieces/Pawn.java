package cannon.pieces;

import breakthrough.moves.Capture;
import breakthrough.moves.SimpleMove;
import game.core.Move;
import game.core.Piece;
import game.core.PieceColor;
import game.core.Square;

/**
 * Правила:
 * https://www.chessprogramming.org/Breakthrough_(Game)
 */
public class Pawn extends Piece {
	public Pawn(Square square, PieceColor color) {
		super(square, color);
	}

	@Override
	public boolean isCorrectMove(Square... squares) {
		return true;
	}

	@Override
	public Move makeMove(Square... squares) {
		return new SimpleMove(squares);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
