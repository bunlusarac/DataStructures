package datastructures;

/**
 * Binary tree node containing two links to
 * left and right subtree to implement binary
 * trees.
 * @author Baris Unlusarac
 *
 * @param <T> type of data stored in the node
 */
public class BinaryTreeNode<T> {
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	/**
	 * Initialize a node with given data and
	 * assign {@code left} and {@code right} 
	 * to {@code null}
	 * @param data data to be stored in node
	 */
	public BinaryTreeNode(T data) {
		this(data, null, null);
	}
	
	/**
	 * Initialize a node with given data and
	 * left subtree's reference, assign {@code right} 
	 * to {@code null}
	 * @param data data to be stored in node
	 * @param left reference to left subtree
	 */
	public BinaryTreeNode(T data, BinaryTreeNode<T> left) {
		this(data, left, null);
	}
	
	/**
	 * Initialize a node with given data, left
	 * and right subtree's reference.
	 * @param data data to be stored in node
	 * @param left reference to left subtree
	 * @param right reference to right subtree
	 */
	public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
