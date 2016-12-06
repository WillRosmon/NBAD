package rosmon.purchaseOrder.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rosmon.purchaseOrder.business.PurchaseOrder;
import rosmon.purchaseOrder.business.AddPurchaseOrderBean;
import rosmon.purchaseOrder.business.PurchaseOrderBean;
import rosmon.purchaseOrder.business.PurchaseOrderUpdateBean;

import rosmon.utilities.SessionConstants;
import rosmon.utilities.ValidatedReturn;
import rosmon.vendors.business.VendorBean;

/**
 * Servlet implementation class AddPOServlet
 */
@WebServlet("/POs")
public class POServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("getByVendor")) {
            PurchaseOrderBean poBean = new PurchaseOrderBean();
            try {
                int intId = Integer.parseInt(id);
                List<PurchaseOrder> poList = poBean.findByVendor(intId);
                request.setAttribute("poList", poList);
                getServletContext().getRequestDispatcher("/purchaseOrders.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                System.out.println("ID Not entered as a number");
            }
        } else if (id.equalsIgnoreCase("all")) {
            PurchaseOrderBean poBean = new PurchaseOrderBean();
            List<PurchaseOrder> poList = poBean.selectAll();
            request.setAttribute("poList", poList);
            getServletContext().getRequestDispatcher("/purchaseOrders.jsp").forward(request, response);
        } else {
            //look up purchase order in database
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            request.setAttribute("purchase_order", purchaseOrder);
            getServletContext().getRequestDispatcher("/viewPurchaseOrder.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        double amount = 0;
        try {
            amount = Double.parseDouble(request.getParameter("amount"));
            String userId = (String) request.getSession().getAttribute(SessionConstants.USER_ID);
            purchaseOrder.setUserId(userId);
            purchaseOrder.setAmount(amount);
            purchaseOrder.setAmountRemaining(amount);
            purchaseOrder.setVendorId(request.getParameter("vendorId"));

            AddPurchaseOrderBean poBean = new AddPurchaseOrderBean();
            List<ValidatedReturn> errorList = poBean.addPurchaseOrder(purchaseOrder);

            if (errorList.isEmpty()) {
                getServletContext().getRequestDispatcher("/purchaseOrders").forward(request, response);
            } else {
                request.setAttribute("failure", errorList);
                request.setAttribute("purchase_order", purchaseOrder);
                PrintWriter out = response.getWriter();
                out.println("Error adding PurchaseOrder");
                getServletContext().getRequestDispatcher("/purchaseOrders.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            amount = 0;
            PrintWriter out = response.getWriter();
            out.println("Amount was not a valid number");
            getServletContext().getRequestDispatcher("/purchaseOrders.jsp").forward(request, response);
        }
    }

    @Override
    /**
     * Allows Put operations from web service to be processed for Purchase
     * Orders Only allows the amount of a purchase order to be changed This
     * would be used to increase the funds of a PO, or set the funds to 0
     * Additionally, this can be used to merge two POs into a single PO
     */
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("change_amount")) {
            String purchaseOrderId = req.getParameter("purchase_order_number");
            // look up Purchase Order in Database
            double newAmount = 0;
            try {
                newAmount = Double.parseDouble((String) req.getParameter("new_amount"));
                String userId = (String) req.getSession().getAttribute("user");
                String companyId = (String) req.getSession().getAttribute("company_id");
                // validate
            } catch (NumberFormatException e) {
                newAmount = 0;
                PrintWriter out = resp.getWriter();
                out.println("New Amount was not entered as a number");
            }
        } else if (action.equalsIgnoreCase("merge")) {
            String firstPo = req.getParameter("po_one");
            String secondPo = req.getParameter("po_two");
            String userId = (String) req.getSession().getAttribute("user");
            String companyId = (String) req.getSession().getAttribute("company_id");

            PurchaseOrder purchaseOrder = new PurchaseOrder();

            //get both POs from DB
            //validate both are for same vendor
            //combine POs
        }
    }
}
