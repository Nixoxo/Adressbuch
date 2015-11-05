package de.dhbw.webengineering.addressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressList {
	
	private String search = "";

	public List<Address> getList() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {

		Connection conn = AddressBookConnectionFactory.createConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM address " +
					 "WHERE name like '%"+search+"%' or christianname like '%"+search+"%' or "
					 	 + "email like '%"+search+"%'"; 
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Address> list = new ArrayList<Address>();
		while (rs.next()) {
			list.add(new Address(rs));
		}
		rs.close();
		stmt.close();
		conn.close();		
		return list;
	}
	
	public boolean delete(long id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean done = true;
		Connection conn = AddressBookConnectionFactory.createConnection();
		Statement stmt = conn.createStatement();
		String sql = "DELETE FROM address WHERE id=" + id;
		stmt.execute(sql);
		stmt.close();
		conn.close();
		return done;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
