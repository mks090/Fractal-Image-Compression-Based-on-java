package domain;
class ReferenceImage extends MonochromeImage
{
	ReferenceImage(DestinationImage pDestImage,double pGamma)
	{
		mWidth = pDestImage.getWidth() / 2;
		mHeight = pDestImage.getHeight() / 2;
		mRed = new short[mWidth][mHeight];
                mGreen = new short[mWidth][mHeight];
                mBlue = new short[mWidth][mHeight];
		int valR, xx, yy,valG,valB;
		for (int x = 0; x < mWidth; x++)
		{
			for (int y = 0; y < mHeight; y++)
			{
				xx = 2 * x;
				yy = 2 * y;
				valR = pDestImage.getPixelR(xx, yy) + pDestImage.getPixelR(xx + 1, yy)
						+ pDestImage.getPixelR(xx, yy + 1) + pDestImage.getPixelR(xx + 1, yy + 1);
				
				mRed[x][y] = (short)((valR * pGamma / 4));
                                valG = pDestImage.getPixelG(xx, yy) + pDestImage.getPixelG(xx + 1, yy)
						+ pDestImage.getPixelG(xx, yy + 1) + pDestImage.getPixelG(xx + 1, yy + 1);
				
				mGreen[x][y] = (short)((valG * pGamma / 4));
                                valB = pDestImage.getPixelB(xx, yy) + pDestImage.getPixelB(xx + 1, yy)
						+ pDestImage.getPixelB(xx, yy + 1) + pDestImage.getPixelB(xx + 1, yy + 1);
				
				mBlue[x][y] = (short)((valB * pGamma / 4));
			}
		}
	}

	
	int numberOfRefPanels()
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

	void prepareRefRegions(int pPanelSize)
	{
		mXPanels = mWidth - (pPanelSize - 1);
		mYPanels = mHeight - (pPanelSize - 1);
		int numRegions = mXPanels * mYPanels;
		mPanels = new ImagePanel[numRegions];

		
		int i = 0;
		for (int y = 0; y < mYPanels; y++)
			for (int x = 0; x < mXPanels; x++)
				mPanels[i++] = new ImagePanel(x, y, pPanelSize, this);
	}


	ImagePanel getRefRegion(int i)
	{
		return mPanels[i];
	}

}