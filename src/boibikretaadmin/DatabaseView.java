/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class DatabaseView extends BorderPane{
    private static DatabaseView instance;
    
    public static DatabaseView getInstance()
    {
        if(instance == null)
            instance = new DatabaseView();
        return instance;
    }
    
    public DatabaseView()
    {
        VBox leftBar = new VBox();
        this.setLeft(new ScrollPane(leftBar));
        
        ScrollPane center = new ScrollPane();
        this.setCenter(center);
        
        ToggleGroup tgp = new ToggleGroup();
        
        ToggleButton publisher = new ToggleButton("Publisher");
        publisher.setToggleGroup(tgp);
        publisher.setOnAction(e->{
            if(!publisher.isSelected())
                publisher.setSelected(true);
            else {
                VBox publisherBox = new VBox();
                publisherBox.setPadding(new Insets(10));
                publisherBox.setSpacing(10);
                
                publisherBox.getChildren().add(PublisherInsertCard.getInstance());
                publisherBox.getChildren().add(new ScrollPane(PublisherViewCard.getInstance()));
                
                center.setContent(publisherBox);
            }
        });
        leftBar.getChildren().add(publisher);
        
        ToggleButton author = new ToggleButton("Author");
        author.setToggleGroup(tgp);
        author.setOnAction(e->{
            if(!author.isSelected())
                author.setSelected(true);
            else {
                VBox authorBox = new VBox();
                authorBox.setPadding(new Insets(10));
                authorBox.setSpacing(10);
                
                authorBox.getChildren().add(AuthorInsertCard.getInstance());
                authorBox.getChildren().add(new ScrollPane(AuthorViewCard.getInstance()));
                
                center.setContent(authorBox);
            }
        });
        leftBar.getChildren().add(author);
        
        ToggleButton genre = new ToggleButton("Genre");
        genre.setToggleGroup(tgp);
        genre.setOnAction(e->{
            if(!genre.isSelected())
                genre.setSelected(true);
            else {
                VBox genreBox = new VBox();
                genreBox.setPadding(new Insets(10));
                genreBox.setSpacing(10);
                
                genreBox.getChildren().add(GenreInsertCard.getInstance());
                genreBox.getChildren().add(new ScrollPane(GenreViewCard.getInstance()));
                
                center.setContent(genreBox);
            }
        });
        leftBar.getChildren().add(genre);
        
        ToggleButton book = new ToggleButton("Book");
        book.setToggleGroup(tgp);
        book.setOnAction(e->{
            if(!book.isSelected())
                book.setSelected(true);
            else {
                VBox bookBox = new VBox();
                bookBox.setPadding(new Insets(10));
                bookBox.setSpacing(10);
                
                bookBox.getChildren().add(BookInsertCard.getInstance());
                bookBox.getChildren().add(new ScrollPane(BookViewCard.getInstance()));
                
                center.setContent(bookBox);
            }
        });
        leftBar.getChildren().add(book);
        
        ToggleButton store = new ToggleButton("Store");
        store.setToggleGroup(tgp);
        store.setOnAction(e->{
            if(!store.isSelected())
                store.setSelected(true);
            else {
                VBox storeBox = new VBox();
                storeBox.setPadding(new Insets(10));
                storeBox.setSpacing(10);
                
                storeBox.getChildren().add(StoreInsertCard.getInstance());
                storeBox.getChildren().add(new ScrollPane(StoreViewCard.getInstance()));
                
                center.setContent(storeBox);
            }
        });
        leftBar.getChildren().add(store);
        
        ToggleButton bookAvailability = new ToggleButton("Book Availability");
        bookAvailability.setToggleGroup(tgp);
        bookAvailability.setOnAction(e->{
            if(!bookAvailability.isSelected())
                bookAvailability.setSelected(true);
            else {
                VBox supplierBox = new VBox();
                supplierBox.setPadding(new Insets(10));
                supplierBox.setSpacing(10);
                
                supplierBox.getChildren().add(BookAvailabilityViewCard.getInstance());
                
                center.setContent(supplierBox);
            }
        });
        leftBar.getChildren().add(bookAvailability);
        
        ToggleButton supplier = new ToggleButton("Supplier");
        supplier.setToggleGroup(tgp);
        supplier.setOnAction(e->{
            if(!supplier.isSelected())
                supplier.setSelected(true);
            else {
                VBox supplierBox = new VBox();
                supplierBox.setPadding(new Insets(10));
                supplierBox.setSpacing(10);
                
                supplierBox.getChildren().add(SupplierInsertCard.getInstance());
                supplierBox.getChildren().add(new ScrollPane(SupplierViewCard.getInstance()));
                
                center.setContent(supplierBox);
            }
        });
        leftBar.getChildren().add(supplier);
    }
    
}
