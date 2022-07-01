package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RankController implements Initializable {

    private static String playerlogin;

    @FXML private ImageView exitImage;
    @FXML private ImageView logoutImage;
    @FXML private ImageView homeImage;

    @FXML private Label firstNickname;
    @FXML private Label secondNickname;
    @FXML private Label thirdNickname;
    @FXML private Label fourthNickname;
    @FXML private Label fourthPoints;
    @FXML private Label fifthNickname;
    @FXML private Label fifthPoints;

    public RankController(String login){
        playerlogin = login;
    }

    public RankController() {}

    public void openWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rank-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Ranking");
        stage.setScene(scene);
        stage.show();
        System.out.println("Ranking normalny dla uzytkownika \"" + playerlogin + "\"");
    }


    @FXML
    public void onHomeImageClick() throws IOException {
        MainMenuController mmc = new MainMenuController(playerlogin);
        mmc.openWindow();
        Stage stage = (Stage) homeImage.getScene().getWindow();
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


    public void openNormalRanking() throws SQLException {
        DbConnection dbc = new DbConnection();
        String[] scores;
        scores = dbc.getTopNormalScore();
        firstNickname.setText(scores[0]);
        secondNickname.setText(scores[1]);
        thirdNickname.setText(scores[2]);
        fourthNickname.setText(scores[3]);
        fourthPoints.setText(scores[4]);
        fifthNickname.setText(scores[5]);
        fifthPoints.setText(scores[6]);

        dbc.closeConnection();
    }

    public void openSpeedrunRanking() throws SQLException {
        DbConnection dbc = new DbConnection();
        String[] scores;
        scores = dbc.getTopSpeedrunScore();
        firstNickname.setText(scores[0]);
        secondNickname.setText(scores[1]);
        thirdNickname.setText(scores[2]);
        fourthNickname.setText(scores[3]);
        fourthPoints.setText(scores[4]);
        fifthNickname.setText(scores[5]);
        fifthPoints.setText(scores[6]);

        dbc.closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            openNormalRanking();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Automatyczne wypelnianie rankingu pobranego z bazy danych, uzytkownik:  \"" + playerlogin + "\"");
    }
}
