package pkg_main;

import java.util.Scanner;

import pkg_book.Book;
import pkg_book.BookManager;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNotFoundException;
import pkg_person.Student;
import pkg_person.StudentManager;
import pkg_transaction.BookTransactionManager;

public class Main {
	public static void main(String args[]) {
		int choice;
		Scanner sc = new Scanner(System.in);
		
		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager();
		
		do {
			System.out.println("Enter 1 if Student\nEnter 2 if Librarian\nEnter 3 to Exit");
			choice = sc.nextInt();
			
			if(choice == 1) {    // for Student
				System.out.println("Enter Your Roll Number");
				int rollno = sc.nextInt();
				try {
					Student s = sm.get(rollno);
					if(s == null) {
						throw new StudentNotFoundException();
					}
					int stud_choice;
					
					do {
						System.out.println("Enter 1 to View All Books\nEnter 2 to Search Book by ISBN\n"
								+ "Enter 3 to List Books by Subject\nEnter 4 to Issue a Book\n"
								+ "Enter 5 to Return a Book\nEnter 99 to LogOut");
						stud_choice = sc.nextInt();
						
						switch(stud_choice) {
						case 1:
							System.out.println("View All Selected");
							bm.viewAllBooks();
							break;
						case 2:
							System.out.println("Please Enter ISBN to search");
							int search_isbn = sc.nextInt();
							Book thisbook = bm.searchBokbyisbn(search_isbn);
							if(thisbook == null) {
								System.out.println("No Book with this ISBN Exists in our Libarary");
							}else {
								System.out.println(thisbook);
							}
							break;
						case 3:
							System.out.println("Enter the Subject to Search");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBookbySubject(search_subject);
							break;
						case 4:
							System.out.println("Enter the ISBN to Issue a Book");
							int issue_isbn  =sc.nextInt();
							Book book = bm.searchBokbyisbn(issue_isbn);
							try {
								if(book == null) {
									throw new BookNotFoundException();
								}
								if(book.getAvailable() > 0) {
									if(btm.issueBook(rollno, issue_isbn)) {
										book.setAvailable(book.getAvailable() -1);
										System.out.println("Book has been Issued");
									}else {
										System.out.println("You have already issued 3 Books\nYou can issue after Return a Books");
									}
								}else {
									System.out.println("The Book is not Available in Libarary");
								}
							}catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							break;
						case 5:
							System.out.println("Enter ISBN to Return a Book");
							int return_isbn = sc.nextInt();
							try {
								book = bm.searchBokbyisbn(return_isbn);
								if(book == null) {
									throw new BookNotFoundException();
								}
								if(btm.returnBook(rollno, return_isbn)) {
									book.setAvailable(book.getAvailable()+1);
									System.out.println("Thank You for Returning the Book");
								}else {
									System.out.println("Could'nt Return the Book");
								}
							}catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							break;
						case 99:
							System.out.println("Thank You for Using Library");
							break;
						default:
							System.out.println("Invalid Number");
						}
						
						
					}while(stud_choice != 99);
					
				}catch(StudentNotFoundException e) {
					System.out.println(e);
				}
			}
			else if(choice == 2) {
				int lib_choice;
			do {
				System.out.println("Enter 11 to View all Students\nEnter 12 to Print a Sutdent by Roll Number\n"
						+ "Enter 13 to Register a Student\nEnter 14 to Update a Student\n"
						+ "Enter 15 to Delete a Student\n");
				System.out.println("Enter 21 to View all Books\nEnter 22 to Print a Book by ISBN\n"
						+ "Enter 23 to Add a Book\nEnter 24 to Update a Book\nEnter 25 to Delete a Book");
				System.out.println("Enter 31 to View all Transaction\nEnter 99 to LogOut");
				lib_choice = sc.nextInt();
				
				switch(lib_choice) {
				case 11:     // view all students
					System.out.println("All the Students Records");
					sm.viewAllStudent();
					break;
				case 12:     // Search a student based on roll number
					System.out.println("Enter Roll Number to Fetch Students's Records");
					int get_rollno = sc.nextInt();
					Student student = sm.get(get_rollno);
					if(student == null) {
						System.out.println("No Records Matchs this Roll Number");
					}else {
						System.out.println(student);
					}
					break;
				case 13:     // Add a Student
					System.out.println("Enter Students Details to Add");
					String name;
					String emailid;
					String phonenumber;
					String address;
					String dob;
					int rollno;
					int std;
					String divison;
					sc.nextLine();
					System.out.println("Name");
					name = sc.nextLine();
					System.out.println("Email ID");
					emailid = sc.nextLine();
					System.out.println("Phone Number");
					phonenumber = sc.nextLine();
					System.out.println("Address");
					address = sc.nextLine();
					System.out.println("Date of Birth");
					dob = sc.nextLine();
					System.out.println("Roll Number");
					rollno = sc.nextInt();
					System.out.println("Standard");
					std = sc.nextInt();
					sc.nextLine();
					System.out.println("Division");
					divison = sc.nextLine();
					
					student = new Student( name,  emailid,  phonenumber,  address,  dob,  rollno,  std,divison);
					sm.addAStudent(student);
					System.out.println("Student is Added");
					break;
				case 14:      // update a student
					System.out.println("Enter the Roll Number to Modify the Record");
					int modifyrollno = sc.nextInt();
					student = sm.get(modifyrollno);
					try {
						if(student == null) {
							throw new StudentNotFoundException();
						}
						
						sc.nextLine();
						System.out.println("Name");
						name = sc.nextLine();
						System.out.println("Email ID");
						emailid = sc.nextLine();
						System.out.println("Phone Number");
						phonenumber = sc.nextLine();
						System.out.println("Address");
						address = sc.nextLine();
						System.out.println("Date of Birth");
						dob = sc.nextLine();
						System.out.println("Standard");
						std = sc.nextInt();
						sc.nextLine();
						System.out.println("Division");
						divison = sc.nextLine();
					
						sm.updateaStudent(modifyrollno, name, emailid, phonenumber, address, dob, std, divison);
						System.out.println("Student Record is Updated");
					}catch(StudentNotFoundException snfe) {
						System.out.println(snfe);
					}
					break;
				case 15:  // delete a student
					System.out.println("Enter Roll Number to Delete Student");
					int delte_rollno = sc.nextInt();
					if(sm.deleteStudent(delte_rollno)) {
						System.out.println("Deleted Successfully");
					}else {
						System.out.println("No Records with Given Roll Number Exists");
					}
					break;
				
				case 21:  // view all books
					bm.viewAllBooks();
					break;
				case 22:     // search by isbn
					System.out.println("Enter ISBN of the Book for search");
					int search_isbn = sc.nextInt();
					Book thisbook = bm.searchBokbyisbn(search_isbn);
					if(thisbook == null) {
						System.out.println("No Book with this ISBN Exists in our Libarary");
					}else {
						System.out.println(thisbook);
					}
					break;
				case 23:      // adding new book
					System.out.println("Please Enter Book Details to Add");
					 int isbn;
					 String title;
					 String author;
					 String publisher;
					 int edition;
					 String subject;
					 int available;
					 
					 sc.nextLine();
					 System.out.println("ISBN");
					 isbn = sc.nextInt();
					 
					 sc.nextLine();
					 
					 System.out.println("Title");
					 title = sc.nextLine();
					 System.out.println("Author");
					 author = sc.nextLine();
					 System.out.println("Publisher");
					 publisher = sc.nextLine();
					 System.out.println("Edition");
					 edition = sc.nextInt();
					 
					 sc.nextLine();
					 
					 System.out.println("Subject");
					 subject = sc.nextLine();
					 System.out.println("Quantity");
					 available = sc.nextInt();
					 
					 Book book = new Book( isbn,  title,  author,  publisher,  edition,  subject,  available);
					 bm.addABook(book);
					 System.out.println("A Book Record is Added");
					 break;
				case 24:   // update a record of book
					System.out.println("Please Enter the ISBN to update the record");
					int update_book = sc.nextInt();
					try {
						book = bm.searchBokbyisbn(update_book);
						if(book == null) {
							throw new BookNotFoundException();
						}
						System.out.println("Please Enter Book Details to Modify");
						
						 sc.nextLine();
						
						 System.out.println("Title");
						 title = sc.nextLine();
						 System.out.println("Author");
						 author = sc.nextLine();
						 System.out.println("Publisher");
						 publisher = sc.nextLine();
						 System.out.println("Edition");
						 edition = sc.nextInt();
						 
						 sc.nextLine();
						 
						 System.out.println("Subject");
						 subject = sc.nextLine();
						 System.out.println("Quantity");
						 available = sc.nextInt();
						 
						 bm.updateBook(update_book, title, author, publisher, edition, subject, available);
						 System.out.println("A Book Record is Updated");
					}catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					 break;
				case 25:      // Delete a record of book
					System.out.println("Please Enter the ISBN to Delete the record");
					int delete_isbn = sc.nextInt();
					try {
						book = bm.searchBokbyisbn(delete_isbn);
						if(book == null) {
							throw new BookNotFoundException();
						}
						bm.deleteBook(delete_isbn);
						System.out.println("Deleted Successfully");
					}catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					break;
				case 31:   // view all transaction
					System.out.println("All the Transaction are :- ");
					btm.showAll();
					break;
				case 99:
					System.out.println("Thank for Using Library");
					break;
				default:
					System.out.println("Invalid Choice");
				}
			  }while(lib_choice != 99);
			}
			
		}while(choice != 3);
		sm.writeToFile();
		btm.writeToFile();
		bm.writeToFile();
		sc.close();
		
	}
}
