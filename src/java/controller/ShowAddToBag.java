/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;
import model.ProductsDB;

/**
 *
 * @author LENOVO
 */
public class ShowAddToBag extends HttpServlet {
    Map<Products,String> pro = new HashMap<>();
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
            out.println("<title>Servlet ShowAddToBag</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowAddToBag at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        
        switch (action) {
            case "addToBag":
                doAddToBag(request,response);
                break;
            case "showBag":
                doShowBag(request, response);
                break;
            default:
                throw new AssertionError();
        }
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
        processRequest(request, response);
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

    private void doAddToBag(HttpServletRequest request, HttpServletResponse response) {
         String sizeProduct = null;
        String[] checkedValues = request.getParameterValues("checkboxes");
        if (checkedValues != null && checkedValues.length > 0) {
            // Xử lý từng giá trị checkbox được chọn
            for (String value : checkedValues) {
                sizeProduct = value;
                // Xử lý logic dựa trên giá trị của checkbox được chọn
            }
        } else {
            System.out.println("No checkboxes selected.");
            // Xử lý khi không có checkbox nào được chọn
        }
        
       
        String idProduct_String = request.getParameter("id");
        int idProduct = Integer.parseInt(idProduct_String);
        
        try {
            Products products = ProductsDB.getProductById(idProduct);
            
            pro.put(products, sizeProduct);
        } catch (Exception ex) {
            Logger.getLogger(ShowAddToBag.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void doShowBag(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("MapProduct", pro);
            request.getRequestDispatcher("include/shopBag.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ShowAddToBag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowAddToBag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
