package domain;
public class Metric
{

	private int[] mMetricTable;
	Metric(int pL, int pMaxDiff)
	{
		mMetricTable = new int[(int)(1.51 * pMaxDiff)];
		int top = mMetricTable.length - 1;

		switch (pL)
		{
		case 1:
			for (int i = 0; i < top; i++)
				mMetricTable[i] = i;
			break;
		case 2:
		default:
			for (int i = 0; i < top; i++)
				mMetricTable[i] = i * i;
		}
	}

	int getDistance(int pDifference)
	{
		return mMetricTable[pDifference];
	}
}