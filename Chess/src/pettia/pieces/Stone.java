package pettia.pieces;

import game.core.Move;
import game.core.Piece;
import game.core.PieceColor;
import game.core.Square;
import pettia.moves.SimpleMove;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stone extends Piece {
	public Stone(Square square, PieceColor color) {
		super(square, color);
	}

	@Override
	public boolean isCorrectMove(Square... squares) {
		Square source = this.square;

		List<Square> moves = Arrays.stream(squares)
				.filter(Square::isEmpty)
				.filter(square -> square.isHorizontal(source) || square.isVertical(source))
				.collect(Collectors.toList());


		return !moves.isEmpty();
	}

	@Override
	public Move makeMove(Square... squares) {
		return new SimpleMove(this, squares);
	}

	@Override
	public String toString() {
		return "" + square;
	}
}