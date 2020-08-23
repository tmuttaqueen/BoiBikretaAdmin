/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SupplyBooks {
    public Long supply_id;
    public int line_no;
    public String book_id;
    public int quantity;
    public int price;
        
    public static ArrayList<SupplyBooks> getAllBooks(Long supply_id)
    {
        ArrayList<SupplyBooks> list = new ArrayList<>();
        Connection con = new OracleDBMS().getConnection();
        String getSupply = "Select * from supply_invoice_line where supply_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getSupply);
            ps.setLong(1, supply_id);
            ResultSet rst = ps.executeQuery();
            while (rst.next())
            {
                SupplyBooks line = new SupplyBooks();
                
                line.supply_id = rst.getLong("order_id");
                line.line_no = rst.getInt("line_no");
                line.book_id = rst.getString("book_id");
                line.quantity = rst.getInt("quantity");
                line.price = rst.getInt("price");
                
                list.add(line);
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void setSupplyBook(Long supply_id, int line_no, String book_id, int quantity, int price)
    {
        Connection con = new OracleDBMS().getConnection();
        String insertSupply = "INSERT INTO ORDER_INVOICE (supply_id, line_no, book_id, quantity, price)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insertSupply);
            ps.setLong(1, supply_id);
            ps.setInt(2, line_no);
            ps.setString(3, book_id);
            ps.setLong(4, quantity);
            ps.setLong(5, price);
            
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateSupplyBook(Long supply_id, int line_no, int quantity, int price)
    {
        Connection con = new OracleDBMS().getConnection();
        String insertSupply = "UPDATE ORDER_INVOICE set quantity = ? and price = ? where supply_id = ? and line_no = ?"
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insertSupply);
            ps.setLong(1, quantity);
            ps.setLong(2, price);
            ps.setLong(3, supply_id);
            ps.setInt(4, line_no);
            
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
