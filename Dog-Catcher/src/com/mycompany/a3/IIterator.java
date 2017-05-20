package com.mycompany.a3;

public interface IIterator {
	public boolean hasNext();
	public Object getNext();
	
	public void remove();
	public int getIndex();
	public Object objectAt(int i);
	
	public void scoopSetUp();
	public Object scoopGetNext();
	public boolean scoopHasNext();

}
