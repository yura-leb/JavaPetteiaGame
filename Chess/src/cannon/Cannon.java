package cannon;

import static game.core.PieceColor.WHITE;

import cannon.pieces.King;
import cannon.pieces.Pawn;
import cannon.players.Kutuzov;
import cannon.players.Napoleon;
import game.core.Game;
import game.core.PieceColor;
import game.players.IPlayer;
import game.players.Neznaika;

/**
 * TODO Тихонов Максим и Егорова Елена
 */
public class Cannon extends Game {
	private static final int BOARD_SIZE = 10;

	static {
		addPlayer(Cannon.class, IPlayer.HOMO_SAPIENCE);
		addPlayer(Cannon.class, new Neznaika());
		addPlayer(Cannon.class, new Kutuzov());
		addPlayer(Cannon.class, new Napoleon());
	}

	/**
	 * Расстановка фигур пешек в начальную позицию.
	 */
	public Cannon() {
		initBoardDefault();

		board.setWhitePlayer(IPlayer.HOMO_SAPIENCE);
		board.setBlackPlayer(new Neznaika());
	}

	@Override
	public void initBoardDefault() {
		super.initBoard(10, 10);

		putPieces(WHITE);
		putPieces(PieceColor.BLACK);
	}

	private void putPieces(PieceColor color) {
		int d = (color == WHITE) ? -1 : 1;
		int baseV = (color == WHITE) ? 0 : 1;
		int baseH = (color == WHITE) ? BOARD_SIZE - 2 : 1;

		for (int v = 0; v < board.nV; v += 2)
			for (int k = 0; k < 3; k++)
				new Pawn(board.getSquare(baseV + v, baseH + d * k), color);

		if (color == WHITE)
			new King(board.getSquare(0, BOARD_SIZE - 1), color);
		else
			new King(board.getSquare(BOARD_SIZE - 1, 0), color);
	}
}
