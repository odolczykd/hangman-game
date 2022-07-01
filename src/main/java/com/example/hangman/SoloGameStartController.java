package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class SoloGameStartController {

    private static String playerlogin;

    public SoloGameStartController(String login){
        playerlogin = login;
    }

    public SoloGameStartController() {}

    public void openWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sologamestart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Gra Solo");
        stage.setScene(scene);
        stage.show();
        System.out.println("Wybor trybu solo dla uzytkownika \"" + playerlogin + "\"");
    }

    @FXML private ImageView exitImage;
    @FXML private ImageView logoutImage;
    @FXML private ImageView normalModeImage;
    @FXML private ImageView speedrunModeImage;
    @FXML private Label modeDescriptionLabel;

    @FXML
    public void beginNormalGame() {
        System.out.println("Zaczynam gre solo w trybie NORMAL dla gracza \"" + playerlogin + "\"");

        //  TUTAJ ZACZĄĆ GRE SOLO - NORMAL

        Stage stage = (Stage) normalModeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void beginSpeedrunGame() {
        System.out.println("Zaczynam gre solo w trybie SPEEDRUN dla gracza \"" + playerlogin + "\"");

        //  TUTAJ ZACZĄĆ GRE SOLO - SPEEDRUN

        Stage stage = (Stage) speedrunModeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void showNormalDescription(){
        modeDescriptionLabel.setText("Gra normalna - zgadnij losowe hasło bez limitu czasowego.");
    }

    @FXML
    public void showSpeedrunDescription(){
        modeDescriptionLabel.setText("Wyścig z czasem - zgadnij największą liczbe haseł w ciągu dwóch minut!");
    }

    @FXML
    public void hideDescription(){
        modeDescriptionLabel.setText("");
    }

    @FXML
    public void onHomeImageClick() throws IOException {
        MainMenuController mmc = new MainMenuController(playerlogin);
        mmc.openWindow();
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
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
}
