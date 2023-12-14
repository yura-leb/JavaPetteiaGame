package cannon.ui;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import cannon.pieces.Pawn;
import chess.ui.images.ChessImages;
import game.core.Game;
import game.core.Piece;
import game.core.PieceColor;
import game.ui.AsiaBoard;
import game.ui.listeners.MovePieceListener;

public class CannonBoardPanel extends AsiaBoard {

	public CannonBoardPanel(Composite composite, Game game) {
		super(composite, game.board);

		listener = new MovePieceListener(this);
	}

	@Override
	public Image getPieceImage(Piece piece, PieceColor color) {
		return color == PieceColor.WHITE
				? (piece instanceof Pawn) ? ChessImages.imagePawnWhite : ChessImages.imageKingWhite
				: (piece instanceof Pawn) ? ChessImages.imagePawnBlack : ChessImages.imageKingBlack;
	}
}
