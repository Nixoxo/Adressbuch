package de.dhbw.webengineering.addressbook;

import java.sql.SQLException;
import java.util.List;

public class AddressListTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		AddressList list = new AddressList();
		List<Address> addresses = list.getList();
		
		for (Address address : addresses) {
			System.out.print(address.getName()+"   ");
			System.out.print(address.getChristianname()+"   ");
			System.out.print(address.getEmail()+"   ");
			System.out.print(address.getAddressform()+"   ");
			System.out.print(address.getPhone()+"   ");
			System.out.print(address.getMobile()+"   ");
			System.out.print(address.getStreet()+"   ");
			System.out.print(address.getNumber()+"   ");
			System.out.print(address.getCity()+"   ");
			System.out.print(address.getPostcode()+"   ");
			System.out.print(address.getCountry()+"   ");
			System.out.print(address.getBirthday()+"   ");
			System.out.println();
		}
	}

}
