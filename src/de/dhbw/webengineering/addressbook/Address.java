package de.dhbw.webengineering.addressbook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Address {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.uu", Locale.GERMANY);

	private long id;
	private boolean valid;
	private String name;
	private String christianname;
	private String email;
	private String addressform;
	private String phone;
	private String mobile;
	private String street;
	private String number;
	private long numberValue;
	private boolean numberError;
	private String numberErrorMessage;
	private String city;
	private String postcode;
	private String country;
	private String birthday;
	private Date birthdayValue;
	private boolean birthdayError;
	private String birthdayErrorMessage;
	
	private void init() {
		this.id=-1;
		this.valid = true;
		this.name="";
		this.christianname="";
		this.email="";
		this.addressform="";
		this.phone="";
		this.mobile="";
		this.street="";
		this.number="";
		this.numberValue=0;
		this.numberError=false;
		this.numberErrorMessage=null;
		this.numberValue=0;
		this.city="";
		this.postcode="";
		this.country="";
		this.birthday="01.01.1970";
		try {
			this.birthdayValue=new Date(dateFormat.parse(birthday).getTime());
		} catch (ParseException e) {
			// This should not happen!
			e.printStackTrace();
		}
		this.birthdayError=false;
		this.birthdayErrorMessage="";
	}
	
	private void read(long i)  {
		if (i==-1) {
			// Auf default-Werte setzen
			init();
		} else {
			Connection conn;
			Statement stmt;
			ResultSet rs;
			try {
				conn = AddressBookConnectionFactory.createConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM address WHERE ID=" + i);
				if (rs.next()) {
					readAddressFromResultset(rs);
					this.valid = true;
				} else {
					init();
					this.valid = false;
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				init();
				this.valid=false;
				//TODO close rs, stmt and conn
				//TODO log stacktrace
			}
		}
	}
	
	private void readAddressFromResultset(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.id=rs.getLong("id");
		this.name=rs.getString("name");
		this.christianname=rs.getString("christianname");
		this.email=rs.getString("email");
		this.addressform=rs.getString("addressform");
		this.phone=rs.getString("phone");
		this.mobile=rs.getString("mobile");
		this.street=rs.getString("street");
		this.number=rs.getString("number");
		this.numberValue=rs.getLong("number");
		this.city=rs.getString("city");
		this.postcode=rs.getString("postcode");
		this.country=rs.getString("country");
		this.birthday=dateFormat.format(rs.getDate("birthday"));
		this.birthdayValue=rs.getDate("birthday");
	}

	public Address() {
		dateFormat.setLenient(true);
		init();
	}
	
	public Address(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this();
		readAddressFromResultset(rs);
	}

	public boolean save() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean done = false;
		Connection conn = AddressBookConnectionFactory.createConnection();
		Statement stmt = conn.createStatement();
		if (id==-1) {
			String sql = "INSERT INTO address (name, christianname, email, addressform, "
											+ "phone, mobile, street, number, city, postcode, "
											+ "country, birthday) "
					+ "VALUES  (\"" + name + "\", " +
					 "\"" + christianname + "\", " +
					 "\"" + email + "\", " +
					 "\"" + addressform + "\", " +
					 "\"" + phone + "\", " +
					 "\"" + mobile + "\", " +
					 "\"" + street + "\", " +
					 "" + numberValue + ", " +
					 "\"" + city + "\", " +
					 "\"" + postcode + "\", " +
					 "\"" + country + "\", " +
					 "\"" + birthdayValue + "\" );";	
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
				done=true;
			}
		} else {
			String sql = "UPDATE address SET  name=\"" + name + "\", " +
					 "christianname=\"" + christianname + "\", " +
					 "email=\"" + email + "\", " +
					 "addressform=\"" + addressform + "\", " +
					 "phone=\"" + phone + "\", " +
					 "mobile=\"" + mobile + "\", " +
					 "street=\"" + street + "\", " +
					 "number=" + numberValue + ", " +
					 "city=\"" + city + "\", " +
					 "postcode=\"" + postcode + "\", " +
					 "country=\"" + country + "\", " +
					 "birthday=\"" + birthdayValue + "\" " +
					 "WHERE ID=" + id;
			stmt.execute(sql);
			done=true;
		}
		stmt.close();
		conn.close();
		return done;
	}
	
	public boolean hasError() {
		return numberError || birthdayError;
	}

	public List<String> getErrorMessages() {
		List<String> res = new ArrayList<String>();
		if (numberError) res.add(numberErrorMessage);
		if (birthdayError) res.add(birthdayErrorMessage);
		return res;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id!=this.id) {
			read(id);
		}
	}

	public String getName() {
		return name;
	}

	public boolean isValid() {
		return valid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChristianname() {
		return christianname;
	}

	public void setChristianname(String christianname) {
		this.christianname = christianname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressform() {
		return addressform;
	}

	public void setAddressform(String addressform) {
		this.addressform = addressform;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
		try {
			numberValue = Integer.parseInt(number);
			numberError=false;
			numberErrorMessage=null;
		} catch (NumberFormatException e) {
			numberErrorMessage="Nummer ist keine gültige Zahl.";
			numberError=true;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
		try {
			birthdayValue = new Date(dateFormat.parse(birthday).getTime()); 
			birthdayError=false;
			birthdayErrorMessage=null;
		} catch (ParseException e) {
			birthdayErrorMessage="Das Geburtsdatum ist kein gültiges Datum.";
			birthdayError=true;
		}
	}

}
