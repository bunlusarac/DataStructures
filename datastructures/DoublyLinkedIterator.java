package datastructures;

import java.util.Iterator;

public class DoublyLinkedIterator<T> implements Iterator<T>{
    private DoubleNode<T> cursor;
    
    public DoublyLinkedIterator(DoubleNode<T> head)
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