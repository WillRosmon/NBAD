
public class PurchaseOrder extends Transaction {


	double amountRemaining;
	
	public PurchaseOrder() {
		amountRemaining = 0;
	}

	public double getAmountRemaining() {
		return amountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}
}
