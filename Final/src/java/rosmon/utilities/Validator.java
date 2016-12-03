package rosmon.utilities;

import java.util.ArrayList;
import java.util.List;

import rosmon.purchase.business.Purchase;
import rosmon.purchaseOrder.business.PurchaseOrder;

public class Validator {

    public List<ValidatedReturn> validateTransaction(Transaction transaction) {
        List<ValidatedReturn> errorList = new ArrayList<>();

        if (!isValidAmount(transaction)) {
            errorList.add(new ValidatedReturn(-1, "Invalid Amount"));
        }
        if (transaction instanceof PurchaseOrder) {
            validatePurchaseOrder((PurchaseOrder) transaction, errorList);
        } else if (transaction instanceof Purchase) {
            validatePurchase((Purchase) transaction, errorList);
        }
        return errorList;
    }

    private void validatePurchaseOrder(PurchaseOrder transaction, List<ValidatedReturn> errorList) {
        if (!isValidUser(transaction)) {
            errorList.add(new ValidatedReturn(-1, "You do not have permission to issue a Purchase Order"));
        }
        if (!isValidVendor(transaction)) {
            errorList.add(new ValidatedReturn(-1, "You must add this vendor to your vendor list before issuing a PO"));
        }
    }

    private void validatePurchase(Purchase transaction, List<ValidatedReturn> errorList) {
        PurchaseOrder purchaseOrder = transaction.getPo();

        if (transaction.getVendorId() != purchaseOrder.getVendorId()) {
            errorList.add(new ValidatedReturn(-1, "The Purchase Order Provided is not for that vendor"));
            return;
        }
        if (transaction.getAmount() > purchaseOrder.getAmountRemaining()) {
            errorList.add(new ValidatedReturn(-1, "There are not enough funds remaining to cover that purchase"));
        }
    }

    public List<ValidatedReturn> validateMerge(PurchaseOrder po1, PurchaseOrder po2) {
        List<ValidatedReturn> errorList = new ArrayList<>();
        if (!validateVendors(po1, po2)) {
            errorList.add(new ValidatedReturn(-1, "Purchase Orders do not belong to the same vendor"));
        }

        return errorList;
    }

    private boolean isValidAmount(Transaction transaction) {
        return transaction.getAmount() > 0;
    }

    private boolean isValidUser(Transaction transaction) {
        if (transaction instanceof PurchaseOrder) {
            //ensure User has administrator rights in their organization
        } else if (transaction instanceof Purchase) {
            //ensure User has purchaser rights in their organization
        }

        return true;
    }

    private boolean isValidVendor(Transaction transaction) {
        return true;
    }

    private boolean validateVendors(PurchaseOrder po1, PurchaseOrder po2) {
        return po1.getVendorId().equalsIgnoreCase(po2.getVendorId());
    }
}
