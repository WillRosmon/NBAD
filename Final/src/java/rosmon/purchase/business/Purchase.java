package rosmon.purchase.business;

import rosmon.purchaseOrder.business.PurchaseOrder;
import rosmon.utilities.Transaction;

public class Purchase extends Transaction {

    PurchaseOrder po;
    int receiptNumber;
    int purchaseSeqNum;

    public PurchaseOrder getPo() {
        return po;
    }

    public void setPo(PurchaseOrder po) {
        this.po = po;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getPurchaseSeqNum() {
        return purchaseSeqNum;
    }

    public void setPurchaseSeqNum(int purchaseSeqNum) {
        this.purchaseSeqNum = purchaseSeqNum;
    }
}
