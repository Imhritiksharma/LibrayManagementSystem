package pkg_person;

import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String emailId;
	protected String phoneNumber;
	protected String address;
	protected String dob;
	
	
	public Person() {
		super();
	}
	public Person(String name, String emailId, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.setEmailId(emailId);
		this.setPhoneNumber(phoneNumber);
		this.address = address;
		this.setDob(dob);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(Pattern.matches("[A-Za-z ]+", name)) {
		    this.name = name;
		}else {
			this.name = "default Name";
		}
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		if(Pattern.matches("^[a-z][a-z\\d._]*[a-z\\d]+@[a-z]+[\\.][a-z]{2,3}$", emailId)) {
			this.emailId = emailId;
		}else {
			this.emailId = "adam@email.com";
		}
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if(Pattern.matches("[6-9][\\d]{9} ", phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}else {
			this.phoneNumber = "91+ **********";
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isvalid = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if(isvalid) {
			this.dob = dob;
		}else {
			this.dob = "01-01-2000";
		}
	}
	
}
