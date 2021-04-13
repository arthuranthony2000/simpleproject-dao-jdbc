package application;

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
		
		System.out.println("### TEST 2: sellers findByDepartment ###");
		Department department = new Department(1, null);
		List<Seller> list = sellerDao.findByDeparment(department);
		list.forEach(System.out::println);
		
		System.out.println("### TEST 3: sellers findAll ###");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
	}
}
