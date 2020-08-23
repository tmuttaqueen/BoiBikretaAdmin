/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.Publishers;
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
public class PublisherInsertCard extends VBox{
    private static PublisherInsertCard instance;
    
    public static PublisherInsertCard getInstance()
    {
        if(instance == null)
            instance = new PublisherInsertCard();
        return instance;
    }

    private PublisherInsertCard() {
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
        
        Label infoLabel = new Label("Information");
        TextArea infoField = new TextArea();
        card.add(infoLabel, 0, 1);
        card.add(infoField, 1, 1);
        
        this.getChildren().add(card);
        
        Button insert = new Button("Insert");
        insert.setOnAction(e->{
            if(!nameField.getText().equals(""))
            {
                Publishers.insert(nameField.getText(), infoField.getText());
                PublisherViewCard.getInstance().addLine(nameField.getText(), infoField.getText());
                nameField.setText("");
                infoField.setText("");
            }
        });
        
        this.getChildren().add(insert);
    }
}
