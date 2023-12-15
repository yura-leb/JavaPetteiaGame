package pettia.players;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import game.core.*;
import game.core.moves.ICaptureMove;
import pettia.moves.Capture;

/**
 * TODO Лебединский Юрий
 */
public class Archimedes extends PettiaPlayer {
	private int MAX_MOVES = 80;
	final Comparator<? super Move> brain = (m1, m2) -> getMoveWeight(m2) - getMoveWeight(m1);


	private int getMoveWeight(Move m) {
		int weight = 0;
		if (m instanceof ICaptureMove) {
			weight += 5 * ((ICaptureMove) m).getCaptured().size() * ((ICaptureMove) m).getCaptured().size();
		}

		if (m instanceof Capture) {
			Square source = ((Capture) m).getSource();
			Square target = ((Capture) m).getTarget();
			Board board = target.getBoard();
			if (source.h != 0 && source.h != board.nH - 1 && source.v != 0 && source.v != board.nV - 1) {
				weight += (Math.abs(source.h - target.h) + Math.abs(source.v - target.v)) / 2;
			}

			if (source.h == 0 && source.v == 0) {
				weight -= 20;
			}
			else if (source.h == board.nH - 1 && source.v == board.nV - 1) {
				weight -= 20;
			}
			else if (source.h == 0 && source.v == board.nV - 1) {
				weight -= 20;
			}
			else if (source.h == board.nH - 1 && source.v == 0) {
				weight -= 20;
			}

			if (target.h == 0 && target.v == 0) {
				weight += 20;
			}
			else if (target.h == board.nH - 1 && target.v == board.nV - 1) {
				weight += 20;
			}
			else if (target.h == 0 && target.v == board.nV - 1) {
				weight += 20;
			}
			else if (target.h == board.nH - 1 && target.v == 0) {
				weight += 20;
			}

		}
		Piece piece = m.getPiece();

		try {
			m.doMove();
		} catch (GameOver e) {
			if (piece.isWhite() && e.result == GameResult.WHITE_WIN
			 || m.getPiece().isBlack() && e.result == GameResult.BLACK_WIN) {
				weight += 1000000;
			}
		}
		m.undoMove();
		return weight;
	}

	@Override
	public String getName() {
		return "Архимед";
	}

	@Override
	public String getAuthorName() {
		return "Лебединский Юрий";
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
