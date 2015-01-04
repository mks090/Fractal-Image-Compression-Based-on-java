
package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import domain.CompressionResults;




public class GuiController
{
	private IFractalCompressorFacade compressor;
	private IGui gui;
	
	public GuiController(IFractalCompressorFacade compressor, IGui gui)
	{
		this.compressor = compressor;
		this.gui = gui;	
		gui.setController(this);
	}

	public void openFile(String referenceFileName)
	{
		BufferedImage bi;
		try
		{
			bi = compressor.openFile(referenceFileName);
		}
		catch (IOException e)
		{
			gui.displayWarning("The file " + referenceFileName + "could not be opened. The warning is: " + e.getMessage());
			return;
		}
		gui.displayReferenceImage(bi);
		gui.enableCompression();
		String compressedFileName = getDefaultCompressedFileName(referenceFileName);
		gui.setCompressedFileName(compressedFileName);
	}

	private String getDefaultCompressedFileName(String referenceFileName)
	{
		String[] tokens = referenceFileName.split("[.]");
		String newName = tokens[0] + ".fcp";
		return newName;
	}

	public void compress(final String compressedFileName, final int panelSize)
	{
		if(panelSize <= 0) 
		{
			gui.displayWarning("Panel size must be 1 or greater.");
			return;
		}
		final IFractalCompressorListener listener = new IFractalCompressorListener()
		{
			public void updateProgress(double percent)
			{
				gui.updateCompressionProgress(percent);
			}
			
		};
		SwingWorker worker = new SwingWorker("Compressor thread")
		{
			private CompressionResults results;
			public Object construct()
			{
				results = compressor.compress(compressedFileName, panelSize, listener);
				return results;
			}			
			public void finished()
			{
				gui.displayCompressionResults(results);
				gui.enableDecompression();
			}
		};
		worker.start();
	}

	public void uncompress(final String compressedFileName, final int panelSize)
	{
		if(panelSize <= 0) 
		{
			gui.displayWarning("Panel size must be 1 or greater.");
			return;
		}
		final IUncompressionListener listener = new IUncompressionListener()
		{
			public void imageReady(BufferedImage image)
			{
				gui.displayUncompressionResults(image);
			}
			
		};
		
		SwingWorker worker = new SwingWorker("Decompression Thread")
		{
			public Object construct()
			{
				try
				{
					compressor.uncompress(compressedFileName, panelSize, listener);
				}
				catch (IOException e)
				{
					gui.displayWarning("Could not open/read the compressed file: " + compressedFileName);
				}
				return null;
			}
		};
		worker.start();
	}

}
