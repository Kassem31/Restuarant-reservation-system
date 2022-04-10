package Restaurant_Reservation;

public class Customer extends User {
	
	private int tableID;
	private double totalPricePaid;
	private double paidAmount;
	private String Dishes;
	
	
	public int getTableID()
	{
		return tableID;
	}
	public void setTableID(int tableID)
	{
		this.tableID = tableID;
	}
	public double getTotalPricePaid()
	{
		return totalPricePaid;
	}
	public void setTotalPricePaid(double totalPricePaid) //For regular customers
	{
		this.totalPricePaid = totalPricePaid;
	}
	
	public void setTotalPricePaid(double totalPricePaid, double Discount) // For premium customers
	{
		if(totalPricePaid > 300)
		{
			this.totalPricePaid=totalPricePaid-totalPricePaid/4; //25% off for above 300 L.E for Premium customer only
		}
		else
		{
			this.totalPricePaid=totalPricePaid-totalPricePaid*Discount;
		}
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getDishes() {
		return Dishes;
	}
	public void setDishes(String dishes) {
		Dishes = dishes;
	}
	
	
	
	

}
