package datastructures.trees;

import java.util.NoSuchElementException;

import datastructures.AVLTreeNode;

/**
 * AVL tree (self-balancing BST tree) implementation
 * made with {@code AVLTreeNode<T>} objects.
 * @author Baris Unlusarac
 *
 * @param <T> type of data stored in tree, must be
 * subclass of {@code java.lang.Number}
 */

public class AVLTree<T extends Number> {
	private AVLTreeNode<T> root;
	
	public AVLTree(T data) {
		this.root = new AVLTreeNode<T>(data);
	}
	
	/**
	 * Inserts a value to the tree and balances it
	 * if it gets imbalanced.
	 * <p>Time complexity of this operation is O(logn).</p>
	 * <p>Throws {@code IllegalArgumentException} if tree 
	 * already contains the given value.</p>
	 * @param data data to insert to the tree.
	 */
	public void insert(T data) {
		this.root = insert(data, root);
	}
	
	/**
	 * Removes a value from the tree and balances it
	 * if it gets imbalanced.
	 * <p>Time complexity of this operation is O(logn).</p>
	 * <p>Throws {@code NoSuchElementException} if given value
	 * is not be found in the tree.</p>
	 * @param data data to remove from the tree
	 */
	public void delete(T data) {
		this.root = delete(data, root);
	}
	
	/**
	 * Searches a value from the tree and returns true if it's found.
	 * <p>Time complexity of this operation is O(logn).</p>
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
	private String postOrder(AVLTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return postOrder(tree.left) + postOrder(tree.right) + tree.data.toString();
		}
	}

	private String preOrder(AVLTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return tree.data.toString() + preOrder(tree.left) + preOrder(tree.right);
		}
	}

	private String inOrder(AVLTreeNode<T> tree){
		if(tree == null){
			return "";
		}
		else{
			return inOrder(tree.left) + tree.data.toString() + inOrder(tree.right);
		}
	}
	
	private AVLTreeNode<T> insert(T data, AVLTreeNode<T> tree){
		if(tree == null) {
			return new AVLTreeNode<T>(data);
		}
		else if(data.doubleValue() > tree.data.doubleValue()) {
			tree.right = insert(data, tree.right);
		}
		else if(data.doubleValue() < tree.data.doubleValue()) {
			tree.left = insert(data, tree.left);
		}
		else if(data.doubleValue() == tree.data.doubleValue()) {
			throw new IllegalArgumentException("The element is already in the tree");
		}
		
		//Update height of this node
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
		
		//Calculate balance factor for this node
		int balanceFactor = calculateBalanceFactor(tree);
		
		//and test if there's imbalance, if so, do corresponding rotation
		//this will return balanced version of the current subtree to its parent
		//to be assigned to the corresponding link (left or right)
		if(balanceFactor > 1) {
			if(data.doubleValue() < tree.left.data.doubleValue()) {
				return rightRotate(tree);
			}
			else {
				tree.left = leftRotate(tree.left);
				return rightRotate(tree);
			}
		}
		else if(balanceFactor < -1) {
			if(data.doubleValue() > tree.right.data.doubleValue()) {
				return leftRotate(tree);
			}
			else {
				tree.right = rightRotate(tree.right);
				return leftRotate(tree);
			}
		}
		
		//If balancing is not required, return current subtree to its parent
		//so that it will be assigned to the corresponding link (left or right)
		return tree;
	}
	
	private AVLTreeNode<T> delete(T data, AVLTreeNode<T> tree){
		//Search recursively for the node to be deleted

		if(tree == null) {
			throw new NoSuchElementException("There is no such element in the tree");
		}
		else if(data.doubleValue() > tree.data.doubleValue()) {
			tree.right = delete(data, tree.right);
		}
		else if(data.doubleValue() < tree.data.doubleValue()) {
			tree.left = delete(data, tree.left);
		}
		else{
			//Found the node!
			
			//If tree has at most 1 child
			if(tree.left == null || tree.right == null){
				if(tree.left != null){
					//There is a child to the left
					tree = tree.left;
				}
				else if(tree.right != null){
					//There is a child to the right
					tree = tree.right;
				}
				else{
					//There are no children
					tree = null;
				}
			}
			else{
				//If tree has 2 children
				T successor = findMin(tree.right).data;
				tree.data = successor;
				tree.right = delete(successor, tree.right);
			}
		}

		//If there was no children of the node to be deleted, node
		//was set to null, so that it can return itself to parent
		//node and assign parent node's corresponding link to null. 
		//It won't be necessary updating height and calculating 
		//balance factor for it, so return beforehand if that's the case.
		if(tree == null)
			return tree;

		//Update height and calculate balance factor of the current node,
		//and balance the current subtree if necessary. Return the balanced
		//subtree to its parent so that the parent can assign it to its
		//corresponding link.
		tree.height = Math.max(getHeight(tree.left), getHeight(tree.right)) + 1; 
		int balanceFactor = calculateBalanceFactor(tree);

		if(balanceFactor > 1) {
			if(calculateBalanceFactor(tree.left) >= 0) {
				return rightRotate(tree);
			}
			else {
				tree.left = leftRotate(tree.left);
				return rightRotate(tree);
			}
		}
		else if(balanceFactor < -1) {
			if(calculateBalanceFactor(tree.right) <= 0) {
				return leftRotate(tree);
			}
			else {
				tree.right = rightRotate(tree.right);
				return leftRotate(tree);
			}
		}
		
		//If balancing is not required, return current subtree to its parent
		//so that it will be assigned to the corresponding link (left or right)
		return tree;
	}
	
	private boolean search(T data, AVLTreeNode<T> tree) {
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

	private int getHeight(AVLTreeNode<T> tree) {
		if(tree == null)
			return -1;
		else
			return tree.height;
	}

	private int calculateBalanceFactor(AVLTreeNode<T> node) {
		return getHeight(node.left) - getHeight(node.right);
	}
	
	private AVLTreeNode<T> rightRotate(AVLTreeNode<T> node) {
		AVLTreeNode<T> leftSubtree = node.left;
		node.left = leftSubtree.right;
		leftSubtree.right = node;
		
		//Update height of modified nodes
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		leftSubtree.height = Math.max(getHeight(leftSubtree.left), getHeight(leftSubtree.right)) + 1;
		
		return leftSubtree;
	}
	
	private AVLTreeNode<T> leftRotate(AVLTreeNode<T> node) {
		AVLTreeNode<T> rightSubtree = node.right;
		node.right = rightSubtree.left;
		rightSubtree.left = node;
		
		//Update height of modified nodes
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		rightSubtree.height = Math.max(getHeight(rightSubtree.left), getHeight(rightSubtree.right)) + 1;
		
		return rightSubtree;
	}

	private AVLTreeNode<T> findMin(AVLTreeNode<T> tree){ 
		AVLTreeNode<T> temp = tree;
		
		while(temp.left != null)
			temp = temp.left;
		
		return temp;
	}
}
