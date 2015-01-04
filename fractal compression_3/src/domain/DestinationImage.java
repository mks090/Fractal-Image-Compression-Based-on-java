package domain;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


public class DestinationImage extends MonochromeImage
{ 
    public BufferedImage s1;
    
	public DestinationImage(BufferedImage bufferedImage)
	{
		
		mWidth = bufferedImage.getWidth();
		mHeight = bufferedImage.getHeight();
                mRed = new short[mWidth][mHeight];
                mGreen= new short[mWidth][mHeight];
                mBlue= new short[mWidth][mHeight];
		for (int x = 0; x < mWidth; x++)
			for (int y = 0; y < mHeight; y++)
			{
				
                                        s1=bufferedImage;
					int red, green, blue;
					red = bufferedImage.getRaster().getSample(x, y, 0);
					green = bufferedImage.getRaster().getSample(x, y, 1);
					blue = bufferedImage.getRaster().getSample(x, y, 2);
                                        mRed[x][y]=(short)red;
                                        mGreen[x][y]=(short)green;
                                        mBlue[x][y]=(short)blue;
									
				

			}
	}
	public DestinationImage(int pWidth, int pHeight)
	{
		mWidth = pWidth;
		mHeight = pHeight;
		mRed = new short[mWidth][mHeight];
                mGreen = new short[mWidth][mHeight];
                mBlue = new short[mWidth][mHeight];

		for (int x = 0; x < mWidth; x++)
			for (int y = 0; y < mHeight; y++)
                        {
				mRed[x][y] = (short)0x0080;
                                mGreen[x][y] = (short)0x0080;
                                mBlue[x][y] = (short)0x0080;
                                
                        }
	}

	 
	ImagePanel getDestPanelAt(int i)
	{
		return mPanels[i];
	}
	int numberOfDestPanels()
	{
		return mXPanels * mYPanels;
	}
	public int getXPanels()
	{
		return mXPanels;
	}
	public int getYPanels()
	{
		return mYPanels;
	}
	void prepareDestRegions(int pPanelSize)
	{
		
		adjustImageSize(pPanelSize);
		mXPanels = mWidth / pPanelSize;
		mYPanels = mHeight / pPanelSize;
		int numRegions = mXPanels * mYPanels;
		mPanels = new ImagePanel[numRegions];
		int i = 0;
		for (int y = 0; y < mHeight; y += pPanelSize)
			for (int x = 0; x < mWidth; x += pPanelSize)
				mPanels[i++] = new ImagePanel(x, y, pPanelSize, this);
	}
	private void adjustImageSize(int pPanelSize)
	{
		int newWidth = mWidth;
		while ((newWidth % pPanelSize != 0) || (newWidth % 2 != 0))
			newWidth--;
		int newHeight = mHeight;
		while ((newHeight % pPanelSize != 0) || (newHeight % 2 != 0))
			newHeight--;
		if ((newWidth != mWidth) || (newHeight != mHeight))
		{
			short[][] newRed = new short[newWidth][newHeight];
                        short[][] newGreen = new short[newWidth][newHeight];
                        short[][] newBlue = new short[newWidth][newHeight];
			for (int x = 0; x < newWidth; x++)
				for (int y = 0; y < newHeight; y++)
                                {
					newRed[x][y] = mRed[x][y];
                                        newGreen[x][y] = mGreen[x][y];
                                        newBlue[x][y] = mBlue[x][y];
                                        
                                }
			mRed = newRed;
                        mGreen = newGreen;
                        mBlue = newBlue;
			mWidth = newWidth;
			mHeight = newHeight;
		}
	}

	public BufferedImage getImage()
	{
		BufferedImage bi = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = bi.getRaster();
		for (int x = 0; x < mWidth; x++)
			for (int y = 0; y < mHeight; y++)
                        {
				raster.setSample(x, y, 0, mRed[x][y]);
                                raster.setSample(x, y, 1, mGreen[x][y]);
                                raster.setSample(x, y, 2, mBlue[x][y]);
                        }

		return bi;
	}
}