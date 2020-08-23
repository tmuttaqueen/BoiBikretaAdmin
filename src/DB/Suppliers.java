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
public class Suppliers {
    public long id;
    public String name;
    public String address;
    
    public static long getPK(String str)
    {
        long supplier_id = 0;
        Connection con = new OracleDBMS().getConnection();
        String getSupplier = "Select * from suppliers where name||', '||address = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getSupplier);
            ps.setString(1, str);
            ResultSet rst = ps.executeQuery();
            while (rst.next())
            {
                supplier_id = rst.getLong("supplier_id");
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplier_id;
    }
    
    public static ArrayList<Stores> getAllSuppliers()
    {
        ArrayList<Stores> list = null;
        Connection con = new OracleDBMS().getConnection();
        String getSupplier = "Select * from supplier";
        try {
            PreparedStatement ps = con.prepareStatement(getSupplier);
            ResultSet rst = ps.executeQuery();
            list = new ArrayList<>();
            while (rst.next())
            {
                Stores store = new Stores();
                
                store.id = rst.getLong("supplier_id");
                store.name = rst.getString("name");
                store.address = rst.getString("address");
                
                list.add(store);
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void insert(String name, String address)
    {
        Connection con = new OracleDBMS().getConnection();
        String setSupplier = "Insert into supplier(NAME, ADDRESS) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(setSupplier);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
