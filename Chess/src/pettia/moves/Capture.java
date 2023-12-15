package pettia.moves;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
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
	private final List<Square> capturedSquares;

	public Capture(List<Piece> captured, Square[] squares) {
		super(null, squares);
		this.captured = captured;
		this.capturedSquares = new ArrayList<>();
		for (Piece c : captured) {
			capturedSquares.add(c.square);
		}
//		System.out.println(this.captured);

	}

	@Override
	public List<Square> getCaptured() {
		return capturedSquares;
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

        for (Piece c : captured) {
            System.out.println(c);
            c.remove();
        }
		super.doMove();

		checkGameFinished(target.getBoard());
	}
	
	@Override
	public void undoMove() {
		super.undoMove();

		for (int i = 0; i < capturedSquares.size(); i++) {
			Square square = capturedSquares.get(i);
			Piece capturedPiece = captured.get(i);
			if (square != null && capturedPiece != null) {
				square.setPiece(capturedPiece);
			}
		}
	}

	public String toString() {
		return "" + piece + source + "x" + target;
	}
}
