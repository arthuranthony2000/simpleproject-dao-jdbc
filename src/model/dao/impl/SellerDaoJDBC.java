package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department depto = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, depto);
				return obj;
			}
			return null;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department depto) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(depto);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department depto = new Department();
		depto.setId(rs.getInt("DepartmentId"));
		depto.setName(rs.getString("DepName"));
		return depto;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name"
					);
			
			rs = st.executeQuery();
			
			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> departments = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				Department depto = departments.get(rs.getInt("DepartmentId"));
				if (depto == null) {
					depto = instantiateDepartment(rs);
					departments.put(depto.getId(), depto);
				}
				sellers.add(instantiateSeller(rs, depto));
			}
			return sellers;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDeparment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ?"
					+ "ORDER BY Name"
					);
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> departments = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				Department depto = departments.get(rs.getInt("DepartmentId"));
				if (depto == null) {
					depto = instantiateDepartment(rs);
					departments.put(depto.getId(), depto);
				}
				sellers.add(instantiateSeller(rs, depto));
			}
			return sellers;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
}
