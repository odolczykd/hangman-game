package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SoloNormalGameController implements Initializable {

    private static String playerlogin;
    private String[] phrase;

    @FXML private ImageView exitImage, logoutImage;
    @FXML private Label phraseLabel, categoryLabel;
    @FXML private TextField letterField;

    public SoloNormalGameController() {}

    public SoloNormalGameController(String login){
        playerlogin = login;
    }

    public void openWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanApplication.class.getResource("solonormalgame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 500);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Normalna gra solo");
        stage.setScene(scene);
        stage.show();
    }

    public String hidePhrase(String phrase){
        String hidden = "";
        for(int i=0; i<phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))) hidden += '-';
            else hidden += phrase.charAt(i);
        }

        return hidden;
    }

    @FXML
    public void onExitImageClick(){
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLogoutImageClick() throws IOException {
        LoginController lc = new LoginController();
        lc.reopenWindow();
        Stage stage = (Stage) logoutImage.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // aby w fieldzie byly same wielkie litery
        letterField.textProperty().addListener((ov, oldValue, newValue) -> {
            letterField.setText(newValue.toUpperCase());
        });

        DbConnection dbc = null;
        try {
            dbc = new DbConnection();
            phrase = dbc.getPhrase();
            phraseLabel.setText(hidePhrase(phrase[0]));
            categoryLabel.setText("Kategoria: " + phrase[1]);
            dbc.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
