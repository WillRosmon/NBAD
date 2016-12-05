/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rosmon.vendors.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rosmon.vendors.business.Vendor;
import rosmon.vendors.business.VendorBean;

/**
 *
 * @author wrosmon
 */
@WebServlet(name = "VendorServlet", urlPatterns = {"/vendors"})
public class VendorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VendorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VendorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vendorId = request.getParameter("id").toString();
        VendorBean vendorBean = new VendorBean();
        String url = null;

        if (!vendorId.equalsIgnoreCase("all")) {
            Vendor vendor = null;
            vendor = vendorBean.selectVendor(vendorId);
            if (vendor != null) {
                request.setAttribute("vendor", vendor);
            } else {
                request.setAttribute("error", "Error Loading Vendor Information");
            }
            url = "/vendor.jsp";
        } else {
            List<Vendor> vendorsList = vendorBean.selectAllVendors();
            request.setAttribute("vendorList", vendorsList);
            url = "/vendorList.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vendor vendor = new Vendor();
        vendor.setVendorStatus("P");
        String vendorName = request.getParameter("vendorName");
        vendor.setVendorName(vendorName);
        String url = null;
        VendorBean vendorBean = new VendorBean();
        if (vendorBean.addVendor(vendor)) {
            url = "/vendors?id=all";
            
        } else {
            request.setAttribute("Error", "Error Adding Vendor");
            url = "/addVendor.jsp";
            
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
