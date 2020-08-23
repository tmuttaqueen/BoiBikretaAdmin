/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AuthorInsertCard extends VBox{
    private static AuthorInsertCard instance;
    
    File selectedPhoto;
    
    public static AuthorInsertCard getInstance()
    {
        if(instance == null)
            instance = new AuthorInsertCard();
        return instance;
    }
    private AuthorInsertCard() {
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
        
        Label photoLabel = new Label("Photo");
        VBox photoField = new VBox();
        
        ImageView photoView = new ImageView();
        photoView.setFitHeight(100);
        photoView.setFitWidth(100);
        photoView.setPreserveRatio(true);
        photoField.getChildren().add(photoView);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Button browse = new Button("Browse");
        browse.setOnAction(e->{
            selectedPhoto = fileChooser.showOpenDialog(new Stage());
            if(selectedPhoto != null)
            {
                photoView.setImage(new Image(selectedPhoto.toURI().toString()));
            }
        });
        photoField.getChildren().add(browse);
        
        card.add(photoLabel, 0, 1);
        card.add(photoField, 1, 1);
        
        Label infoLabel = new Label("Information");
        TextArea infoField = new TextArea();
        card.add(infoLabel, 0, 2);
        card.add(infoField, 1, 2);
        
        this.getChildren().add(card);
        
        Button insert = new Button("Insert");
        insert.setOnAction(e->{
            if(!nameField.getText().equals(""))
            {
                Authors.insert(nameField.getText(), infoField.getText(), selectedPhoto);
                Image photo = null;
                if(selectedPhoto != null)
                    photo = new Image(selectedPhoto.toURI().toString());
                AuthorViewCard.getInstance().addLine(nameField.getText(), infoField.getText(), photo);
                nameField.setText("");
                infoField.setText("");
                selectedPhoto = null;
                photoView.setImage(null);
            }
        });
        
        this.getChildren().add(insert);
    }
    
}
