/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author User
 */
public class View {
    static BorderPane root;
    
    public static void setView(BorderPane pane)
    {
        root = pane;
        HBox topBar = new HBox();
        
        Button dataBase = new Button("DataBase");
        dataBase.setOnAction(e->{
            root.setCenter(DatabaseView.getInstance());
        });
        topBar.getChildren().add(dataBase);
        
        Button orders = new Button("Orders");        
        orders.setOnAction(e->{
            root.setCenter(PendingOrderList.getInstance().getView());
        });
        topBar.getChildren().add(orders);
        
        root.setTop(new ToolBar(topBar));
    }
}
