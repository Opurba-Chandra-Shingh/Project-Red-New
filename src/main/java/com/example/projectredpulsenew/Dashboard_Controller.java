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
    private HBox ctaRow, sidebarHeader, statsRow, topBar, topBarRight, welcomeRow;

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

        updateTopButtons();

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


    private void updateTopButtons() {
        boolean isLogged = chkLogin.isLoggedIn();

        btnLogin.setVisible(!isLogged);
        btnSignUp.setVisible(!isLogged);

        btnLogOut.setVisible(isLogged);
    }

//    public void refreshUIAfterLogin() {
//        updateTopButtons();
//    }
//private boolean checkLoginBeforeAction() {
//    if (!AuthService.isLoggedIn()) {
//        // Show alert
//        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
//        alert.setTitle("Login Required");
//        alert.setHeaderText(null);
//        alert.setContentText("Please login first to perform this action.");
//        alert.showAndWait();
//        return false;   // Not logged in
//    }
//    return true;        // Logged in
//}

//    public void alert() {
//        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
//        alert.setTitle("Login Required");
//        alert.setHeaderText(null);
//        alert.setContentText("Please login first to perform this action.");
//        alert.showAndWait();
//    }




    @FXML
    void login_d(ActionEvent event) throws Exception {
        Parent root_login = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Stage stage_dashboard = (Stage) btnLogin.getScene().getWindow();
        stage_dashboard.setScene(new Scene(root_login));
        stage_dashboard.show();
    }

    @FXML
    void signup_d(ActionEvent event) throws Exception {
        Parent root_signup = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage_dashboard = (Stage) btnSignUp.getScene().getWindow();
        stage_dashboard.setScene(new Scene(root_signup));
        stage_dashboard.show();
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
    void logout_d(ActionEvent event) throws Exception {
        chkLogin.setlogout();
        updateTopButtons();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
