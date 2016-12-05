package rosmon.purchase.business;

import rosmon.purchaseOrder.business.PurchaseOrder;
import rosmon.utilities.Transaction;

public class Purchase extends Transaction {

    private PurchaseOrder po;
    private int purchaseSeqNum;
    private String invoiceNum;
    private String paymentNum;

    public PurchaseOrder getPo() {
        return po;
    }

    public void setPo(PurchaseOrder po) {
        this.po = po;
    }

    public int getPurchaseSeqNum() {
        return purchaseSeqNum;
    }

    public void setPurchaseSeqNum(int purchaseSeqNum) {
        this.purchaseSeqNum = purchaseSeqNum;
    }
    
    public String getInvoiceNum() {
        return this.invoiceNum;
    }
    
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
    
    public String getPaymentNum() {
        return this.paymentNum;
    }
    
    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }
}
