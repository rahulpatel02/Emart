package co.emart.model;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String userType;
	private int userCounts;
	private int orederCount;





	public User() {

	}








	public User(int id, String name, String email, String password, String userType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getOrederCount() {
		return orederCount;
	}



	public void setOrederCount(int orederCount) {
		this.orederCount = orederCount;
	}


	public int getUserCounts() {
		return userCounts;
	}

	public void setUserCounts(int userCounts) {
		this.userCounts = userCounts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", userType="
				+ userType + "]";
	}



}
