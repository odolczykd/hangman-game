package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class DuetGameTypeController {

    private static String playerlogin;

    public DuetGameTypeController(String login){
        playerlogin = login;
    }

    public DuetGameTypeController() {}

    public void openWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("duetgametype-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Gra Duet");
        stage.setScene(scene);
        stage.show();
        System.out.println("Wybór trybu duet dla użytkownika \"" + playerlogin + "\"");
    }

    @FXML private ImageView exitImage;
    @FXML private ImageView logoutImage;
    @FXML private ImageView hostGameImage;
    @FXML private ImageView joinGameImage;
    @FXML private HBox hostnameBar;
    @FXML private TextField hostnameField;
    @FXML private Label hostnameErrorLabel;

    @FXML
    public void hostDuet() throws IOException {
        System.out.println("Hostuję grę duet. host: \"" + playerlogin + "\"");

        //  TUTAJ ZACZĄĆ GRE - NORMAL
        DuetGameHostController dghc = new DuetGameHostController(playerlogin);
        dghc.openWindow();

        Stage stage = (Stage) hostGameImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void joinDuet() {
        if(!hostnameBar.isVisible()){
            hostnameBar.setVisible(true);
        }
        else {
            hostnameField.setText("");
        }

    }

    @FXML
    public void joinDuetConnection() {
        hostnameErrorLabel.setText("");

        if(hostnameField.getText().length() == 0){
            hostnameErrorLabel.setVisible(true);
            hostnameErrorLabel.setText("Wprowadź nazwę hosta!");
        }
        else{
            //else próba połączenia else błąd

            // otwórz okienko z grą
            System.out.println("Zaczynam grę duet. Dołączający: \"" + playerlogin + "\". Hostname: \"" + hostnameField.getText() + "\"");

        Stage stage = (Stage) joinGameImage.getScene().getWindow();
        stage.close();
        }

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
