package LearnBinaryTree;

public class BinaryTree {
	private static Node root;
	
	public BinaryTree(int rootData){
		root = new Node(rootData);
	}
	
	public BinaryTree(){
		
	}
	
	public void addNode(Node parent, Node child, String treeSide){
		if("left".equalsIgnoreCase(treeSide)){
			parent.setLeft(child);
		}else if("right".equalsIgnoreCase(treeSide)){
			parent.setRight(child);
		}else{
			System.out.println("Invalid tree side : Node not added.");
		}
	}
	
	/*
	 * The tree constructed :
	 * 
	 * 						10
	 * 			28						15
	 * 29				16		8				7
	 * 		1
	 * 
	 */
	public Node createTree(){
		BinaryTree tree = new BinaryTree(10);
		Node n1 = new Node(28);
		Node n2 = new Node(15);
		Node n3 = new Node(29);
		Node n4 = new Node(16);
		Node n5 = new Node(1);
		Node n6 = new Node(8);
		Node n7 = new Node(7);
		
		tree.addNode(root, n1, "left");
		tree.addNode(root, n2, "right");
		tree.addNode(n1, n3, "left");
		tree.addNode(n1, n4, "right");
		tree.addNode(n3, n5, "right");
		tree.addNode(n2, n6, "left");
		tree.addNode(n2, n7, "right");
		
		return root;
	}
}
