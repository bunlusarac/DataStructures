package datastructures;

public class AVLTreeNode<T>{
	public T data;
	public int height;
	public AVLTreeNode<T> left;
	public AVLTreeNode<T> right;
	
	public AVLTreeNode(T data) {
		this(data, null, null);
	}

	public AVLTreeNode(T data, AVLTreeNode<T> left) {
		this(data, left, null);
	}
	
	public AVLTreeNode(T data, AVLTreeNode<T> left, AVLTreeNode<T> right) {
		this.left = left;
		this.right = right;
		this.data = data;
		this.height = 0;
	}
	
	
}
