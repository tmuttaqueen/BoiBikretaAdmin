/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class BookAvailabilityViewCard extends BorderPane {
    private static BookAvailabilityViewCard instance;
    
    GridPane card;
    private int line;
    public static BookAvailabilityViewCard getInstance()
    {
        if(instance == null)
            instance = new BookAvailabilityViewCard();
        return instance;
    }
    
    private BookAvailabilityViewCard()
    {
        super();
        
        ChoiceBox<Stores> stores = new ChoiceBox<Stores>(FXCollections.observableArrayList(Stores.getAllStores()));
        this.setTop(stores);
        
        ScrollPane center = new ScrollPane();
        this.setCenter(center);
        
        stores.valueProperty().addListener((observable, oldValue, newValue) -> {            
            card = new GridPane();
            card.setVgap(10);
            card.setHgap(10);
            
            center.setContent(card);
            
            line = 0;
            
            Label isbnLabel = new Label("ISBN");
            card.add(isbnLabel, 0, line);

            Label nameLabel = new Label("Title");
            card.add(nameLabel, 1, line);
            
            Label addressLabel = new Label("Quantity");
            card.add(addressLabel, 2, line);
            
            BookAvailabilities.getAllBookAvailability(newValue.id).forEach(bookAvailability->{
                addLine(bookAvailability.book_id, bookAvailability.quantity.toString());
            });
        });
                
    }
    
    public void addLine(String isbn, String quantity)
    {
        line++;
        
        Label isbnLabel = new Label(isbn);
        card.add(isbnLabel, 0, line);
        
        Label nameLabel = new Label(Books.getBook(isbn).title);
        card.add(nameLabel, 1, line);
        
        Label addressLabel = new Label(quantity);
        addressLabel.setWrapText(true);
        card.add(addressLabel, 2, line);
    }
}
