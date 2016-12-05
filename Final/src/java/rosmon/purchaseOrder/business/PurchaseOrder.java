package rosmon.purchaseOrder.business;

import rosmon.utilities.Transaction;

public class PurchaseOrder extends Transaction {

    double amountRemaining;
    int poSequenceNumber;

    public PurchaseOrder() {
        amountRemaining = 0;
    }

    public double getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(double amountRemaining) {
        this.amountRemaining = amountRemaining;
    }
    
    public int getPOSequenceNumber() {
        return this.poSequenceNumber;
    }
    
    public void setPOSequenceNumber(int sequenceNumber) {
        this.poSequenceNumber = sequenceNumber;
    }
}
