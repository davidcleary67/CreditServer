package CreditServer;

class BST<T>
{ 
	//node class that defines BST node
    class Node<T>
    { 
        int key; 
        T data;
        Node<T> left, right; 
   
        public Node(int key, T data)
        { 
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        } 

        @Override
    	public String toString()
    	{
        	return "Node: " + key + " -> " + data;
    	}
} 
    
    // BST root node 
    Node<T> root; 
  
   // Constructor for BST =>initial empty tree
    public BST()
    { 
        root = null; 
    } 
    
    //delete a node from BST
    void deleteKey(int key)
    { 
        // root = delete_Recursive(root, key); 
        root = deleteNode(root, key);
    } 
   
    // Get minimum element in binary search tree
    Node<T> minimumElement(Node<T> root)
    {
           if (root.left == null)
           {
                return root;
           }
            else
            {
                return minimumElement(root.left);
            }
        }
    
    // Delete node from binary search tree
    Node<T> deleteNode(Node<T> root, int key)
    {
            if (root == null)
            {
                return null;
            }
            
            if (root.key > key)
            {
                root.left = deleteNode(root.left, key);
            }
            else if (root.key < key)
            {
                root.right = deleteNode(root.right, key);
            }
            else
            {
                // if nodeToBeDeleted have both children
                if ((root.left != null) && (root.right != null))
                {
                    Node<T> temp = root;
                    // Finding minimum element from right
                    Node<T> minNodeForRight = minimumElement(temp.right);
                    // Replacing current node with minimum node from right subtree
                    root.key = minNodeForRight.key;
                    root.data = minNodeForRight.data; // ***
                    // Deleting minimum node from right now
                    root.right = deleteNode(root.right, minNodeForRight.key);
                }
                // if nodeToBeDeleted has only left child
                else if (root.left != null)
                {
                    root = root.left;
                }
                // if nodeToBeDeleted has only right child
                else if (root.right != null)
                {
                    root = root.right;
                }
                // if nodeToBeDeleted do not have child (Leaf node)
                else
                {
                    root = null;
                }
            }
            
            return root;
        }
    
    // insert a node in BST 
    void insert(int key, T data)
    { 
        root = insert_Recursive(root, key, data); 
    } 
   
    //recursive insert function
    Node<T> insert_Recursive(Node<T> root, int key, T data)
    { 
        //tree is empty
        if (root == null)
        { 
            root = new Node<T>(key, data); 
            return root; 
        } 
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key, data); 
        else if (key > root.key)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key, data); 
          // return pointer
        return root; 
    } 
 
    private String display;
    
    // method for inorder traversal of BST 
    public void inorder()
    { 
    	this.display = "Binary Search Tree:\n";
        inorder_Recursive(root); 
    } 
   
    // recursively traverse the BST  
    private void inorder_Recursive(Node<T> root)
    { 
        if (root != null)
        { 
            inorder_Recursive(root.left); 
            display += root + "\n"; 
            inorder_Recursive(root.right); 
        } 
    } 
     
    T search(int key)
    { 
        Node<T> node = search_Recursive(root, key); 
        if (node != null)
            return node.data;
        else
            return null;
    } 
   
    //recursive insert function
    Node<T> search_Recursive(Node<T> root, int key)
    { 
        // Base Cases: root is null or key is present at root 
        if (root == null || root.key == key) 
            return root; 
        // val is greater than root's key 
        if (root.key > key) 
            return search_Recursive(root.left, key); 
        // val is less than root's key 
        return search_Recursive(root.right, key); 
    } 

    @Override
	public String toString()
	{
    	inorder();
		return display;
	}
}