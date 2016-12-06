/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rosmon.purchaseOrder.business;

import java.util.List;
import rosmon.data.accessors.PurchaseOrderAccessor;

/**
 *
 * @author wrosmon
 */
public class PurchaseOrderBean {
    
    public List<PurchaseOrder> findByVendor(int vendorId) {
        return PurchaseOrderAccessor.selectAllPurchaseOrdersByVendor(vendorId);
    }
    
    public List<PurchaseOrder> selectAll() {
        return PurchaseOrderAccessor.selectAllPurchaseOrders();
    }
}
