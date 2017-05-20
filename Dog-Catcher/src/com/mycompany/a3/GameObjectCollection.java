package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection {
	@SuppressWarnings("rawtypes")
	private Vector theCollection;

    @SuppressWarnings("rawtypes")
	public GameObjectCollection(){
    	theCollection = new Vector();
    }

    @SuppressWarnings("unchecked")
	public void add(Object newObject) { // maybe replace Object with GameObject
    	theCollection.addElement(newObject);
    }
    public IIterator getIterator() {
        return new GameObjectIterator();
    }

    //	used space collection from csc133 lecture slides as reference
    private class GameObjectIterator implements IIterator {
        private int currElementIndex;

        public GameObjectIterator() {
        	currElementIndex = -1;
        }
        
        
        public void scoopSetUp() {
        	currElementIndex = theCollection.size();
        }
        
        // i like to go through the objects right to left for scooping
        public Object scoopGetNext() {
        	currElementIndex -- ;
            return(theCollection.elementAt(currElementIndex));
        }
        
        public boolean scoopHasNext() {
            if (theCollection.size() <= 0){
                return false;
            }
            if(currElementIndex <= 0) {
            	return false;
            }
            return true;
        }
        
        public boolean hasNext() {
            if (theCollection.size() <= 0){
                return false;
            }
            if (currElementIndex == theCollection.size() - 1){
                return false;
            }
            return true;
        }
        public Object getNext() {
        	currElementIndex ++ ;
            return(theCollection.elementAt(currElementIndex));
        }
        public void remove() {
        	theCollection.remove(currElementIndex);
        }

        public int getIndex(){
            return currElementIndex;
        }

        public Object objectAt(int i){
            return theCollection.get(i);
        }
    }

}
