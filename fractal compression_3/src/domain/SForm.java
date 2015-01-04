package domain;

public class SForm
{

	private double mContractivity;
	private int mA, mB, mC, mD;
	private int[] mXCoords, mYCoords;
	private int mPanelSize;
	private boolean isPrepared = false;
	SForm(int pA, int pB, int pC, int pD, double pContractivity)
	{
		mA = pA;
		mB = pB;
		mC = pC;
		mD = pD;
		mContractivity = pContractivity;
	}

	
	int getA()
	{
		return mA;
	}

	
	int getB()
	{
		return mB;
	}

	
	int getC()
	{
		return mC;
	}

	
	int getD()
	{
		return mD;
	}
	double getContractivity()
	{
		return mContractivity;
	}

	short getTransformedPixelR(int pX, int pY, ImagePanel pRefRegion)
	{
		int index = pX + pY * mPanelSize;
		return pRefRegion.getPixelR(mXCoords[index], mYCoords[index]);
	}
short getTransformedPixelG(int pX, int pY, ImagePanel pRefRegion)
	{
		int index = pX + pY * mPanelSize;
		return pRefRegion.getPixelG(mXCoords[index], mYCoords[index]);
	}
short getTransformedPixelB(int pX, int pY, ImagePanel pRefRegion)
	{
		int index = pX + pY * mPanelSize;
		return pRefRegion.getPixelB(mXCoords[index], mYCoords[index]);
	}
	void prepare(int pPanelSize)
	{
		if(isPrepared)
			return;
		mXCoords = new int[pPanelSize * pPanelSize];
		mYCoords = new int[pPanelSize * pPanelSize];
		mPanelSize = pPanelSize;

		for (int x = 0; x < pPanelSize; x++)
		{
			for (int y = 0; y < pPanelSize; y++)
			{
				int index = x + y * pPanelSize;
				if (mA == 1)
					mXCoords[index] = x;
				else if (mA == -1)
					mXCoords[index] = pPanelSize - 1 - x;

				if (mB == 1)
					mXCoords[index] = y;
				else if (mB == -1)
					mXCoords[index] = pPanelSize - 1 - y;

				if (mC == 1)
					mYCoords[index] = x;
				else if (mC == -1)
					mYCoords[index] = pPanelSize - 1 - x;

				if (mD == 1)
					mYCoords[index] = y;
				else if (mD == -1)
					mYCoords[index] = pPanelSize - 1 - y;
			}
		}
		isPrepared = true;
	}

}