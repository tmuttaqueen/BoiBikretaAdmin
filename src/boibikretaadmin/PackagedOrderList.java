/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.Orders;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class PackagedOrderList {
    private static PackagedOrderList instance;
    private static ScrollPane view;
    private static VBox vbox;
    
    public static PackagedOrderList getInstance()
    {
        if(instance == null)
        {
            instance = new PackagedOrderList();
            instance.set();
        }
        return instance;
    }
    private void set()
    {
        vbox = new VBox();
        
        Orders.getAllOrders("packaged").forEach(order->{
            VBox orderList = new VBox();
            vbox.getChildren().add(orderList);
            
            orderList.getChildren().add(new Label("Order Id: "+order.order_id));
            orderList.getChildren().add(new Label("Customer Id: "+order.customer_id));
            orderList.getChildren().add(new Label("Store Id: "+order.store_id));
            orderList.getChildren().add(new OrderCard(order));
            
            Button delivered = new Button("Delivered");
            if(order.paid_amount.equals(order.total_price))delivered.setDisable(true);
            orderList.getChildren().add(delivered);
            
            delivered.setOnAction(e->{
                Orders.deliverOrder(order.order_id);
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
