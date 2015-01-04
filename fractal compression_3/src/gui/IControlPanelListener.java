package gui;
import java.io.File;
public interface IControlPanelListener
{

	public void fileToCompressSelected(File fileToCompress);

	public void compressPressed();

	public void uncompressPressed();

}
