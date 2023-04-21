package co.emart.model;

public class AddProduct {
    private String pName;
    private int pPrice;
    private String pDesc;
    private int pCategory;
    private String pPic;


    public AddProduct() {

    }


	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	public int getpCategory() {
		return pCategory;
	}
	public void setpCategory(int pCategory) {
		this.pCategory = pCategory;
	}
	public String getpPic() {
		return pPic;
	}
	public void setpPic(String pPic) {
		this.pPic = pPic;
	}


	@Override
	public String toString() {
		return "AddProduct [pNmae=" + pName + ", pPrice=" + pPrice + ", pDesc=" + pDesc + ", pCategory=" + pCategory
				+ ", pPic=" + pPic + "]";
	}



}
