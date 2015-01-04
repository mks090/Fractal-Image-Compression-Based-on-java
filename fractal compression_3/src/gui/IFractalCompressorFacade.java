package gui;

import java.awt.image.BufferedImage;
import domain.CompressionResults;
import java.io.IOException;

public interface IFractalCompressorFacade
{

	public BufferedImage openFile(String referenceFileName) throws IOException;

	public CompressionResults compress(String compressedFileName, int panelSize, IFractalCompressorListener listener);

	public void uncompress(String compressedFileName, int panelSize, IUncompressionListener listener) throws IOException;


}
