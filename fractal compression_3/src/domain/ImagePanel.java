package domain;
public class ImagePanel
{

	private int xOrigin, yOrigin; 
       	private int mMeanR, mPanelSize,mMeanG,mMeanB;
	private MonochromeImage mImage;
	private short[][] originalPixelsR;
        private short[][] originalPixelsB;
        private short[][] originalPixelsG;
	private short[][] panelPixels2DR;
        private short[][] panelPixels2DB;
        private short[][] panelPixels2DG;
        int sumR = 0;
                int sumG = 0;
                int sumB = 0;
	ImagePanel(int pX, int pY, int pPanelSize, MonochromeImage pImage)
	{
		xOrigin = pX;
		yOrigin = pY;
		mPanelSize = pPanelSize;
		mImage = pImage;
		originalPixelsR = pImage.getPixelsR();
                originalPixelsB = pImage.getPixelsB();
                originalPixelsG= pImage.getPixelsG();
		//panelPixels = new short[mPanelSize*mPanelSize];
		panelPixels2DR = new short[mPanelSize][mPanelSize];
                panelPixels2DG = new short[mPanelSize][mPanelSize];
                panelPixels2DB = new short[mPanelSize][mPanelSize];

		
		
                
		//int i = 0;
		for (int x = 0; x < mPanelSize; x++)
		{
			for (int y = 0; y < mPanelSize; y++)
			{
				
				sumR += originalPixelsR[x + xOrigin][y + yOrigin];
				//panelPixels[i++] = originalPixels[x + xOrigin][y + yOrigin];
				panelPixels2DR[x][y] = originalPixelsR[x + xOrigin][y + yOrigin];
                                sumG += originalPixelsG[x + xOrigin][y + yOrigin];
				//panelPixels[i++] = originalPixels[x + xOrigin][y + yOrigin];
				panelPixels2DG[x][y] = originalPixelsG[x + xOrigin][y + yOrigin];
                                sumB += originalPixelsB[x + xOrigin][y + yOrigin];
				//panelPixels[i++] = originalPixels[x + xOrigin][y + yOrigin];
				panelPixels2DB[x][y] = originalPixelsB[x + xOrigin][y + yOrigin];
			}
		}
		mMeanR = (short)(sumR / (mPanelSize * mPanelSize));
                mMeanG = (short)(sumG / (mPanelSize * mPanelSize));
                mMeanB = (short)(sumB / (mPanelSize * mPanelSize));
	}
          
	
	int getX()
	{
		return xOrigin;
	}

	
	int getY()
	{
		return yOrigin;
	}

	
	int getPanelSize()
	{
		return mPanelSize;
	}

        int getSumR()
	{
		return sumR;
	}
        int getSumG()
	{
		return sumG;
	}
        int getSumB()
	{
		return sumB;
	}
	
	int getMeanR()
	{
		return mMeanR;
	}
        int getMeanG()
	{
		return mMeanG;
	}
        int getMeanB()
	{
		return mMeanB;
	}
	public short getPixelR(int pX, int pY)
	{
		return panelPixels2DR[pX][pY];
	}
        public short getPixelG(int pX, int pY)
	{
		return panelPixels2DG[pX][pY];
	}
        public short getPixelB(int pX, int pY)
	{
		return panelPixels2DB[pX][pY];
	}
	void setPixelR(int pX, int pY, short pValue)
	{
		mImage.setPixelR(pX + xOrigin, pY + yOrigin, pValue);
	}
        void setPixelG(int pX, int pY, short pValue)
	{
		mImage.setPixelG(pX + xOrigin, pY + yOrigin, pValue);
	}
        void setPixelB(int pX, int pY, short pValue)
	{
		mImage.setPixelB(pX + xOrigin, pY + yOrigin, pValue);
	}
        short[][] getPanelPixelsR()
	{
		
		return panelPixels2DR;
	}
        short[][] getPanelPixelsG()
	{
		
		return panelPixels2DG;
	}
        short[][] getPanelPixelsB()
	{
		
		return panelPixels2DB;
	}
}