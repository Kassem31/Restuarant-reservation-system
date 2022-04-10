package Restaurant_Reservation;


public class Tables  {
	
	 private int Number;
	 private int Seats;
	 private String smokingArea;
	 private boolean smoking;
	 private boolean reserved;

	 
	public int getNumber() {
		return Number;
	}


	public void setNumber(int number) {
		Number = number;
	}


	public int getSeats() {
		return Seats;
	}


	public void setSeats(int seats) {
		Seats = seats;
	}


	
	public String getSmokingArea() {
		return smokingArea;
	}


	public void setSmokingArea(String smokingArea) {
		this.smokingArea = smokingArea;
	}


	public boolean isSmoking() {
		return smoking;
	}


	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}


	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

}
