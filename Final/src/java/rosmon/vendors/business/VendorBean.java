/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rosmon.vendors.business;

import java.util.List;
import rosmon.data.accessors.VendorAccessor;

/**
 *
 * @author wrosmon
 */
public class VendorBean {
    
    private Vendor vendor;
    
    public Vendor getVendor() {
        return this.vendor;
    }
    
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    public Vendor selectVendor(String vendorId) {
        this.vendor = VendorAccessor.selectVendor(vendorId);
        return vendor;
    }
    
    public Vendor addVendor(Vendor vendor) {
        if (vendor.getVendorId() != null) {
            vendor.setVendorId(null);
        }
        this.vendor = VendorAccessor.addVendor(vendor);
        if(this.vendor.getVendorId() != null) {
            return this.vendor;
        } else {
            return vendor;
        }
    }
    
    public List<Vendor> selectAllVendors() {
        return VendorAccessor.selectAllVendors();
    }
    
    public Vendor searchForVendorByName(String vendorName) {
        return VendorAccessor.searchForVendorByName(vendorName);
    }
}
