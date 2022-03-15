package CreditServer;

/**
 * Binary search tree parameterisable class.
 * @author David Cleary
 * @param <T>
 */
class BST<T>
{
    /**
     * Items in a BST are Nodes with type T.	
     * @author David Cleary
     * @param <T>
     */
    class Node<T>
    {
    	/**
    	 * The current Node's key used to sort the BST.
    	 */
        int key; 
        /**
         * An object with type T that contains the current node's data. 
         */
        T data;
        /**
         * Left and right subtrees containing keys less than and greater than the current Node's key. 
         */
        Node<T> left;
        Node<T> right; 
   
        /**
         * Constructor
         * @param key
         * @param data
         */
        public Node(int key, T data)
        { 
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        } 

        /**
         * Display the current Node.
         */
        @Override
    	public String toString()
    	{
        	return "Node: " + key + " -> " + data;
    	}
    } 
    
    /**
     * BST root Node.
     */
    Node<T> root; 
  
    /**
     * Constructor
     * Create an initially empty BST.
     */
    public BST()
    { 
        root = null; 
    } 
    

    /**
     * Find the Node with the minimum key in the BST.
     * @param root
     * @return the Node with the minimum key in the BST.
     */
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
    
    /**
     * Delete the Node with the specified key from the BST. 
     * @param key
     */
    //delete a node from BST
    void deleteKey(int key)
    { 
        root = deleteNode(root, key);
    } 
   
    /**
     * Recursively traverse the tree to find and delete the Node with the specified key from the BST.
     * @param root
     * @param key
     * @return the Node 
     */
    Node<T> deleteNode(Node<T> root, int key)
    {
    	// BST is empty
        if (root == null)
        {
            return null;
        }
        
        // Whilst Node with key not found, recursively traverse BST
        if (root.key > key)
        {
            root.left = deleteNode(root.left, key);
        }
        else if (root.key < key)
        {
            root.right = deleteNode(root.right, key);
        }
        // Node with key found
        else
        {
            // Node has both left and right subtrees
            if ((root.left != null) && (root.right != null))
            {
                Node<T> temp = root;
                // Find minimum Node in right subtree
                Node<T> minNodeForRight = minimumElement(temp.right);
                // Replace current Node's values with values from minimum Node in right subtree
                root.key = minNodeForRight.key;
                root.data = minNodeForRight.data;
                // Delete minimum Node in right subtree
                root.right = deleteNode(root.right, minNodeForRight.key);
            }
            // Node has only left subtree
            else if (root.left != null)
            {
                root = root.left;
            }
            // Node has only right subtree
            else if (root.right != null)
            {
                root = root.right;
            }
            // Node is a leaf node)
            else
            {
                root = null;
            }
        }
        return root;
    }
    
    /**
     * Add a new Node with key and data to the BST. 
     * @param key
     * @param data
     */
    // insert a node in BST 
    void insert(int key, T data)
    { 
        root = insert_Recursive(root, key, data); 
    } 

    /**
     * Recursively traverse the tree to find the correct location to add a new Node with the specified key
     * into the BST.
     * @param root
     * @param key
     * @param data
     * @return the Node 
     */
    Node<T> insert_Recursive(Node<T> root, int key, T data)
    { 
        // A leaf node is reached in the BST
        if (root == null)
        { 
            // Create a new Node
            root = new Node<T>(key, data); 
            return root; 
        } 
        // Recursively traverse the BST
        // New key is less than the current root key
        if (key < root.key)     
        {
            // Insert into the left subtree
            root.left = insert_Recursive(root.left, key, data);
        }
        // New key is greater than the current root key
        else if (key > root.key)
        {
            // Insert in the right subtree
            root.right = insert_Recursive(root.right, key, data);
        }
        return root; 
    } 
 
    /**
     * Used to display BST.
     */
    private String display;
    
    /**
     * Perform inorder traversal of BST to build display string.
     */
    public void inorder()
    { 
    	this.display = "Binary Search Tree:\n";
        inorder_Recursive(root); 
    } 
   
    /**
     * Recursively perform inorder traversal of BST to build display string.
     * @param root
     */
    private void inorder_Recursive(Node<T> root)
    { 
        if (root != null)
        { 
            inorder_Recursive(root.left); 
            display += root + "\n"; 
            inorder_Recursive(root.right); 
        } 
    } 
     
    /**
     * Search for key in BST.
     * @param key
     * @return Node containing key or null
     */
    T search(int key)
    { 
        Node<T> node = search_Recursive(root, key); 
        if (node != null)
        {
            return node.data;
        }
        else
        {
            return null;
        }
    } 
   
    /**
     * Recursively search for key in BST.
     * @param root
     * @param key
     * @return Node containing key or null.
     */
    Node<T> search_Recursive(Node<T> root, int key)
    { 
        // root is null or key is present at root 
        if ((root == null) || (root.key == key))
        {
            return root;
        }
        // root key is greater than key 
        if (root.key > key)
        {
            return search_Recursive(root.left, key);
        }
        // root key is is less than key 
        return search_Recursive(root.right, key); 
    } 

    /**
     * Display BST.
     */
    @Override
	public String toString()
	{
    	inorder();
		return display;
	}
}