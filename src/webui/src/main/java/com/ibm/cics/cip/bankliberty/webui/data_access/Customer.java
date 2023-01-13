/*
 *
 *    Copyright IBM Corp. 2022
 *
 */
package com.ibm.cics.cip.bankliberty.webui.data_access;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import com.ibm.cics.cip.bankliberty.api.json.CustomerJSON;
import com.ibm.cics.cip.bankliberty.api.json.CustomerResource;
import com.ibm.json.java.JSONObject;

public class Customer {

    static final String COPYRIGHT =
      "Copyright IBM Corp. 2022";
    
    private static Logger logger = Logger.getLogger("com.ibm.cics.cip.bankliberty.webui.dataAccess");



	private static final String JSON_SORT_CODE = "sortCode";
	private static final String JSON_ID = "id";
	private static final String JSON_CUSTOMER_NAME = "customerName";
	private static final String JSON_CUSTOMER_ADDRESS = "customerAddress";
	private static final String JSON_CUSTOMER_CREDIT_SCORE = "customerCreditScore";
	private static final String JSON_CUSTOMER_REVIEW_DATE = "customerCreditScoreReviewDate";
	private static final String JSON_DATE_OF_BIRTH = "dateOfBirth";
	
	private static final String dashes = "------------";



	// String ACCOUNT_EYECATCHER             CHAR(4),
	private 	String 		customerNumber;
	private 	String 		sortcode;              
	private 	String 		name;
	private 	String 		address;
	private 	Date 		dob;
	private 	String		creditScore;
	private		Date		creditScoreReviewDate;
	private		Boolean		editingCustomer;
		
	
	
	//NEW CUSTOMER
	public Customer (String custNo, String sortCode, String name, String address, Date dob) 
	{
		sortOutLogging();
		editingCustomer = false;
		setCustomerNumber(custNo);
		setSortcode(sortCode);
		setName(name);
		setAddress(address);
		setDob(dob);
	}
	
	//EDITING CUSTOMER
	public Customer (String custNo, String sortCode, String name, String address, Date dob, String creditScore, Date creditScoreReviewDate) 
	{
		sortOutLogging();
		editingCustomer = true;
		setCustomerNumber(custNo);
		setSortcode(sortCode);
		setName(name);
		setAddress(address);
		setDob(dob);
		setCreditScore(creditScore);
		setCreditScoreReviewDate(creditScoreReviewDate);
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String custNo) {
		this.customerNumber = custNo;
	}

	public String getSortcode() {
		return sortcode;
	}

	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCreditScore(){
		return creditScore;
	}
	
	public void setCreditScore(String creditScore){
		this.creditScore = creditScore;
	}
	
	public Date getCreditScoreReviewDate(){
		return creditScoreReviewDate;
	}
	
	public void setCreditScoreReviewDate(Date creditScoreReviewDate){
		this.creditScoreReviewDate = creditScoreReviewDate;
	}
	
	
	public boolean updateThis(){
		CustomerResource myCustomerResource = new CustomerResource();

		CustomerJSON myCustomerJSON = new CustomerJSON();

		myCustomerJSON.setCustomerAddress(this.getAddress());
		myCustomerJSON.setCustomerName(this.getName());
		myCustomerJSON.setSortCode(this.getSortcode());
		myCustomerJSON.setSortCode(this.getSortcode());
		
		Response myCustomerResponse = myCustomerResource.updateCustomerExternal(Long.parseLong(this.getCustomerNumber()), myCustomerJSON);

		String myCustomerString = null;
		JSONObject myCustomer = null;

		if(myCustomerResponse.getStatus() == 200)
		{
			myCustomerString = myCustomerResponse.getEntity().toString();
			try {
				myCustomer = JSONObject.parse(myCustomerString);
			} catch (IOException e) {
				myCustomerResponse.close();
 				return false;
			}

			this.setDob(sortOutDate((String) myCustomer.get(JSON_DATE_OF_BIRTH)));
			this.setAddress((String) myCustomer.get(JSON_CUSTOMER_ADDRESS));
			this.setName((String) myCustomer.get(JSON_CUSTOMER_NAME));
			this.setSortcode((String) myCustomer.get(JSON_SORT_CODE));
			String customerNoString = (String) myCustomer.get(JSON_ID);
			this.setCustomerNumber(customerNoString);

		}
		else
		{
			myCustomerResponse.close();
			return false;
		}
		myCustomerResponse.close();
		return true;
	}

	public boolean deleteFromDB(){
		CustomerResource myCustomerResource = new CustomerResource();
		
		Response myCustomerResponse = myCustomerResource.deleteCustomerExternal(Long.parseLong(this.getCustomerNumber()));

		String myCustomerString = null;
		JSONObject myCustomer = null;

		if(myCustomerResponse.getStatus() == 200)
		{
			myCustomerString = myCustomerResponse.getEntity().toString();
			try 
			{
				myCustomer = JSONObject.parse(myCustomerString);
			} 
			catch (IOException e) 
			{
					logger.severe(e.toString());
				myCustomerResponse.close();
				return false;
			}

			this.setDob(sortOutDate((String) myCustomer.get(JSON_DATE_OF_BIRTH)));
			this.setAddress((String) myCustomer.get(JSON_CUSTOMER_ADDRESS));
			this.setName((String) myCustomer.get(JSON_CUSTOMER_NAME));
			this.setSortcode((String) myCustomer.get(JSON_SORT_CODE));
			this.setCreditScore((String) myCustomer.get(JSON_CUSTOMER_CREDIT_SCORE));
			this.setCreditScoreReviewDate(sortOutDate((String) myCustomer.get(JSON_CUSTOMER_REVIEW_DATE)));
			String customerNoString = (String) myCustomer.get(JSON_ID);

			this.setCustomerNumber(customerNoString);
		}
		else
		{
			myCustomerResponse.close();
			return false;
		}
		myCustomerResponse.close();
		return true;	
	}

	
	public String addToDB(){
		CustomerResource myCustomerResource = new CustomerResource();

		CustomerJSON myCustomerJSON = new CustomerJSON();

		myCustomerJSON.setCustomerAddress(this.getAddress());
		myCustomerJSON.setCustomerName(this.getName());
		myCustomerJSON.setDateOfBirth(this.getDob());
		myCustomerJSON.setSortCode(this.getSortcode());
		Response myCustomerResponse = myCustomerResource.createCustomerExternal(myCustomerJSON);

		String myCustomerString = null;
		JSONObject myCustomer = null;

		if(myCustomerResponse.getStatus() == 201)
		{
			myCustomerString = myCustomerResponse.getEntity().toString();
			try {
				myCustomer = JSONObject.parse(myCustomerString);
			} catch (IOException e) {
				logger.severe(e.toString());
				myCustomerResponse.close();
				return "-1";
			}

			this.setDob(sortOutDate((String) myCustomer.get(JSON_DATE_OF_BIRTH)));
			this.setAddress((String) myCustomer.get(JSON_CUSTOMER_ADDRESS));
			this.setName((String) myCustomer.get(JSON_CUSTOMER_NAME));
			this.setSortcode((String) myCustomer.get(JSON_SORT_CODE));
		
			String customerNoString = (String) myCustomer.get(JSON_ID);
			this.setCustomerNumber(customerNoString);
			myCustomerResponse.close();
			return  customerNoString;
		}
		else
		{
			logger.severe(myCustomerResponse.getStatus() + " " + myCustomerResponse.getEntity().toString());
			myCustomerResponse.close();
			return "-1";
		}
	}

	/** inDB
	 * 
	 * Checks if the customer is in the database
	 * 
	 * @return
	 */
	public boolean inDB(){
		CustomerResource myCustomerResource = new CustomerResource();

		Response myCustomerResponse = null;
		String myCustomerString = null;
		JSONObject myCustomer = null;

		myCustomerResponse = myCustomerResource.getCustomerExternal(Long.parseLong(this.customerNumber));
		if(myCustomerResponse.getStatus() == 200)
		{
			myCustomerString = myCustomerResponse.getEntity().toString();
			try {
				myCustomer = JSONObject.parse(myCustomerString);
			} catch (IOException e) {
				logger.severe(e.toString());
				myCustomerResponse.close();
				return false;
			}

			this.setDob(sortOutDate((String) myCustomer.get(JSON_DATE_OF_BIRTH)));

			String customerNoString = (String) myCustomer.get(JSON_ID);
			this.setCustomerNumber(customerNoString);
			this.setAddress((String) myCustomer.get(JSON_CUSTOMER_ADDRESS));
			this.setName((String) myCustomer.get(JSON_CUSTOMER_NAME));
			this.setSortcode((String) myCustomer.get(JSON_SORT_CODE)); 	
			myCustomerResponse.close();
			return true;
		}
		myCustomerResponse.close();
		return false;
	}

	/** sortOutDate
	 * 
	 * Returns a correctly formatted java date from the one input
	 * 
	 * @param dateString
	 * @return
	 */
	private Date sortOutDate(String dateString) {
		String[] dateArray = dateString.split("-");

		Integer year = new Integer(dateArray[0]);
		Integer month = new Integer(dateArray[1]);
		Integer day = new Integer(dateArray[2]);

		return new Date(year - 1900,month - 1, day);
	}

	
	/** showInfo
	 * 
	 * Displays all the info stored about the customer
	 * Will show a credit score if the customer is being edited
	 * 
	 */
	public void showInfo() {
		
		if(!editingCustomer)
		{
			logger.info(dashes+ this.customerNumber+":"+this.sortcode+dashes);
			logger.info("Sortcode - "+ this.sortcode);
			logger.info("Customer name - "+this.getName());
			logger.info("Customer address - " +this.getAddress());
			logger.info("Customer Date of Birth - "+ this.getDob().toString());
			logger.info("Customer is new");
		}
		else 
		{
			logger.info(dashes+ this.customerNumber+":"+this.sortcode+dashes);
			logger.info("Sortcode - "+ this.sortcode);
			logger.info("Customer name - "+this.getName());
			logger.info("Customer address - " +this.getAddress());
			logger.info("Customer Date of Birth - "+ this.getDob().toString());
			logger.info("Customer credit score - " + this.getCreditScore());
			logger.info("Customer cs review date - "+ this.getCreditScoreReviewDate().toString());
			logger.info("Customer is being edited");
		}
	}
	
	private static void sortOutLogging()
	{
		try 
		{
			LogManager.getLogManager().readConfiguration();
		} 
		catch (SecurityException | IOException e) 
		{
			logger.severe(e.toString());
		} 
	}


}