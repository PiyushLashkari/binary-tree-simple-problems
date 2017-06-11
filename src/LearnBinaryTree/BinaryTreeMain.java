package LearnBinaryTree;

import javax.annotation.PreDestroy;

public class BinaryTreeMain {

	public static void main(String[] args) {
		Node root = new Node();
		BinaryTree tree = new BinaryTree();
		root = tree.createTree();
		System.out.println("Preorder Traversal of the tree: ");
		preOrderTraversal(root);
		System.out.println("Preorder Traversal of the tree (Morris Algo): ");
		morrisPreOrderTraversal(root);
		System.out.println("Inorder Traversal of the tree: ");
		inOrderTraversal(root);
		System.out.println("Inorder Traversal of the tree (Morris Algo): ");
		morrisInOrderTraversal(root);
		System.out.println("Postorder Traversal of the tree: ");
		postOrderTraversal(root);
		System.out.println("Size of tree : " + sizeOfTree(root));
		System.out.println("Number of Leaf Nodes in tree : " + numberOfLeafNodes(root));
		System.out.println("Number of Internal Nodes in tree : " + numberOfInternalNodes(root));
		System.out.println("Height of tree : " + heightOfTree(root));
		System.out.println("Sum of all Nodes : " + sumOfNodesData(root));
		System.out.println("Path for sum 33(true) : " + findSumInPathInTheTree(root, 33));
		System.out.println("Path for sum 23(false) : " + findSumInPathInTheTree(root, 23));
		System.out.println("Least Common Ancestor : " + leastCommonAncestor(root, 16, 15).getData());
	}

	public static void preOrderTraversal(Node node){
		if(node == null){
			return;
		}
		System.out.println(node.getData());
		preOrderTraversal(node.getLeft());
		preOrderTraversal(node.getRight());
	}
	
	public static void inOrderTraversal(Node node){
		if(node == null){
			return;
		}
		inOrderTraversal(node.getLeft());
		System.out.println(node.getData());
		inOrderTraversal(node.getRight());
	}
	
	public static void morrisInOrderTraversal(Node node){
		Node current = node;
		while(current != null){
			if(current.getLeft() == null){
				System.out.println(current.getData());
				current = current.getRight();
			}else{
				Node predecessor = current.getLeft();
				while(predecessor.getRight() != current && predecessor.getRight() != null){
					predecessor = predecessor.getRight();
				}
				if(predecessor.getRight() == null){
					predecessor.setRight(current);
					current = current.getLeft();
				}else{
					predecessor.setRight(null);
					System.out.println(current.getData());
					current = current.getRight();
				}
			}
		}
	}
	
	public static void morrisPreOrderTraversal(Node node){
		Node current = node;
		while(current != null){
			if(current.getLeft() == null){
				System.out.println(current.getData());
				current = current.getRight();
			}else{
				Node predecessor = current.getLeft();
				while(predecessor.getRight() != current && predecessor.getRight() != null){
					predecessor = predecessor.getRight();
				}
				if(predecessor.getRight() == null){
					System.out.println(current.getData());
					predecessor.setRight(current);
					current = current.getLeft();
				}else{
					predecessor.setRight(null);
					current = current.getRight();
				}
			}
		}
	}
	
	public static void postOrderTraversal(Node node){
		if(node == null){
			return;
		}
		postOrderTraversal(node.getLeft());
		postOrderTraversal(node.getRight());
		System.out.println(node.getData());
	}
	
	public static int sizeOfTree(Node node){
		if(node == null){
			return 0;
		}
		int left = sizeOfTree(node.getLeft());
		int right = sizeOfTree(node.getRight());
		return left + right + 1;
	}
	
	public static int numberOfLeafNodes(Node node){
		if(node == null){
			return 0;
		}
		if(node.getLeft() == null && node.getRight() == null){
			return 1;
		}
		int left = numberOfLeafNodes(node.getLeft());
		int right = numberOfLeafNodes(node.getRight());
		
		return left + right;
	}
	
	public static int numberOfInternalNodes(Node node){
		if(node == null){
			return 0;
		}
		if(node.getLeft() == null && node.getRight() == null){
			return 0;
		}
		int left = numberOfInternalNodes(node.getLeft());
		int right = numberOfInternalNodes(node.getRight());
		
		return left + right + 1;
	}
	
	public static int heightOfTree(Node node){
		int max;
		if(node == null){
			return 0;
		}
		int left = heightOfTree(node.getLeft());
		int right = heightOfTree(node.getRight());
		if(left > right){
			max = left;
		}else{
			max = right;
		}
		return max + 1;
	}
	
	public static int sumOfNodesData(Node node){
		if(node == null){
			return 0;
		}
		
		return node.getData() + sumOfNodesData(node.getLeft()) + sumOfNodesData(node.getRight());
	}
	
	public static boolean findSumInPathInTheTree(Node node, int sum){
		if(node == null){
			return false;
		}
		if(node.getLeft() == null && node.getRight() == null){
			if(node.getData() == sum){
				return true;
			}else{
				return false;
			}
		}
		if(findSumInPathInTheTree(node.getLeft(), sum - node.getData())){
			return true;
		}
		if(findSumInPathInTheTree(node.getRight(), sum - node.getData())){
			return true;
		}
		return false;
	}
	
	public static Node leastCommonAncestor(Node node, int a, int b){
		if(node == null){
			return null;
		}
		
		Node left = leastCommonAncestor(node.getLeft(), a, b);
		Node right = leastCommonAncestor(node.getRight(), a, b);
		
		if(node.getData() == a || node.getData() == b){
			return node;
		}
		
		if(left != null && right != null){
			return node;
		}else if(left == null && right == null){
			return null;
		}else if(left == null && right != null){
			return right;
		}else{
			return left;
		}
	}
	
	public static bstData isHavingBinarySearchTree(Node node){
		bstData tempBstData = new bstData();
		if(node == null){
			return tempBstData;
		}
		bstData left = isHavingBinarySearchTree(node.getLeft());
		bstData right = isHavingBinarySearchTree(node.getRight());
		
		if(left.isBst == false || right.isBst == false || (node.getData() < left.max && node.getData() > right.min)){
			tempBstData.isBst = false;
			tempBstData.size = Math.max(left.size, right.size);
		}
		
		tempBstData.isBst = true;
		tempBstData.size = left.size + right.size + 1;
		tempBstData.min = node.getLeft() != null ? left.max : node.getData();
		tempBstData.max = node.getRight() != null ? right.min : node.getData();
		
		return tempBstData;
	}
}
