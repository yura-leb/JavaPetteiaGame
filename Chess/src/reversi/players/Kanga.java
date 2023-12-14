package reversi.players;

import game.core.IPieceProvider;
import game.core.Move;
import game.core.Square;
import game.core.moves.ICaptureMove;
import game.core.moves.IPutMove;

import java.util.Comparator;

/**
 * TODO Алиева Асиман реализовать принцип минимума:
 * https://othello.gomel.by/stepanov/princip-minimuma/
 */
public class Kanga extends ReversiPlayer {
	final Comparator<? super Move> brain
		= (m1, m2) -> getMoveWeight(m2) - getMoveWeight(m1);

	@Override
	public String getName() {
		return "Kanga";
	}

	@Override
	public String getAuthorName() {
		return "Алиева Асиман";
	}

	@Override
	public Comparator<? super Move> getComparator() {
		return brain;
	}

	public Kanga(IPieceProvider pieceProvider) {
		super(pieceProvider);
		this.pieceProvider = pieceProvider;
	}
	
	private int getMoveWeight(Move move) {
		return 0; 
	}
}