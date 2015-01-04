package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import domain.CompressionResults;

public class FractalGuiPanel extends JPanel implements IGui, IControlPanelListener
{
	
	private ImageDisplayingPanel refImagePanel;
	private GuiController controller;
	private ControlPanel controlPanel;
	private ImageDisplayingPanel retrievedImagePanel;


	public FractalGuiPanel()
	{
		refImagePanel = new ImageDisplayingPanel();
		retrievedImagePanel = new ImageDisplayingPanel();
		Dimension d = new Dimension(300,300);
		refImagePanel.setPreferredSize(d);
		retrievedImagePanel.setPreferredSize(d);
		controlPanel = new ControlPanel(this);
		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.SOUTH);
				
		Box box = Box.createHorizontalBox();
		box.add(refImagePanel);
		box.add(retrievedImagePanel);
		this.add(box, BorderLayout.CENTER);		
	}
	
	public void setController(GuiController controller)
	{
		this.controller = controller;
	}
	
	public void displayReferenceImage(BufferedImage bi)
	{
		refImagePanel.setImage(bi);
		refImagePanel.repaint();
	}

	public void enableCompression()
	{
		controlPanel.enableCompression();
	}

	public void setCompressedFileName(String string)
	{
		controlPanel.setCompressedFileName(string);
	}

	public void displayWarning(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	public void displayCompressionResults(CompressionResults results)
	{
		controlPanel.updateCompressionProgress(0);
		controlPanel.displayCompressionResults(results);
	}

	public void enableDecompression()
	{
		controlPanel.enableDecompression();
	}

	public void displayUncompressionResults(BufferedImage image)
	{
		retrievedImagePanel.setImage(image);
		retrievedImagePanel.repaint();
	}

	public void fileToCompressSelected(File fileToCompress)
	{
		controller.openFile(fileToCompress.getAbsolutePath());		
	}

	public void compressPressed()
	{
		controller.compress(controlPanel.getCompressedFileName(), controlPanel.getCompressPanelSize());
	}

	public void uncompressPressed()
	{
		controller.uncompress(controlPanel.getUncompressFileName(), controlPanel.getUncompressPanelSize());		
	}

	public void updateCompressionProgress(double percent)
	{
		controlPanel.updateCompressionProgress(percent);
	}

}
