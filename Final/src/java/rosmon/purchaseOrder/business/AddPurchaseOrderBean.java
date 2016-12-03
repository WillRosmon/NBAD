import java.util.List;

public class AddPurchaseOrderBean {

	PurchaseOrder purchaseOrder;
	
	public AddPurchaseOrderBean() {
		purchaseOrder = null;
	}
	
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}
	
	public List<ValidatedReturn> addPurchaseOrder() {
		Validator validator = new Validator();
		List<ValidatedReturn> errorList;
		errorList = validator.validateTransaction(this.purchaseOrder);
		if(errorList.isEmpty()) {
			//add PO to DB
		}
		
		return errorList;
	}
	
	public List<ValidatedReturn> addPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
		return this.addPurchaseOrder();
	}
	

}
