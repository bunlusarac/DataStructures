package datastructures;

import java.util.Iterator;

public class ArrayListIterator<T> implements Iterator<T>{
	private Object[] data;
	private int size;
	private int index;
	
	public ArrayListIterator(Object[] data, int size){
		this.data = data;
		this.size = size;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}
	
	@Override
	public T next() {
		@SuppressWarnings("unchecked")
		T data = (T) this.data[index++];
		return data;
	}
}
