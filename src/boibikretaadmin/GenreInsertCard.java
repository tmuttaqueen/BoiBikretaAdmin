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
public class GenreInsertCard extends VBox {
    private static GenreInsertCard instance;
    
    public static GenreInsertCard getInstance()
    {
        if(instance == null)
            instance = new GenreInsertCard();
        return instance;
    }

    private GenreInsertCard() {
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
        
        Label definitionLabel = new Label("Definition");
        TextArea definitionField = new TextArea();
        card.add(definitionLabel, 0, 1);
        card.add(definitionField, 1, 1);
        
        this.getChildren().add(card);
        
        Button insert = new Button("Insert");
        insert.setOnAction(e->{
            if(!nameField.getText().equals(""))
            {
                Genres.insert(nameField.getText(), definitionField.getText());
                GenreViewCard.getInstance().addLine(nameField.getText(), definitionField.getText());
                nameField.setText("");
                definitionField.setText("");
            }
        });
        
        this.getChildren().add(insert);
    }
}
