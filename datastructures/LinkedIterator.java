package datastructures;

import java.util.Iterator;

public class LinkedIterator<T> implements Iterator<T>{
    private Node<T> cursor;
    
    public LinkedIterator(Node<T> head)
    {
        this.cursor = head;
    }
    
    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public T next() {
        T data = cursor.data;
        cursor = cursor.next;
        return data;
    }
}

