package ds.powergame;

public class ParPtrTree {
	private Integer[] array; // Node array

	public ParPtrTree(int size) {
		array = new Integer[size]; // Create node array
		for (int i = 0; i < size; i++)
			array[i] = null;
	}

	/** Determine if nodes are in different trees */
	public boolean differ(int a, int b) {
		// fill your code
		Integer root1 = FIND(a); //Find root of a node a
		Integer root2 = FIND(b); //Find root of a node b
		return root1 != root2; 	 //Compare roots
	}

	/** Merge two subtrees */
	public void UNION(int a, int b) {
		// fill your code
		Integer root1 = FIND(a); //Find root of a node a
		Integer root2 = FIND(b); //Find root of a node b
		if(root1 != root2) array[root2] = root1; //Merge
	}

	public Integer FIND(Integer curr) {
		// fill your code
		// must use path compression
		if(array[curr] == null) return curr; //At root
		while(array[curr] != null) curr = array[curr];
		return curr;
	}
}
