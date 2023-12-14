package pettia.moves;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import game.core.*;
import game.core.moves.ICaptureMove;

public class Capture extends SimpleMove implements ICaptureMove {
	/**
	 * Захваченные фигуры противника.
	 */
	private final List<Piece> captured;

	public Capture(List<Piece> captured, Square[] squares) {
		super(null, squares);
		this.captured = captured;
	}

	@Override
	public List<Square> getCaptured() {
		return captured
			.stream()
			.map(p -> p.square)
			.collect( toList() );
	}

	public List<Piece> getCapturedPieces() {
		return captured;
	}

	private void checkGameFinished(Board board) throws GameOver {
		List<Piece> blackPieces = board.getPieces(PieceColor.BLACK);
		List<Piece> whitePieces = board.getPieces(PieceColor.WHITE);

		if (blackPieces.size() <= 1) {
			throw new GameOver(GameResult.BLACK_WIN);
		}

		if (whitePieces.size() <= 1) {
			throw new GameOver(GameResult.WHITE_WIN);
		}
	}

	@Override
	public void doMove() throws GameOver {
//		captured.remove();
		super.doMove();
		checkGameFinished(target.getBoard());
	}
	
	@Override
	public void undoMove() {
		super.undoMove();
	}

	public String toString() {
		return "" + piece + source + "x" + target;
	}
}
