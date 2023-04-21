package co.emart.model;

public class Admin {

	private int userCounts;
	private int orederCount;
	private int cId;
	private String categoryName;
	private String description;


	public Admin() {

	 }

	 public int getcId() {
			return cId;
		}

		public void setcId(int cId) {
			this.cId = cId;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	public int getUserCounts() {
		return userCounts;
	}
	public void setUserCounts(int userCounts) {
		this.userCounts = userCounts;
	}
	public int getOrederCount() {
		return orederCount;
	}
	public void setOrederCount(int orederCount) {
		this.orederCount = orederCount;
	}


}
