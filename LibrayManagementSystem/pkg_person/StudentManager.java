package pkg_person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
	ObjectOutputStream oos_student = null;
	ObjectInputStream ois_student = null;
	
	File student_file = null;
	
	ArrayList<Student> student_list  = null;
	
	@SuppressWarnings("unchecked")
	public StudentManager() {
		student_file  = new File("Student.dat");
		student_list = new ArrayList<Student>();
		if(student_file.exists()) {
			try {
				ois_student = new ObjectInputStream(new FileInputStream(student_file));
				student_list = (ArrayList<Student>) ois_student.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void addAStudent(Student student) {	
		student_list.add(student);
	}
	public Student get(int rollNo) {
		for(Student student : student_list) {
			if(student.getRollNo() == rollNo) {
				return student;
			}
		}
		return null;
	}
	public void viewAllStudent() {
		for(Student s:student_list) {
			System.out.println(s);
		}
	}
	public boolean deleteStudent(int rollno) {
		ListIterator<Student> si = (ListIterator<Student>) student_list.listIterator();
		while(si.hasNext()) {
			Student s = si.next();
			if(s.getRollNo() == rollno) {
				student_list.remove(s);
				return true;
			}
		}
		return false;
	}
	public boolean updateaStudent(int updaterollno,String name, String emailId, String phoneNumber, String address, String dob, int std,String divison) {
		ListIterator<Student> si = (ListIterator<Student>) student_list.listIterator();
		while(si.hasNext()) {
			Student s = si.next();
			if(s.getRollNo() == updaterollno) {
				s.setAddress(address);
				s.setDivison(divison);
				s.setDob(dob);
				s.setEmailId(emailId);
				s.setName(name);
				s.setPhoneNumber(phoneNumber);
				s.setStd(std);
				return true;
			}
		}
		return false;
	}
	public void writeToFile() {
		try {
			oos_student = new ObjectOutputStream(new FileOutputStream(student_file));
			oos_student.writeObject(student_list);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
