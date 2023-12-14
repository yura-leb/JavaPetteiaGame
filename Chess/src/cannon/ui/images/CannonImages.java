package cannon.ui.images;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class CannonImages {
	public static Image cannon;

	static {
		load( Display.getCurrent() );
	}

	private static void load(final Display display) {
		cannon   = new Image(display, CannonImages.class.getResourceAsStream("cannon.png"));
	}  
} 
