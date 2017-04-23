package database;

import customer.Customer;

public interface DataAccessObject {
	
	
	/**
	 * Access to the database to add a customer to the database.
	 * @param customer The customer object
	 * @param payment The payment object
	 * @return success If the operation was successful.
	 */
	public boolean addCustomerToDatabase(Customer customer, Payment payment);


}
