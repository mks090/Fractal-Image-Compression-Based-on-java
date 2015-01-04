package domain;
class FractalCode implements java.io.Serializable
{
	private short mX, mY;
	private byte mS;
	private short mBeta,mMean;
	private transient SFormList mSFormList;
        
        FractalCode(int pmean)
        {
            mMean=(short)pmean;
        }
	FractalCode(ImagePanel pReferenceRegion, int pS, int pBeta)
	{
		mX = (short)pReferenceRegion.getX();
		mY = (short)pReferenceRegion.getY();
		mS = (byte)pS;
		mBeta = (short)pBeta;
	}
        
	int getX()
	{
		return mX;
	}

	int getY()
	{
		return mY;
	}

	SForm getSForm()
	{
		if (mSFormList == null)
			mSFormList = new SFormList();
		return mSFormList.getSForm((int)mS);
	}

	int getBeta()
	{
		return mBeta;
	}
        int getMean()
        {
            return mMean;
        }
}