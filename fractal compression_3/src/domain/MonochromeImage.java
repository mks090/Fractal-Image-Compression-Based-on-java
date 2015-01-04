package domain;
abstract class MonochromeImage
{

	protected int mXPanels;
	protected int mYPanels;
	protected ImagePanel[] mPanels;
         protected short[][] mRed;
        protected short[][] mGreen;
        protected short[][] mBlue;
	protected int mWidth, mHeight;
	public static final int MAX_PIXEL_DEPTH = 256;

	
	int getWidth()
	{
		return mWidth;
	}

	
	int getHeight()
	{
		return mHeight;
	}
	short getPixelR(int pX, int pY)
	{
		return mRed[pX][pY];
	}
        short getPixelG(int pX, int pY)
	{
		return mGreen[pX][pY];
	}
        short getPixelB(int pX, int pY)
	{
		return mBlue[pX][pY];
	}
	void setPixelR(int pX, int pY, short pValue)
	{
		mRed[pX][pY] = pValue;
	}
void setPixelG(int pX, int pY, short pValue)
	{
		mGreen[pX][pY] = pValue;
	}
void setPixelB(int pX, int pY, short pValue)
	{
		mBlue[pX][pY] = pValue;
	}
	short[][] getPixelsR()
	{
		
		return mRed;
	}
        short[][] getPixelsG()
	{
		
		return mGreen;
	}
        short[][] getPixelsB()
	{
		
		return mBlue;
	}

}