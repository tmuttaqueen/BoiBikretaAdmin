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
public class Orders {
    public Long order_id;
    public Long customer_id;
    public Long store_id;
    public Long employee_id;
    public String order_date;
    public String packing_date;
    public String delivery_date;
    public Long delivery_price;
    public Long total_price;
    public String order_method;
    public String delivery_place;
    public String order_update;
    public Long paid_amount;
    
    public static ArrayList<Orders> getAllOrders(String order_update)
    {
        ArrayList<Orders> orders = new ArrayList<>();
        Connection con = new OracleDBMS().getConnection();
        String getOrder = "Select * from order_invoice where order_update = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getOrder);
            ps.setString(1, order_update);
            ResultSet rst = ps.executeQuery();
            while (rst.next())
            {
                Orders order = new Orders();
                
                order.order_id = rst.getLong("order_id");
                order.customer_id = rst.getLong("customer_id");
                order.store_id = rst.getLong("store_id");
                order.employee_id = rst.getLong("employee_id");
                order.order_date = rst.getString("order_date");
                order.packing_date = rst.getString("packing_date");
                order.delivery_date = rst.getString("delivery_date");
                order.delivery_price = rst.getLong("delivery_price");
                order.total_price = rst.getLong("total_price");
                order.order_method = rst.getString("order_method");
                order.delivery_place = rst.getString("delivery_place");
                order.order_update = rst.getString("order_update");
                order.paid_amount = rst.getLong("paid_amount");
                
                orders.add(order);
            }
            rst.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public static void packageOrder(Long order_id)
    {
        Connection con = new OracleDBMS().getConnection();
        String getOrder = "update order_invoice set order_update = 'packaged' where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getOrder);
            ps.setLong(1, order_id);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deliverOrder(Long order_id)
    {
        Connection con = new OracleDBMS().getConnection();
        String getOrder = "update order_invoice set order_update = 'delivered' where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(getOrder);
            ps.setLong(1, order_id);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
