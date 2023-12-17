package pettia.players;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import game.core.*;
import pettia.moves.Capture;
import game.core.moves.ICaptureMove;

/**
 * TODO Веремчук Алексей
 */
public class Pythagoras extends PettiaPlayer {
	private int MAX_MOVES = 80;
	final Comparator<? super Move> brain = (m1, m2) -> getMoveWeight(m2) - getMoveWeight(m1);

	private int getMoveWeight(Move m) {

		int weight = 0;

		if (m instanceof ICaptureMove) {
			weight += 5 * ((ICaptureMove) m).getCaptured().size() * ((ICaptureMove) m).getCaptured().size();
		}

		if (m instanceof Capture){
			Capture capture = (Capture) m;
			Square source = capture.getSource();
			Square target = capture.getTarget();
			Board board = target.getBoard();

			boolean isSourceCorner = isCornerSquare(source, board);
			boolean isTargetCorner = isCornerSquare(target, board);

			if (!isSourceCorner && !isTargetCorner) {
				int distance = calculateManhattanDistance(source, target);
				weight += 10 - distance; // Decrease weight as distance increases
			}

			if (isSourceCorner) {
				weight -= 5;
			}

			if (isTargetCorner) {
				weight += 5;
			}
		}

		Piece piece = m.getPiece();

		try {
			m.doMove();
		} catch (GameOver e) {
			if (isWinningMove(piece, e)) {
				weight += 10000;
			}
		} finally {
			m.undoMove();
		}

		return weight;
	}

	private boolean isCornerSquare(Square square, Board board) {
		int h = square.h;
		int v = square.v;
		int nH = board.nH;
		int nV = board.nV;
		return (h == 0 || h == nH - 1) && (v == 0 || v == nV - 1);
	}

	private int calculateManhattanDistance(Square source, Square target) {
		int dx = Math.abs(source.h - target.h);
		int dy = Math.abs(source.v - target.v);
		return dx + dy;
	}

	private boolean isWinningMove(Piece piece, GameOver gameOver) {
		return (piece.isWhite() && gameOver.result == GameResult.WHITE_WIN) ||
				(piece.isBlack() && gameOver.result == GameResult.BLACK_WIN);
	}




	@Override
	public String getName() {
		return "Пифагор";
	}

	@Override
	public String getAuthorName() {
		return "Веремчук Алексей";
	}

	@Override
	public void doMove(Board board, PieceColor color) throws GameOver {
		List<Move> correctMoves = getCorrectMoves(board, color);

		if (correctMoves.isEmpty())
			throw new GameOver(GameResult.DRAWN);

		Collections.shuffle(correctMoves);

		correctMoves.sort(brain);
		Move bestMove;
		bestMove = correctMoves.get(0);

		try {
			bestMove.doMove();
		} catch (GameOver e) {

			// Сохраняем в истории игры последний сделанный ход
			// и результат игры.
			board.history.addMove(bestMove);
			board.history.setResult(e.result);

			// Просим обозревателей доски показать
			// положение на доске, сделанный ход и
			// результат игры.
			board.setBoardChanged();

			throw new GameOver(GameResult.DRAWN);
		}

		// Сохраняем ход в истории игры.
		board.history.addMove(bestMove);

		// Просим обозревателей доски показать
		// положение на доске, сделанный ход и
		// результат игры.
		board.setBoardChanged();

		// Для отладки ограничим количество ходов в игре.
		// После этого результат игры ничья.
		if (board.history.getMoves().size() > MAX_MOVES ) {
			// Сохраняем в истории игры последний сделанный ход
			// и результат игры.
			board.history.setResult(GameResult.DRAWN);

			// Сообщаем что игра закончилась ничьей.
			throw new GameOver(GameResult.DRAWN);
		}

	}

	@Override
	protected Comparator<? super Move> getComparator() {
		return brain;
	}
}
