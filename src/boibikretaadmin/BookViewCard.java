/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class BookViewCard extends GridPane {
    private static BookViewCard instance;
    private int line;
    public static BookViewCard getInstance()
    {
        if(instance == null)
            instance = new BookViewCard();
        return instance;
    }
    
    private BookViewCard()
    {
        super();
        this.setVgap(10);
        this.setHgap(10);
        
        Label isbnLabel = new Label("ISBN");
        this.add(isbnLabel, 0, 0);
        
        Label titleLabel = new Label("Title");
        this.add(titleLabel, 1, 0);
        
        Label editionLabel = new Label("Edition");
        this.add(editionLabel, 2, 0);
        
        Label authorsLabel = new Label("Authors");
        this.add(authorsLabel, 3, 0);
        
        Label genresLabel = new Label("Genres");
        this.add(genresLabel, 4, 0);
        
        Label publisherLabel = new Label("Publisher");
        this.add(publisherLabel, 5, 0);
        
        Label publishDateLabel = new Label("Publishing Date");
        this.add(publishDateLabel, 6, 0);
        
        Label pageLabel = new Label("Number of Page");
        this.add(pageLabel, 7, 0);
        
        Label priceLabel = new Label("Price");
        this.add(priceLabel, 8, 0);
        
        Label availabilityLabel = new Label("Availability");
        this.add(availabilityLabel, 9, 0);
        
        Label photoLabel = new Label("Photo");
        this.add(photoLabel, 10, 0);
        
        line = 0;
        
        Books.getAllBooks().forEach(book -> {
            ArrayList<String> authors = new ArrayList<>();
            BookAuthors.getAllAuthors(book.id).forEach(author -> {
                authors.add(author.name);
            });

            ArrayList<String> genres = BookGenres.getAllGenres(book.id);
            
            if (book.photo != null) {
                addLine(book.id, book.title, book.edition, authors, genres,
                        Publishers.getPublisher(book.publisher_id).name,
                        book.publishing_date, String.valueOf(book.no_page),
                        String.valueOf(book.price), book.availability,
                        new Image(new ByteArrayInputStream(book.photo)));
            } else {
                addLine(book.id, book.title, book.edition, authors, genres,
                        Publishers.getPublisher(book.publisher_id).name,
                        book.publishing_date, String.valueOf(book.no_page),
                        String.valueOf(book.price), book.availability,
                        null);
            }
        });
    }
    
    public void addLine(String isbn, String title, String edition, ArrayList<String> authors, ArrayList<String> genres, String publisher, String publishDate, String page, String price, String availability, Image photo)
    {
        line++;
        
        Label isbnLabel = new Label(isbn);
        this.add(isbnLabel, 0, line);
        
        Label titleLabel = new Label(title);
        this.add(titleLabel, 1, line);
        
        Label editionLabel = new Label(edition);
        this.add(editionLabel, 2, line);
        
        VBox authorsLabel = new VBox();
        authors.forEach(author->{
            Label authorLabel = new Label(author);
            authorsLabel.getChildren().add(authorLabel);
        });
        this.add(authorsLabel, 3, line);
        
        VBox genresLabel = new VBox();
        genres.forEach(genre->{
            Label genreLabel = new Label(genre);
            authorsLabel.getChildren().add(genreLabel);
        });
        this.add(genresLabel, 4, line);
        
        Label publisherLabel = new Label(publisher);
        this.add(publisherLabel, 5, line);
        
        Label publishDateLabel = new Label(publishDate);
        this.add(publishDateLabel, 6, line);
        
        Label pageLabel = new Label(page);
        this.add(pageLabel, 7, line);
        
        Label priceLabel = new Label(price);
        this.add(priceLabel, 8, line);
        
        Label availabilityLabel = new Label(availability);
        this.add(availabilityLabel, 9, line);
        
        ImageView photoView = new ImageView(photo);
        photoView.setFitHeight(50);
        photoView.setFitWidth(50);
        photoView.setPreserveRatio(true);
        this.add(photoView, 10, line);
    }
}
