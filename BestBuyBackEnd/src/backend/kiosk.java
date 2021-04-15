package backend;

public class kiosk 
{
	String name;
	int transactionCount;
	int totalTickets;
	double totalDollars;

	//
	// constructor
	//
	public kiosk (String n, int tr, int ticks, double val)
	{
		name             = n;
		transactionCount = tr;
		totalTickets     = ticks;
		totalDollars     = val;
	}

	public String toString()
	{
		return name + " : " + "  #Trans = " + String.format("%-4d", transactionCount) 
		                    + "   Items = " + String.format("%-4d",  totalTickets)
			                + " Dollars = " + String.format("%.2f", totalDollars);
	}
	
	public String forFileOutput()
	{
		return name + "," + transactionCount + "," + totalTickets + "," + String.format("%.2f", totalDollars);
	}
	
	
	
	//
	// put methods
	//
	public void incrementTrans()
	{
		transactionCount++;
	}
	
	public void addTickets(int c)
	{
		totalTickets = totalTickets + c;
	}

	public void addDollars(double d)
	{
		totalDollars = totalDollars + d;
	}

	
	
	//
	// get methods
	//
	public int getTransactions()
	{
		return transactionCount;
	}
	
	public int getTickets()
	{
		return totalTickets;
	}
	
	public double getDollars()
	{
		return totalDollars;
	}
}

