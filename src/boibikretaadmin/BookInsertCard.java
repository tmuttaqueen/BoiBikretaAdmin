/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boibikretaadmin;

import DB.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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
public class BookInsertCard extends VBox {
    private static BookInsertCard instance;
    
    ArrayList<String> authorNames;
    ArrayList<String> genreNames;
    ArrayList<String> publiserNames;
    
    ArrayList<ChoiceBox<String>> authorFields;
    ArrayList<ChoiceBox<String>> genreFields;
    ChoiceBox<String> publisherField;
    
    String author_abbr;
    
    File selectedPhoto;
    
    public static BookInsertCard getInstance()
    {
        if(instance == null)
            instance = new BookInsertCard();
        else
            instance.refresh();
        return instance;
    }
    private BookInsertCard() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        
        GridPane card = new GridPane();
        card.setVgap(10);
        card.setHgap(10);
        
        Label isbnLabel = new Label("ISBN");
        TextField isbnField = new TextField();
        card.add(isbnLabel, 0, 0);
        card.add(isbnField, 1, 0);
        
        Label titleLabel = new Label("Title");
        TextField titleField = new TextField();
        card.add(titleLabel, 0, 1);
        card.add(titleField, 1, 1);
        
        Label editionLabel = new Label("Edition");
        TextField editionField = new TextField();
        card.add(editionLabel, 0, 2);
        card.add(editionField, 1, 2);
        
        Label authorLabel = new Label("Authors");
        
        VBox authorField = new VBox();
        
        authorNames = new ArrayList<>();
        Authors.getAllAuthors().forEach(author->{
            authorNames.add(author.name);
        });
        authorFields = new ArrayList<>();
        ChoiceBox<String> authorTakerField = new ChoiceBox<>(FXCollections.observableArrayList(authorNames));
        authorFields.add(authorTakerField);
        authorField.getChildren().add(authorTakerField);
        
        Button addAuthor = new Button("+");
        addAuthor.setOnAction(e->{
            authorField.getChildren().remove(addAuthor);
            ChoiceBox<String> newauthorTakerField = new ChoiceBox<>(FXCollections.observableArrayList(authorNames));
            authorField.getChildren().add(newauthorTakerField);
            authorField.getChildren().add(addAuthor);
        });
        authorField.getChildren().add(addAuthor);
        
        card.add(authorLabel, 0, 3);
        card.add(authorField, 1, 3);
        
        Label genreLabel = new Label("Genres");
        
        VBox genreField = new VBox();
        
        genreNames = new ArrayList<>();
        Genres.getAllGenres().forEach(genre->{
            genreNames.add(genre.name);
        });
        genreFields = new ArrayList<>();
        ChoiceBox<String> genreTakerField = new ChoiceBox<>(FXCollections.observableArrayList(genreNames));
        genreFields.add(genreTakerField);
        genreField.getChildren().add(genreTakerField);
        
        Button addGenre = new Button("+");
        addGenre.setOnAction(e->{
            genreField.getChildren().remove(addGenre);
            ChoiceBox<String> newgenreTakerField = new ChoiceBox<>(FXCollections.observableArrayList(genreNames));
            genreField.getChildren().add(newgenreTakerField);
            genreField.getChildren().add(addGenre);
        });
        genreField.getChildren().add(addGenre);
        
        card.add(genreLabel, 0, 4);
        card.add(genreField, 1, 4);
        
        Label publisherLabel = new Label("Publisher");
        
        publiserNames = new ArrayList<>();
        Publishers.getAllPublishers().forEach(publisher->{
            publiserNames.add(publisher.name);
        });
        publisherField = new ChoiceBox<>(FXCollections.observableArrayList(publiserNames));
        
        card.add(publisherLabel, 0, 5);
        card.add(publisherField, 1, 5);
        
        Label publishDateLabel = new Label("Publishing Date");
        DatePicker publishDateField = new DatePicker();
        card.add(publishDateLabel, 0, 6);
        card.add(publishDateField, 1, 6);
        
        Label pageLabel = new Label("Number of Page");
        Spinner<Integer> pageField = new Spinner<>(1, 1000000, 100);
        pageField.setEditable(true);
        card.add(pageLabel, 0, 7);
        card.add(pageField, 1, 7);
        
        Label priceLabel = new Label("Price");
        Spinner<Integer> priceField = new Spinner<>(1, 1000000, 100);
        priceField.setEditable(true);
        card.add(priceLabel, 0, 8);
        card.add(priceField, 1, 8);
        
        Label availabilityLabel = new Label("Avilability");
        ChoiceBox<String> availabilityField = new ChoiceBox<>(FXCollections.observableArrayList("not available", "in stock", "out of stock", "preorder available"));
        card.add(availabilityLabel, 0, 9);
        card.add(availabilityField, 1, 9);
        
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
        
        card.add(photoLabel, 0, 10);
        card.add(photoField, 1, 10);
        
        this.getChildren().add(card);
        
        Button insert = new Button("Insert");
        insert.setOnAction(e->{
            if(isbnField.getText().equals(""))
                isbnField.requestFocus();
            else if(titleField.getText().equals(""))
                titleField.requestFocus();
            else if(publishDateField.getValue() == null)
                publishDateField.requestFocus();
            else {
                author_abbr = "";
                authorFields.forEach(field->{
                    author_abbr = author_abbr.concat(field.getValue()+", ");
                });
                Books.insert(isbnField.getText(), titleField.getText(), editionField.getText(), 
                        author_abbr, publisherField.getValue(), publishDateField.getValue().toString(), 
                        pageField.getValue().toString(), priceField.getValue().toString(), 
                        availabilityField.getValue(), selectedPhoto);
                
                ArrayList<String> authors = new ArrayList<>();
                authorFields.forEach(field->{
                    authors.add(field.getValue());
                    BookAuthors.insert(isbnField.getText(), field.getValue(), "writer");
                });
                
                ArrayList<String> genres = new ArrayList<>();
                genreFields.forEach(field->{
                    genres.add(field.getValue());
                    BookGenres.insert(isbnField.getText(), field.getValue());
                });
                
                Image photo = null;
                if(selectedPhoto != null)
                    photo = new Image(selectedPhoto.toURI().toString());
                
                BookViewCard.getInstance().addLine(isbnField.getText(), titleField.getText(), editionField.getText(),
                        authors, genres, publisherField.getValue(), publishDateField.getValue().toString(), 
                        pageField.getValue().toString(), priceField.getValue().toString(), 
                        availabilityField.getValue(), photo);
                
                isbnField.setText("");
                titleField.setText("");
                editionField.setText("");
                
                for(int i = 1; i < authorFields.size(); i++)
                {
                    authorField.getChildren().remove(authorFields.get(i));
                }
                authorFields = new ArrayList<>();
                authorFields.add(authorTakerField);
                
                for(int i = 1; i < genreFields.size(); i++)
                {
                    authorField.getChildren().remove(genreFields.get(i));
                }
                genreFields = new ArrayList<>();
                genreFields.add(authorTakerField);
                
                publishDateField.setValue(LocalDate.now());
                pageField.getEditor().setText("100");
                priceField.getEditor().setText("100");
                
                selectedPhoto = null;
                photoView.setImage(null);
            }
        });
        
        this.getChildren().add(insert);
    }
    public void refresh()
    {
        authorNames = new ArrayList<>();
        Authors.getAllAuthors().forEach(author->{
            authorNames.add(author.name);
        });
        authorFields.forEach(authorField->{
            authorField.setItems(FXCollections.observableArrayList(authorNames));
        });
        
        genreNames = new ArrayList<>();
        Genres.getAllGenres().forEach(genre->{
            genreNames.add(genre.name);
        });
        genreFields.forEach(genreField->{
            genreField.setItems(FXCollections.observableArrayList(genreNames));
        });
        
        publiserNames = new ArrayList<>();
        Publishers.getAllPublishers().forEach(publisher->{
            publiserNames.add(publisher.name);
        });
        publisherField.setItems(FXCollections.observableArrayList(publiserNames));
    }
    
}
