package datastructures.queues;

import datastructures.heaps.MaxHeap;

/**
 * Priority queue implementation using binary max heaps.
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the queue,
 * must be subclass of {@code java.lang.Number}.
 */
public class PriorityQueue<T extends Number> {
	private MaxHeap<T> data;
	
	/**
	 * Initialize a priority queue.
	 */
	public PriorityQueue() {
		this.data = new MaxHeap<T>();
	}
	
	/**
	 * Insert an element to the queue considering it's priority.
	 * @param data data to be enqueued
	 */
	public void enqueue(T data) { this.data.insert(data); }
	
	/**
	 * Remove an element from the queue with the highest priority.
	 *<p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @param data data to be dequeued
	 */
	public T dequeue() { return this.data.extractMax(); }
	
	/**
	 * Return the element with highest priority.
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element with highest priority/at the front.
	 */
	public T peek() { return this.data.peek(); }
	
	/**
	 * Returns size of the queue. (number of elements in the queue)
	 * @return size of the queue/number of elements
	 */
	public int size() { return this.data.size(); }
	
    /**
     * Delete all elements and clear the queue.
     * <p>This does not free memory allocated by the heap immediately 
     * but removes the reference to the heap, which will make it viable
     * for garbage collection so that it's memory will eventually get
     * freed.</p>
     */
	public void clear() { this.data = new MaxHeap<T>(); }
	
	/**
	 * Returns a boolean value indicating emptiness of the queue.
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() { return size() == 0; }
}
