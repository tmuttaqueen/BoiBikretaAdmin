/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author User
 */
public class GenreViewCard extends GridPane {
    private static GenreViewCard instance;
    private int line;
    public static GenreViewCard getInstance()
    {
        if(instance == null)
            instance = new GenreViewCard();
        return instance;
    }
    
    private GenreViewCard()
    {
        super();
        this.setVgap(10);
        this.setHgap(10);
        
        Label nameLabel = new Label("Name");
        this.add(nameLabel, 0, 0);
        
        Label infoLabel = new Label("Definition");
        this.add(infoLabel, 1, 0);
        
        line = 0;
        
        Genres.getAllGenres().forEach(genre->{
            addLine(genre.name, genre.definition);
        });
    }
    
    public void addLine(String name, String definition)
    {
        line++;
        
        Label nameLabel = new Label(name);
        this.add(nameLabel, 0, line);
        
        Label definitionLabel = new Label(definition);
        definitionLabel.setWrapText(true);
        this.add(definitionLabel, 1, line);
    }
}
