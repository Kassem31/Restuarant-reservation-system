package Restaurant_Reservation;

public class Food {
	
	private String Name;
	private String Type;
	private double Price;
	private double taxes;
	private double totalPrice;
	private int Quantity;
	private boolean Ordered;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double price, double taxes,int quantity) {
		this.taxes = price*taxes*quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public boolean isOrdered() {
		return Ordered;
	}
	public void setOrdered(boolean ordered) {
		Ordered = ordered;
	}
	
}
