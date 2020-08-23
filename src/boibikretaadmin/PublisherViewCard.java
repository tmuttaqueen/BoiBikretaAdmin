/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import java.io.ByteArrayInputStream;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author User
 */
public class PublisherViewCard extends GridPane{
    private static PublisherViewCard instance;
    private int line;
    public static PublisherViewCard getInstance()
    {
        if(instance == null)
            instance = new PublisherViewCard();
        return instance;
    }
    
    private PublisherViewCard()
    {
        super();
        this.setVgap(10);
        this.setHgap(10);
        
        Label nameLabel = new Label("Name");
        this.add(nameLabel, 0, 0);
        
        Label infoLabel = new Label("Information");
        this.add(infoLabel, 1, 0);
        
        line = 0;
        
        Publishers.getAllPublishers().forEach(publisher->{
            addLine(publisher.name, publisher.info);
        });
    }
    
    public void addLine(String name, String info)
    {
        line++;
        
        Label nameLabel = new Label(name);
        this.add(nameLabel, 0, line);
        
        Label infoLabel = new Label(info);
        infoLabel.setWrapText(true);
        this.add(infoLabel, 1, line);
    }
}
