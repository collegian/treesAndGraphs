package treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

// 3-4
// 3-2-1
// [3,2,1,4] [3,2,4,1] [3,4,2,1]
// 2 - [1],[4]
// 1 - [], [4]
public class BSTSequences {
	ArrayList<LinkedList<Node<Integer>>> allSequences(Node<Integer> n) {
		if (n == null)
			return new ArrayList<>(new LinkedList<>());

		ArrayList<LinkedList<Node<Integer>>> left = allSequences(n.getLeft());
		ArrayList<LinkedList<Node<Integer>>> right = allSequences(n.getRight());

		ArrayList<LinkedList<Node<Integer>>> result = new ArrayList<>();
		if (left.isEmpty() && right.isEmpty()) 
		{
			LinkedList<Node<Integer>> nodeList = new LinkedList<>();
			nodeList.add(n);
			result.add(nodeList);
			return result;
		}

		if (left.isEmpty()) 
		{
			for (LinkedList<Node<Integer>> r : right) 
			{
				r.addFirst(n);
			}

			return right;
		}

		if (right.isEmpty()) 
		{
			for (LinkedList<Node<Integer>> l : left) 
			{
				l.addFirst(n);
			}

			return left;
		}

		for (LinkedList<Node<Integer>> l : left) 
		{
			for (LinkedList<Node<Integer>> r : right) 
			{
				Node<Integer> lp = l.removeFirst();
				ArrayList<LinkedList<Node<Integer>>> lws = weaves(lp, l, r);
				l.addFirst(lp);
				Node<Integer> rp = r.removeFirst();
				ArrayList<LinkedList<Node<Integer>>> rws = weaves(rp, l, r);
				r.addFirst(rp);
				for (LinkedList<Node<Integer>> lw : lws) 
				{
					lw.addFirst(n);
				}
				for (LinkedList<Node<Integer>> rw : rws) 
				{
					rw.addFirst(n);
				}
				result.addAll(lws);
				result.addAll(rws);
			}
		}

		return result;
	}

	ArrayList<LinkedList<Node<Integer>>> weaves(Node<Integer> p, LinkedList<Node<Integer>> l,
			LinkedList<Node<Integer>> r) 
	{
		ArrayList<LinkedList<Node<Integer>>> result = new ArrayList<>();
		LinkedList<Node<Integer>> ll = new LinkedList<Node<Integer>>(l);
		LinkedList<Node<Integer>> rr = new LinkedList<Node<Integer>>(r);
		if (ll.isEmpty()) 
		{
			rr.addFirst(p);
			result.add(rr);
			return result;
		}

		if (rr.isEmpty()) 
		{
			ll.addFirst(p);
			result.add(ll);
			return result;
		}

		Node<Integer> lp = ll.removeFirst();
		ArrayList<LinkedList<Node<Integer>>> lws = weaves(lp, ll, rr);
		for (LinkedList<Node<Integer>> lw : lws) 
		{
			lw.addFirst(p);
		}
		ll.addFirst(lp);

		Node<Integer> rp = rr.removeFirst();
		ArrayList<LinkedList<Node<Integer>>> rws = weaves(rp, ll, rr);
		for (LinkedList<Node<Integer>> rw : rws) 
		{
			rw.addFirst(p);
		}

		result.addAll(lws);
		result.addAll(rws);

		return result;
	}

	public static void main(String[] args) {
		 Node<Integer> l21 = new Node<>(3,null,null);
		 Node<Integer> l22 = new Node<>(7,null,null);
		 Node<Integer> l1 = new Node<>(5,l21,l22);
		
		 Node<Integer> r21 = new Node<>(10,null,null);
		 Node<Integer> r22 = new Node<>(13,null,null);
		 Node<Integer> r1 = new Node<>(11,r21,r22);
		
		 Node<Integer> root = new Node<>(9,l1,r1);

//		Node<Integer> l21 = new Node<>(1, null, null);
//		Node<Integer> l1 = new Node<>(2, l21, null);
//
//		Node<Integer> r1 = new Node<>(4, null, null);
//
//		Node<Integer> root = new Node<>(3, l1, r1);
		
		BSTSequences s = new BSTSequences();
		ArrayList<LinkedList<Node<Integer>>> all = s.allSequences(root);
		for (LinkedList<Node<Integer>> seq : all) {
			for (Node<Integer> i : seq) {
				System.out.println(i.getData() + ",");
			}
			System.out.println();
		}
	}
}
