/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.Orders;
import DB.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class PendingOrderList {
    private static PendingOrderList instance;
    private static ScrollPane view;
    private static VBox vbox;
    
    public static PendingOrderList getInstance()
    {
        if(instance == null)
        {
            instance = new PendingOrderList();
            instance.set();
        }
        return instance;
    }
    private void set()
    {
        vbox = new VBox();
        
        Orders.getAllOrders("pending").forEach(order->{
            VBox orderList = new VBox();
            vbox.getChildren().add(orderList);
            
            orderList.getChildren().add(new Label("Order Id: "+order.order_id));
            orderList.getChildren().add(new Label("Customer Id: "+order.customer_id));
            orderList.getChildren().add(new Label("Store Id: "+order.store_id));
            orderList.getChildren().add(new OrderCard(order));
            
            Button packaged = new Button("Packaged");
            orderList.getChildren().add(packaged);
            
            packaged.setOnAction(e->{
                Orders.packageOrder(order.order_id);
                vbox.getChildren().remove(orderList);
            });
        });
        
        view = new ScrollPane();
        view.setContent(vbox);
    }
    public ScrollPane getView()
    {
        view.setContent(vbox);
        return view;
    }
}
