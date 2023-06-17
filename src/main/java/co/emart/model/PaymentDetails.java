package co.emart.model;

public class PaymentDetails {
    private int amount;
    private String orderId;
    private String status;
    
	public PaymentDetails() {
		super();
	}

	public PaymentDetails(int amount, String orderId, String status) {
		super();
		this.amount = amount;
		this.orderId = orderId;
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PaymentDetails [amount=" + amount + ", orderId=" + orderId + ", status=" + status + "]";
	}
	
    
}
