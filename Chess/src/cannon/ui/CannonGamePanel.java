package cannon.ui;

import org.eclipse.swt.widgets.Composite;

import breakthrough.BreakThrough;
import cannon.Cannon;
import game.ui.GamePanel;

/**
 * Панель для игры в Прорыв.
 * 
 */
public class CannonGamePanel extends GamePanel {
	public CannonGamePanel(Composite parent) {
		super(parent, new Cannon());
		
		insertSquares( new CannonBoardPanel(this, game) );
	}
}
