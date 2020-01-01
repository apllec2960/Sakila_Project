package sakila.business.model;

import sakila.customer.model.Customer;
import sakila.inventory.model.Film;
import sakila.inventory.model.Inventory;

public class Rental {
	private int rentalId;
	private String rentalDate;
	private String returnDate;
	private String lastUpdate;
	private Inventory inventory;
	private Customer customer;
	private Film film;
	private Staff staff;
	private String customerName;
	private String staffName;
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate
				+ ", lastUpdate=" + lastUpdate + ", inventory=" + inventory + ", customer=" + customer + ", film="
				+ film + ", staff=" + staff + ", customerName=" + customerName + ", staffName=" + staffName + "]";
	}

	
	
}
