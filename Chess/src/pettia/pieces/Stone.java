package pettia.pieces;

import game.core.*;
import pettia.moves.Capture;
import pettia.moves.SimpleMove;

import java.util.ArrayList;
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

	public List<Piece> collectCaptured(Square target) {
		Board board = target.getBoard();
		PieceColor myColor = this.color;

		int hor = target.h;
		int vert = target.v;

		int left = -1;
		int right = -1;
		int top = -1;
		int bottom = -1;
		List<Piece> captured = new ArrayList<>();

		for (int v = 0; v < board.nV; v++) {
			Square s = board.getSquare(v, hor);
			Piece p = s.getPiece();
			if (p != null && p.getColor() == myColor) {
				if (left == -1) {
					left = v;
				}
				right = v;
			}
		}

		if (left != -1) {
			for (int v = left; v < right; v++) {
				Square s = board.getSquare(v, hor);
				Piece p = s.getPiece();
				if (p != null && p.getColor() != myColor) {
					captured.add(p);
				}
			}
		}

		for (int h = 0; h < board.nH; h++) {
			Square s = board.getSquare(vert, h);
			Piece p = s.getPiece();
			if (p != null && p.getColor() == myColor) {
				if (bottom == -1) {
					bottom = h;
				}
				top = h;
			}
		}
		if (bottom != -1) {
			for (int h = bottom; h < top; h++) {
				Square s = board.getSquare(vert, h);
				Piece p = s.getPiece();
				if (p != null && p.getColor() != myColor) {
					captured.add(p);
				}
			}
		}

		return captured;
	}

	@Override
	public Move makeMove(Square... squares) {

		Square source = squares[0];
		Square target = squares[1];
		Piece piece = source.getPiece();
		piece.moveTo(target);
		// Поставим временно фигуру на клетку target и соберем клетки
		// на которых стоят окруженные фигуры противника.
		List<Piece> captured = collectCaptured(target);
		piece.moveTo(source);
		return new Capture(captured, squares); // Ход-захват.
	}

	@Override
	public String toString() {
		return "" + square;
	}
}