package com.example.projectredpulsenew;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard_Controller implements Initializable {

    @FXML
    private Button btnChat, btnCreatePost, btnHome, btnLogin, btnNewsFeed,
            btnNotifications, btnProfile, btnRequestBlood, btnSettings,
            btnSignUp, btnLogOut;

    @FXML
    private Label appNameLabel, stat1Number, stat1Number1, stat1Number11,
            stat1Title, stat1Title1, stat1Title11, welcomeSubtitle, welcomeTitle;

    @FXML
    private HBox ctaRow, sidebarHeader, statsRow, topBar, topBarRight, welcomeRow, loginBox, logoutBox, signupBox;

    @FXML
    private VBox mainContent, sidebar, sidebarButtons;

    @FXML
    private ScrollPane mainScroll;

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private AnchorPane welcomeCard;

    @FXML
    private StackPane statActiveRequests, statSuccessful, statTotalDonors;

    @FXML
    private NumberAxis x;

    @FXML
    private CategoryAxis y;

    @FXML
    private BarChart<String, Integer> requestChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ButtonsVisibility();

        XYChart.Series<String, Integer> set1 = new XYChart.Series<>();
        set1.setName("Requests");

        set1.getData().add(new XYChart.Data<>("January", 20));
        set1.getData().add(new XYChart.Data<>("February", 35));
        set1.getData().add(new XYChart.Data<>("March", 50));
        set1.getData().add(new XYChart.Data<>("April", 75));
        set1.getData().add(new XYChart.Data<>("May", 45));
        set1.getData().add(new XYChart.Data<>("June", 40));
        set1.getData().add(new XYChart.Data<>("July", 60));
        set1.getData().add(new XYChart.Data<>("August", 90));
        set1.getData().add(new XYChart.Data<>("September", 55));
        set1.getData().add(new XYChart.Data<>("October", 50));
        set1.getData().add(new XYChart.Data<>("November", 85));
        set1.getData().add(new XYChart.Data<>("December", 70));

        requestChart.getData().add(set1);
        requestChart.setLegendVisible(true);

        Platform.runLater(() -> {
            for (XYChart.Data<String, Integer> data : set1.getData()) {
                data.getNode().setStyle("-fx-bar-fill: #FD7F5D;");
            }
        });


    }


    private void ButtonsVisibility() {
        boolean isLogged = chkLogin.isLoggedIn();

        btnLogin.setVisible(!isLogged);
        btnSignUp.setVisible(!isLogged);
        btnLogOut.setVisible(isLogged);

        // SIDEBAR (IMPORTANT FIX)
        loginBox.setVisible(!isLogged);
        loginBox.setManaged(!isLogged);

        logoutBox.setVisible(isLogged);
        logoutBox.setManaged(isLogged);

        signupBox.setVisible(!isLogged);
    }






    @FXML
    void login_d(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Log in");

            popupStage.initOwner(btnLogin.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signup_d(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Sign up");

            popupStage.initOwner(btnSignUp.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DashtoNews(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
            Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }

    @FXML
    void DashtoCreate(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
            Stage stage = (Stage) btnCreatePost.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }

    @FXML
    void DashtoNoti(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
            Stage stage = (Stage) btnNotifications.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }

    @FXML
    void DashtoChat(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
            Stage stage = (Stage) btnChat.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }

    @FXML
    void DashtoProf(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) btnProfile.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }

    @FXML
    void Dashtostng(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Stage stage = (Stage) btnSettings.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }




    @FXML
    void RequestBlood(ActionEvent event)throws Exception{

        if(chkLogin.isLoggedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
            Stage stage = (Stage) btnRequestBlood.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            chkLogin.alert();
        }
    }



    @FXML
    void logout_d(ActionEvent event) throws Exception {
        chkLogin.setlogout();
        ButtonsVisibility();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
