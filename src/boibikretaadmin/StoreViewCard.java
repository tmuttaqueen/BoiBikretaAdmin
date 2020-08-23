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
public class StoreViewCard extends GridPane {
    private static StoreViewCard instance;
    private int line;
    public static StoreViewCard getInstance()
    {
        if(instance == null)
            instance = new StoreViewCard();
        return instance;
    }
    
    private StoreViewCard()
    {
        super();
        this.setVgap(10);
        this.setHgap(10);
        
        Label nameLabel = new Label("Name");
        this.add(nameLabel, 0, 0);
        
        Label addressLabel = new Label("Address");
        this.add(addressLabel, 1, 0);
        
        line = 0;
        
        Stores.getAllStores().forEach(store->{
            addLine(store.name, store.address);
        });
    }
    
    public void addLine(String name, String address)
    {
        line++;
        
        Label nameLabel = new Label(name);
        this.add(nameLabel, 0, line);
        
        Label addressLabel = new Label(address);
        addressLabel.setWrapText(true);
        this.add(addressLabel, 1, line);
    }
}
