package domain;
import gui.IFractalCompressorListener;
public class FractalCompressor implements Runnable
{

	private int mPanelSize;
	private ReferenceImage mRefImage;
	private DestinationImage mDestImage;
	private SFormList mSFormList = new SFormList();
	private FractalImageModel mFractalImageModel;
	private double mPercentDone;
	private boolean mCompressorRunning;
	private IFractalCompressorListener listener;
	
	public static final double GAMMA = 0.75;
        public static final double homo = 0.0;

	public FractalCompressor(DestinationImage dimage, int panelSize)
	{
		mDestImage = dimage;
		mPanelSize = panelSize;
	}
	

	public synchronized void setPanelSize(int pPanelSize)
	{
		mPanelSize = pPanelSize;
	}

	
	public synchronized void setDestImage(DestinationImage pDestImage)
	{
		mDestImage = pDestImage;
	}

	public void run()
	{
		System.out.println("Compressor thread started.");
		compress();
	}

	synchronized void compress()
	{
		int errorR, betaR,errorG, betaG,errorB, betaB, s, x, y, diffR,diffG,diffB;
		ImagePanel destRegion, refRegion, bestRegionR,bestRegionB,bestRegionG;
		SForm tSForm;
		Metric tMetric;
		int bestErrorR, bestBetaR, bestSFormR;
                int bestErrorG, bestBetaG, bestSFormG;
                int bestErrorB, bestBetaB, bestSFormB;
		mCompressorRunning = true;
		tMetric = new Metric(2, mDestImage.MAX_PIXEL_DEPTH);
		bestSFormR = bestBetaR = 0;
		bestRegionR = null;
                bestSFormG= bestBetaG = 0;
		bestRegionG = null;
                bestSFormB = bestBetaB = 0;
		bestRegionB = null;
		prepareImages();
		prepareSForms();
		int numDestRegions = mDestImage.numberOfDestPanels();
		int numRefRegions = mRefImage.numberOfRefPanels();
		mFractalImageModel = new FractalImageModel(mDestImage.getXPanels(),
				mDestImage.getYPanels(), mDestImage.getDestPanelAt(0).getPanelSize());

		int numSForms = mSFormList.getNumberOfSForms();
		SForm[] sforms = mSFormList.getSFormArray();
		
		for (int numDest = 0; numDest < numDestRegions; numDest++)
		{
			destRegion = mDestImage.getDestPanelAt(numDest);
			bestErrorR = 0x7FFFFFFF;
                        bestErrorG = 0x7FFFFFFF;
                        bestErrorB = 0x7FFFFFFF;
                  
			
			for (int numRef = 0; numRef < numRefRegions; numRef++)
			{
                                
				refRegion = mRefImage.getRefRegion(numRef);
				betaR = destRegion.getMeanR() - refRegion.getMeanR();
                                betaG = destRegion.getMeanG() - refRegion.getMeanG();
                                betaB = destRegion.getMeanB() - refRegion.getMeanB(); 
				for (s = 0; s < numSForms; s++)
				{
					errorR = 0;
                                        errorG = 0;
                                        errorB = 0;
					tSForm = sforms[s];
					for (x = 0; x < mPanelSize; x++)
					{
						for (y = 0; y < mPanelSize; y++)
						{
							diffR = destRegion.getPixelR(x, y)
									- tSForm.getTransformedPixelR(x, y, refRegion);
							errorR += tMetric.getDistance(Math.abs(diffR - betaR));
                                                        diffG = destRegion.getPixelG(x, y)
									- tSForm.getTransformedPixelG(x, y, refRegion);
							errorG += tMetric.getDistance(Math.abs(diffG - betaG));
                                                        diffB = destRegion.getPixelB(x, y)
									- tSForm.getTransformedPixelB(x, y, refRegion);
							errorB += tMetric.getDistance(Math.abs(diffB - betaB));
						}
					}
					if (errorR < bestErrorR)
					{
						bestErrorR = errorR;
						bestSFormR = s;
						bestBetaR = betaR;
						bestRegionR = refRegion;
					}
                                        if (errorG< bestErrorG)
					{
						bestErrorG = errorG;
						bestSFormG = s;
						bestBetaG = betaG;
						bestRegionG = refRegion;
					}
                                        if (errorB < bestErrorB)
					{
						bestErrorB = errorB;
						bestSFormB= s;
						bestBetaB = betaB;
						bestRegionB = refRegion;
					}
				} 
				
			} 

                       
                       mFractalImageModel.addFractalCode(new FractalCode(bestRegionR, bestSFormR, bestBetaR));
                       mFractalImageModel.addFractalCode(new FractalCode(bestRegionG, bestSFormG, bestBetaG));
                       mFractalImageModel.addFractalCode(new FractalCode(bestRegionB, bestSFormB, bestBetaB));
			mPercentDone = (100.0 * (numDest + 1)) / numDestRegions;
			if(listener != null)
                            	listener.updateProgress(mPercentDone);
		} 

		mCompressorRunning = false;
	}
	private synchronized void prepareImages()
	{
		mDestImage.prepareDestRegions(mPanelSize);
		mRefImage = new ReferenceImage(mDestImage, GAMMA);
		mRefImage.prepareRefRegions(mPanelSize);
	}

	public synchronized FractalImageModel getFractalImageModel()
	{
		return mFractalImageModel;
	}

	synchronized void prepareSForms()
	{
		mSFormList.prepareAllSForms(mPanelSize);
	}

	public double getPercentDone()
	{
		return mPercentDone;
	}

	public boolean compressorRunning()
	{
		return mCompressorRunning;
	}
	
	public void setListener(IFractalCompressorListener listener)
	{
		this.listener = listener;
	}

}