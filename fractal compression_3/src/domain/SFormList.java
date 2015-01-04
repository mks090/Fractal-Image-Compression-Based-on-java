package domain;
public class SFormList
{
        private int mNumberOfSForms;
	private SForm[] mSFormList;

	 
	SFormList()
	{
		setDefaultList();
	}

	
	 
	private void setDefaultList()
	{
		mNumberOfSForms = 8;
		mSFormList = new SForm[8];
                mSFormList[0] = new SForm(1, 0, 0, 1, 0.5);
		mSFormList[1] = new SForm(-1, 0, 0, 1, 0.5);
		mSFormList[2] = new SForm(1, 0, 0, -1, 0.5);
		mSFormList[3] = new SForm(-1, 0, 0, -1, 0.5);
		mSFormList[4] = new SForm(0, 1, 1, 0, 0.5);
		mSFormList[5] = new SForm(0, -1, 1, 0, 0.5);
		mSFormList[6] = new SForm(0, 1, -1, 0, 0.5);
		mSFormList[7] = new SForm(0, -1, -1, 0, 0.5);
	}
        int getNumberOfSForms()
	{
		return mNumberOfSForms;
	}
         SForm getSForm(int i)
	{
		return mSFormList[i];
	}
	
	public void prepareAllSForms(int panelSize)
	{
		for (int i = 0; i < mSFormList.length; i++)
		{
			SForm sform = mSFormList[i];
			sform.prepare(panelSize);
		}
	}

	public SForm[] getSFormArray()
	{
		return mSFormList;
	}
}