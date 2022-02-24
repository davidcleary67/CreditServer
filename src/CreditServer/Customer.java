package CreditServer;

//customer credit class that specifies a customer's name and credit status
public class Customer
{
	private String name;
	private boolean credit;
	
	public Customer()
	{
		this.name = "";
		this.credit = false;
	}

	public Customer(String name, boolean credit)
	{
		this.name = name;
		this.credit = credit;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean getCredit()
	{
		return credit;
	}

    @Override
	public String toString()
	{
    	return "Customer: " + name + "; " + credit;
	}
}	