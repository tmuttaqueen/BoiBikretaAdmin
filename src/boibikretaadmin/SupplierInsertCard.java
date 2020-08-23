/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class SupplierInsertCard extends VBox {
    private static SupplierInsertCard instance;
    
    public static SupplierInsertCard getInstance()
    {
        if(instance == null)
            instance = new SupplierInsertCard();
        return instance;
    }

    private SupplierInsertCard() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        
        GridPane card = new GridPane();
        card.setVgap(10);
        card.setHgap(10);
        
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();
        card.add(nameLabel, 0, 0);
        card.add(nameField, 1, 0);
        
        Label addressLabel = new Label("Address");
        TextArea addressField = new TextArea();
        card.add(addressLabel, 0, 1);
        card.add(addressField, 1, 1);
        
        this.getChildren().add(card);
        
        Button insert = new Button("Insert");
        insert.setOnAction(e->{
            if(!nameField.getText().equals(""))
            {
                Suppliers.insert(nameField.getText(), addressField.getText());
                SupplierViewCard.getInstance().addLine(nameField.getText(), addressField.getText());
                nameField.setText("");
                addressField.setText("");
            }
        });
        
        this.getChildren().add(insert);
    }
}
