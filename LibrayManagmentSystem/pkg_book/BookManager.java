package pkg_book;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BookManager {
	ObjectOutputStream oos_book = null;
	ObjectInputStream ois_book = null;
	
	File book_file = new File("Book.dat");
	
	ArrayList<Book> book_list = null;
	
	@SuppressWarnings("unchecked")
	public BookManager() {
		book_list = new ArrayList<Book>();
		if(book_file.exists()) {
			try {
				ois_book = new ObjectInputStream(new FileInputStream(book_file));
				book_list = (ArrayList<Book>) ois_book.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void listBookbySubject(String subjecct) {
		for(Book book:book_list) {
			if(book.getSubject().equals(subjecct)) {
				System.out.println(book);
			}
		}
	}
	public void addABook(Book book) {
		book_list.add(book);
	}
	public void viewAllBooks() {
		for(Book book:book_list) {
			System.out.println(book);
		}
	}
	public Book searchBokbyisbn(int search_isbn) {
		for(Book book: book_list) {
			if(book.getIsbn() == search_isbn) {
				return book;
			}
		}
		return null;
	}
	public boolean deleteBook(int deleteisbn) {
		ListIterator<Book> bookiter = (ListIterator<Book>) book_list.listIterator();
		while(bookiter.hasNext()) {
			Book book = bookiter.next();
			if(book.getIsbn() == deleteisbn) {
				book_list.remove(book);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateBook(int update_isbn ,String title, String author, String publisher, int edition, String subject, int available) {
		ListIterator<Book> bookiter = (ListIterator<Book>) book_list.listIterator();
		while(bookiter.hasNext()) {
			Book book = bookiter.next();
			if(book.getIsbn() == update_isbn) {
				book.setAuthor(author);
				book.setAvailable(available);
				book.setEdition(edition);
				book.setPublisher(publisher);
				book.setTitle(title);
				book.setSubject(subject);
				return true;
			}
		}
		return false;
	}
	
	public void writeToFile() {
		try {
			oos_book = new ObjectOutputStream(new FileOutputStream(book_file));
			oos_book.writeObject(book_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
