package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String args[]) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("### TEST 1: seller findById ###");
		System.out.println(sellerDao.findById(1));
		System.out.println();
		
		System.out.println("### TEST 2: sellers findByDepartment ###");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDeparment(department);
		list.forEach(System.out::println);
		System.out.println();
		
		System.out.println("### TEST 3: sellers findAll ###");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		
//		System.out.println("### TEST 4: seller insert ###");
//		Seller obj = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00d, department);
//		sellerDao.insert(obj);
//		System.out.println("Inserted Seller Successful, Id Seller --> "+obj.getId());
//		
		System.out.println("### TEST 5: seller update ###");
		Seller newSeller = sellerDao.findById(5);
		newSeller.setBaseSalary(4000.00d);
		sellerDao.update(newSeller);
	}
}

