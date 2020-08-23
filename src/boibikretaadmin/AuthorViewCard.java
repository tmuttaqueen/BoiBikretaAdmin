/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.Authors;
import java.io.ByteArrayInputStream;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author User
 */
public class AuthorViewCard extends GridPane{
    private static AuthorViewCard instance;
    private int line;
    public static AuthorViewCard getInstance()
    {
        if(instance == null)
            instance = new AuthorViewCard();
        return instance;
    }
    
    private AuthorViewCard()
    {
        super();
        this.setVgap(10);
        this.setHgap(10);
        
        Label nameLabel = new Label("Name");
        this.add(nameLabel, 0, 0);
        
        Label photoLabel = new Label("Photo");
        this.add(photoLabel, 1, 0);
        
        Label infoLabel = new Label("Information");
        this.add(infoLabel, 2, 0);
        
        line = 0;
        
        Authors.getAllAuthors().forEach(author->{
            if(author.photo != null)
                addLine(author.name, author.information, new Image(new ByteArrayInputStream(author.photo)));
            else
                addLine(author.name, author.information, null);
        });
    }
    
    public void addLine(String name, String info, Image photo)
    {
        line++;
        
        Label nameLabel = new Label(name);
        this.add(nameLabel, 0, line);
        
        ImageView photoView = new ImageView(photo);
        photoView.setFitHeight(50);
        photoView.setFitWidth(50);
        photoView.setPreserveRatio(true);
        this.add(photoView, 1, line);
        
        Label infoLabel = new Label(info);
        infoLabel.setWrapText(true);
        this.add(infoLabel, 2, line);
    }
}
