package datastructures.trees;

import java.util.NoSuchElementException;

import datastructures.BinaryTreeNode;

/**
 * Binary search tree implementation made with 
 * {@code BinaryTreeNode<T>} objects.
 * @author Baris Unlusarac
 *
 * @param <T> type of data stored in tree, must be
 * subclass of {@code java.lang.Number}.
 */
public class BinarySearchTree<T extends Number> {
	private BinaryTreeNode<T> root;
	
	/**
	 * Initialize a BST with root node holding given data.
	 * @param data root node's data
	 */
	public BinarySearchTree(T data) {
		this.root = new BinaryTreeNode<>(data);
	}
	
	/**
	 * Inserts a value to the tree.
	 * <p>Time complexity of this operation is O(logn) in best case, 
	 * O(n) in worst case.</p>
	 * <p>Throws {@code IllegalArgumentException} if tree 
	 * already contains the given value.</p>
	 * @param data data to insert to the tree
	 */
	public void insert(T data) {
		insert(data, root);
	}
	
	/**
	 * Removes a value from the tree.
	 * <p>Time complexity of this operation is O(logn) in best case, 
	 * O(n) in worst case.</p>
	 * <p>Throws {@code NoSuchElementException} if given value
	 * is not be found in the tree.</p>
	 * @param data data to remove from the tree
	 */
	public void delete(T data) {
		delete(data, root);
	}
	
	/**
	 * Searches a value from the tree and returns true if it's found.
	 * <p>Time complexity of this operation is O(logn) in best case, 
	 * O(n) in worst case.</p>
	 * @param data data to look for in the tree
	 * @return boolean showing whether the element is in tree or not
	 */
	public boolean search(T data){
		return search(data, root);
	}
	
	/**
	 * Returns a string that contains elements of the tree
	 * sorted according to post-order traversal.
	 * @return string of elements
	 */
	public String postOrder() {
		return postOrder(root);
	}
	
	/**
	 * Returns a string that contains elements of the tree
	 * sorted according to pre-order traversal.
	 * @return string of elements
	 */
	public String preOrder() {
		return preOrder(root);
	}
	
	/**
	 * Returns a string that contains elements of the tree
	 * sorted according to in-order traversal.
	 * @return string of elements
	 */
	public String inOrder() {
		return inOrder(root);
	}

	//Recursion methods
	private String postOrder(BinaryTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return postOrder(tree.left) + postOrder(tree.right) + tree.data.toString();
		}
	}

	private String preOrder(BinaryTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return tree.data.toString() + preOrder(tree.left) + preOrder(tree.right);
		}
	}

	private String inOrder(BinaryTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return inOrder(tree.left) + tree.data.toString() + inOrder(tree.right);
		}
	}
	
	private BinaryTreeNode<T> insert(T data, BinaryTreeNode<T> tree) {
		if(tree == null) {
			return new BinaryTreeNode<T>(data);
		}
		else if(data.doubleValue() > tree.data.doubleValue()) {
			tree.right = insert(data, tree.right);
		}
		else if(data.doubleValue() < tree.data.doubleValue()) {
			tree.left = insert(data, tree.left);
		}
		else {
			throw new IllegalArgumentException("The element is already in the tree");
		}
		
		return tree;
	}

	private BinaryTreeNode<T> delete(T data, BinaryTreeNode<T> tree){
		if(tree == null) {
			throw new NoSuchElementException("There is no such element in the tree");
		}
		else if(data.doubleValue() > tree.data.doubleValue()) {
			tree.right = delete(data, tree.right);
		}
		else if(data.doubleValue() < tree.data.doubleValue()) {
			tree.left = delete(data, tree.left);
		}
		else {
			if(tree.left == null)
				return tree.right;
			else if(tree.right == null)
				return tree.left;
			
			/*Move inorder successors data to deleted node, delete reference to
			inorder successor from right subtree*/
			T successor = findMin(tree.right).data;
			tree.data = successor;
			tree.right = delete(successor, tree.right);
		}
		
		return tree;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> tree){ 
		BinaryTreeNode<T> temp = tree;
		
		while(temp.left != null)
			temp = temp.left;
		
		return temp;
	}
	
	private boolean search(T data, BinaryTreeNode<T> tree) {
		if(tree == null) {
			return false;
		}
		else if(data.doubleValue() > tree.data.doubleValue()) {
			return search(data, tree.right);
		}
		else if(data.doubleValue() < tree.data.doubleValue()) {
			return search(data, tree.left);
		}
		else {
			return true;
		}
	}
}
