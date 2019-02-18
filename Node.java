package treesAndGraphs;

public class Node<T>
{
	private Node<T> left;
	private Node<T> right;
	private T data;
	
	Node(T data, Node<T> left, Node<T> right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	Node<T> getLeft()
	{
		return left;
	}
	
	Node<T> getRight()
	{
		return right;
	}
	
	T getData()
	{
		return data;
	}
}
