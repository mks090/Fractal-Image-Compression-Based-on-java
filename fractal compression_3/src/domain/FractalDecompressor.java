package domain;
public class FractalDecompressor
{

	public void getNextImage(FractalImageModel pImageModel, DestinationImage pDestImage,
			int pPanelSize)
	{
		
		ReferenceImage refImage = new ReferenceImage(pDestImage, FractalCompressor.GAMMA);
		refImage.prepareRefRegions(pPanelSize);
		pDestImage.prepareDestRegions(pPanelSize);
		int totalDestRegions = pDestImage.numberOfDestPanels();
		ImagePanel destRegion, refRegionR,refRegionG,refRegionB;
		FractalCode fractalCodeR,fractalCodeG,fractalCodeB;
		SForm sFormR,sFormG,sFormB;
		int xR, yR;
                int xG, yG;
                int xB, yB;
		double scaler = (1.0 * pPanelSize) / pImageModel.getPanelSize();
                int j=0;
		for (int i = 0; i < totalDestRegions; i++)
		{
			destRegion = pDestImage.getDestPanelAt(i);
			fractalCodeR = pImageModel.getFractalCode(j);
                      
			xR = (int)(scaler * fractalCodeR.getX());
			yR = (int)(scaler * fractalCodeR.getY());
			if (xR >= refImage.getXPanels())
				xR = refImage.getXPanels() - 1;
			if (yR >= refImage.getYPanels())
				yR = refImage.getYPanels() - 1;
			refRegionR = refImage.getRefRegion(xR + yR * refImage.getXPanels());
			sFormR = fractalCodeR.getSForm();
			sFormR.prepare(pPanelSize);
			short valR;
			for (xR = 0; xR < pPanelSize; xR++)
				for (yR = 0; yR < pPanelSize; yR++)
				{
					valR = (short)(sFormR.getTransformedPixelR(xR, yR, refRegionR) + fractalCodeR
							.getBeta());
					if (valR < 0)
						destRegion.setPixelR(xR, yR, (short)0);
					else if (valR > 255)
						destRegion.setPixelR(xR, yR, (short)0xFF);
					else
						destRegion.setPixelR(xR, yR, valR);
                                }
                        fractalCodeG = pImageModel.getFractalCode(j+1);
                      
			xG = (int)(scaler * fractalCodeG.getX());
			yG = (int)(scaler * fractalCodeG.getY());
			if (xG >= refImage.getXPanels())
				xG = refImage.getXPanels() - 1;
			if (yG >= refImage.getYPanels())
				yG = refImage.getYPanels() - 1;
			refRegionG = refImage.getRefRegion(xG + yG * refImage.getXPanels());
			sFormG= fractalCodeG.getSForm();
			sFormG.prepare(pPanelSize);
			short valG;
			for (xG= 0; xG < pPanelSize; xG++)
				for (yG= 0; yG < pPanelSize; yG++)
				{
					valG = (short)(sFormR.getTransformedPixelG(xG, yG, refRegionG) + fractalCodeG
							.getBeta());
					if (valG < 0)
						destRegion.setPixelG(xG, yG, (short)0);
					else if (valG> 255)
						destRegion.setPixelG(xG, yG, (short)0xFF);
					else
						destRegion.setPixelG(xG, yG, valG);
                                }
                        fractalCodeB = pImageModel.getFractalCode(j+2);
                      
			xB = (int)(scaler * fractalCodeB.getX());
			yB = (int)(scaler * fractalCodeB.getY());
			if (xB >= refImage.getXPanels())
				xB = refImage.getXPanels() - 1;
			if (yB >= refImage.getYPanels())
				yB = refImage.getYPanels() - 1;
			refRegionB = refImage.getRefRegion(xB + yB * refImage.getXPanels());
			sFormB = fractalCodeB.getSForm();
			sFormB.prepare(pPanelSize);
			short valB;
			for (xB= 0; xB < pPanelSize; xB++)
				for (yB = 0; yB< pPanelSize; yB++)
				{
					valB = (short)(sFormB.getTransformedPixelB(xB, yB, refRegionB) + fractalCodeB
							.getBeta());
					if (valB < 0)
						destRegion.setPixelB(xB, yB, (short)0);
					else if (valB > 255)
						destRegion.setPixelB(xB, yB, (short)0xFF);
					else
						destRegion.setPixelB(xB, yB, valB);
                                }
                        j=j+3;
        
}
}
}

	

