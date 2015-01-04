
package fractal;
import javax.swing.JFrame;
import gui.GuiController;
import domain.FractalCompressorFacade;
import gui.FractalGuiPanel;
public class FractalMain
{
        public static void main(String[] args)
	{
		JFrame frame = new JFrame("FINAL YEAR PROJECT--------------------------------------FRACTAL IMAGE COMPRESSION..");
		FractalGuiPanel panel = new FractalGuiPanel();
		frame.add(panel);
		FractalCompressorFacade facade = new FractalCompressorFacade();
		GuiController controller = new GuiController(facade, panel);
		frame.pack();
		frame.show();

	}
}
