package domain;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FractalImageModel implements java.io.Serializable
{
	
	
	private transient List list;
	private int mXPanels, mYPanels, mPanelSize;
	FractalImageModel(int pXPanels, int pYPanels, int pPanelSize)
	{
		mXPanels = pXPanels;
		mYPanels = pYPanels;
		mPanelSize = pPanelSize;
		list = new ArrayList(3*mXPanels * mYPanels);
	}

	void addFractalCode(FractalCode pFractalCode)
	{
		list.add(pFractalCode);
	}

	
	public int getCapacity()
	{
		return 3*mXPanels * mYPanels;
	}

	
	public int getSize()
	{
		return list.size();
	}

	
	public FractalCode getFractalCode(int i)
	{
		return (FractalCode)list.get(i);
	}

	
	public int getXPanels()
	{
		return mXPanels;
	}

	
	public int getYPanels()
	{
		return mYPanels;
	}

	
	public int getPanelSize()
	{
		return mPanelSize;
	}
        private static final String VERSION_1 = "Version 1";
	private void writeObject(ObjectOutputStream oos) throws IOException
	{
		oos.writeObject(VERSION_1);
		oos.defaultWriteObject();
		oos.writeInt(list.size());
		Iterator iter = list.iterator();
		while (iter.hasNext())
		{
			FractalCode code = (FractalCode)iter.next();
			oos.writeObject(code);			
		}
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		String version = (String) ois.readObject();
		if(!VERSION_1.equals(version))
			throw new InvalidObjectException("Version was " + version);
		ois.defaultReadObject();
		int size = ois.readInt();
		list = new ArrayList(size);
		for(int i = 0; i < size; i++)
		{
			FractalCode code = (FractalCode) ois.readObject();
			list.add(code);
		}
	}

}