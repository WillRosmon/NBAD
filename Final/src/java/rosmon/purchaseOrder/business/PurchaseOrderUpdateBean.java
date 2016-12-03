
public class PurchaseOrderUpdateBean {
	
	String userId, vendorId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public PurchaseOrder merge(PurchaseOrder po1, PurchaseOrder po2, boolean keepOriginal) {
		return processMerge(po1, po2, keepOriginal);
	}
	
	private PurchaseOrder processMerge(PurchaseOrder po1, PurchaseOrder po2, boolean keepOriginal) {
		
		if(keepOriginal) {
			Validator validator = new Validator();
			
		}
		
		return null;
	}
	
	private PurchaseOrder generateNewPurchaseOrder() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		
		return purchaseOrder;
	}
}
