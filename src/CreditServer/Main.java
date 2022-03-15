package CreditServer;

import java.io.BufferedReader;
import java.io.FileReader;

enum CreditStatus
{
	ACCEPT,
	REJECT,
	UNKNOWN
}

public class Main
{
	private static final String COMMA_DELIMITER = ",";

	public static void readCustomerCredit(String fileName, BST<Customer> bst)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		    String line;
		    while ((line = br.readLine()) != null)
		    {
		        String[] values = line.split(COMMA_DELIMITER);
		        bst.insert(Integer.parseInt(values[0]), new Customer(values[1], Boolean.parseBoolean(values[2]))); 
		    }
		    br.close();
		}
	    catch( Exception e )
        {
            e.printStackTrace();
        }
	}

	public static CreditStatus getCustomerCreditStatus(int customerID, BST<Customer> bst)
	{
        Customer cust = bst.search(customerID);
        return ((cust == null) ? CreditStatus.UNKNOWN : (cust.getCredit() ? CreditStatus.ACCEPT : CreditStatus.REJECT));
	}
	
    public static void main(String[] args)
    { 
       //create a BST object
        BST<Customer> bst = new BST<>(); 

        //insert data into BST
        readCustomerCredit("CustomerCredit.dat", bst);
        
        //print the BST
        System.out.println(bst); 
        
        //delete leaf node  
        bst.deleteKey(35); 
        System.out.println(bst); 

        //delete the node with one child
        bst.deleteKey(18); 
        System.out.println(bst); 
                 
        //delete node with two children  
        bst.deleteKey(45); 
        System.out.println(bst); 

        //search a key in the BST
        int key = 50;
        Customer cust = bst.search(key);
        System.out.println("Node: " + key + " -> " + ((cust == null) ? "Not found" : cust));
        
        key = 12;
        cust= bst.search(key);
        System.out.println("Node: " + key + " -> " + ((cust == null) ? "Not found" : cust));
        
        int customerID = 45;
        System.out.println(getCustomerCreditStatus(customerID, bst));

        customerID = 67;
        System.out.println(getCustomerCreditStatus(customerID, bst));

        customerID = 111;
        System.out.println(getCustomerCreditStatus(customerID, bst));
     } 
}