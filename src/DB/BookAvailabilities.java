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
public class BookAvailabilities {
    public Long store_id;
    public String book_id;
    public Long quantity = new Long(0);
    
    public static ArrayList<BookAvailabilities> getAllBookAvailability(Long store_id)
    {
        ArrayList<BookAvailabilities> list = null;
        Connection con = new OracleDBMS().getConnection();
        String getBookAvailability = "Select * from book_availability where store_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getBookAvailability);
            ps.setLong(1, store_id);
            ResultSet rst = ps.executeQuery();
            list = new ArrayList<>();
            while (rst.next())
            {
                BookAvailabilities bookAvailability = new BookAvailabilities();
                
                bookAvailability.store_id = rst.getLong("store_id");
                bookAvailability.book_id = rst.getString("book_id");
                bookAvailability.quantity = rst.getLong("quantity");
                
                list.add(bookAvailability);
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static BookAvailabilities getBookAvailability(Long store_id, String book_id)
    {
        BookAvailabilities bookAvailability = new BookAvailabilities();
        Connection con = new OracleDBMS().getConnection();
        String getBookAvailability = "Select * from book_availability where store_id = ? and book_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getBookAvailability);
            ps.setLong(1, store_id);
            ps.setString(2, book_id);
            ResultSet rst = ps.executeQuery();
            if (rst.next())
            {
                bookAvailability.store_id = rst.getLong("store_id");
                bookAvailability.book_id = rst.getString("book_id");
                bookAvailability.quantity = rst.getLong("quantity");
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookAvailability;
    }
}
