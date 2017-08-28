package org.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Query;

import org.dom4j.Branch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Solution1 {
	@ElementCollection
	
	private static void save(Stock stock) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		session.close();
	}
	
	private static void addProduct() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stock stock = new Stock();
		System.out.println("PRODUCT NAME :");
		stock.setProductName(br.readLine());
		System.out.println("QUANTITY :");
		stock.setQuantity(Integer.valueOf(br.readLine()));
		System.out.println("PRICE :");
		stock.setPrice(Float.valueOf(br.readLine()));
		System.out.println("MANUFACTURE DATE :");
		stock.setManufactureDate(br.readLine());
		save(stock);
	}
	
	private static void viewAll() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Query query = session.createQuery("from Stock");
		List<Stock> list = query.getResultList();
		for(Stock each:list) {
			System.out.println(each);
		}
	}
	
	private static void updateProduct() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		System.out.println("Enter Product ID:");
		int pid = Integer.valueOf(br.readLine());
		try {
			Stock stock = session.get(Stock.class, pid);
			System.out.println("PRODUCT NAME :");
			stock.setProductName(br.readLine());
			System.out.println("QUANTITY :");
			stock.setQuantity(Integer.valueOf(br.readLine()));
			System.out.println("PRICE :");
			stock.setPrice(Float.valueOf(br.readLine()));
			System.out.println("MANUFACTURE DATE :");
			stock.setManufactureDate(br.readLine());
			save(stock);
		}
		catch(Exception e) {
			System.out.println("PRODUCT ID NOT AVAILABLE");
		}
		
	}
	
	private static void deleteProduct() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		System.out.println("Enter Product ID (to delete):");
		int pid = Integer.valueOf(br.readLine());
		try {
			Stock stock = session.get(Stock.class, pid);
			session.delete(stock);
			save(stock);
		}
		catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("********* STOCK MANAGEMENT *********");
		Login login = new Login();
		Authenticate au = new Authenticate();
		System.out.println("USERNAME :");
		login.setUserName(br.readLine());
		System.out.println("PASSWORD :");
		login.setPassword(br.readLine());
		if (!au.checkUser(login)) {
			System.out.println("INVALID USER");
			System.exit(0);
		}
		while(true) {
			System.out.println("1.Add Product\n2.View All\n3.Update Product\n4.Delete Product\n5.Exit");
			switch(Integer.valueOf(br.readLine())) {
			case 1: addProduct();
					break;
			case 2: viewAll();
					break;
			case 3: updateProduct();
					break;
			case 4: deleteProduct();
					break;
			case 5: System.out.println("***BYE***");
					System.exit(0);
					break;
			default:System.out.println("Invaid Input");
				
			}
		}
	}
	
}
