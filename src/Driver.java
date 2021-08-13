import java.awt.*;
import appGUI.OpenFrame;

public class Driver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// opening frame and has the "connections" to all the other frames and query classes
					OpenFrame frame = new OpenFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


