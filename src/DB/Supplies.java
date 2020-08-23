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
public class Supplies {
    public Long supply_id;
    public Long supplier_id;
    public Long store_id;
    public Long total_price;    
    public String order_date;    
    public String receive_date;    
    public String supply_update;
    public Long paid_amount;
    
    public static ArrayList<Supplies> getAllSupplies(String supply_update)
    {
        ArrayList<Supplies> supplies = new ArrayList<>();
        Connection con = new OracleDBMS().getConnection();
        String getSupply = "Select * from supply_invoice where supply_update = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getSupply);
            ps.setString(1, supply_update);
            ResultSet rst = ps.executeQuery();
            while (rst.next())
            {
                Supplies supply = new Supplies();
                
                supply.supply_id = rst.getLong("supply_id");
                supply.supplier_id = rst.getLong("supplier_id");
                supply.store_id = rst.getLong("store_id");                
                supply.total_price = rst.getLong("total_price");
                supply.order_date = rst.getString("order_date");
                supply.receive_date = rst.getString("receive_date");
                supply.supply_update = rst.getString("supply_update");
                supply.paid_amount = rst.getLong("paid_amount");
                
                supplies.add(supply);
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplies;
    }
    
    public static Supplies setSupply(Long supplier_id, Long store_id)
    {
        Supplies supply = new Supplies();
        Connection con = new OracleDBMS().getConnection();
        String getSupply_id = "Select Supply_seq.nextval from dual";
        supply.supply_id = Long.valueOf(0);
        try {
            PreparedStatement ps = con.prepareStatement(getSupply_id);
            ResultSet rst = ps.executeQuery();
            if (rst.next())
            {
                supply.supply_id = rst.getLong(1);
            }
            rst.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        supply.supplier_id = supplier_id;
        supply.store_id = store_id;
        supply.total_price = Long.valueOf(0);
        supply.order_date = null;
        supply.receive_date = null;
        supply.supply_update = "pending";
        supply.paid_amount = Long.valueOf(0);
        String insertSupply = "INSERT INTO SUPPLY_INVOICE (supply_id, supplier_id, store_id, total_price, supply_update, paid_amount)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insertSupply);
            ps.setLong(1, supply.supply_id);
            ps.setLong(2, supply.supplier_id);
            ps.setLong(3, supply.store_id);
            ps.setLong(4, supply.total_price);
            ps.setString(5, supply.supply_update);
            ps.setLong(6, supply.paid_amount);
            
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supply;
    }
    
    public static void orderSupply(Long supply_id)
    {
        Connection con = new OracleDBMS().getConnection();
        String getSupply = "update supply_invoice set supply_update = 'ordered' where supply_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getSupply);
            ps.setLong(1, supply_id);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void receiveSupply(Long supply_id)
    {
        Connection con = new OracleDBMS().getConnection();
        String getSupply = "update supply_invoice set supply_update = 'received' where supply_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getSupply);
            ps.setLong(1, supply_id);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
