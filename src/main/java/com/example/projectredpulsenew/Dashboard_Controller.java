package com.example.projectredpulsenew;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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
    private HBox loginBox, logoutBox, signupBox;

    @FXML private PieChart donationPieChart;

    @FXML private VBox interestedDonorsContainer, availableDonorsContainer;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ButtonsVisibility();
        //---------------------------------------------------------------------------------
        // 1. Load Chart Data
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Completed Donations", 65),
                new PieChart.Data("Pending Requests", 25),
                new PieChart.Data("Cancelled", 10)
        );
        donationPieChart.setData(pieData);

        // 2. Load Dummy Lists (Replace with real data from JSON/Database)
        loadInterestedDonors();
        loadAvailableDonors();
    }


// Helper to create list rows programmatically
private HBox createListItem(String name, String blood, String phone, String district, String actionText) {
    HBox row = new HBox();
    row.setAlignment(Pos.CENTER_LEFT);
    row.getStyleClass().add("list-item");
    row.setSpacing(10);

    Label lblName = new Label(name); lblName.setPrefWidth(200);
    Label lblBlood = new Label(blood); lblBlood.setPrefWidth(100); lblBlood.setStyle("-fx-font-weight: bold; -fx-text-fill: #D32F2F;");
    Label lblPhone = new Label(phone); lblPhone.setPrefWidth(150);
    Label lblDist = new Label(district); lblDist.setPrefWidth(150);

    Button actionBtn = new Button(actionText);
    actionBtn.setStyle("-fx-background-color: #E3F2FD; -fx-text-fill: #1976D2; -fx-background-radius: 15; -fx-font-size: 11px;");

    row.getChildren().addAll(lblName, lblBlood, lblPhone, lblDist, actionBtn);
    return row;
}


private void loadAvailableDonors() {
    for (int i = 0; i < 5; i++) {
        availableDonorsContainer.getChildren().add(
                createListItem("Karim Hasan", "A-", "01812345678", "Sylhet", "Available")
        );
    }
}

private void loadInterestedDonors() {
    // Add dummy rows
    for (int i = 0; i < 5; i++) {
        interestedDonorsContainer.getChildren().add(
                createListItem("Rahim Uddin", "O+", "01712345678", "Dhaka", "Call")
        );
    }
}




//    Sidebar Buttons
    @FXML
    void DashtoNews(ActionEvent event)throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
        Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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



//    @FXML
//    void logout_d(ActionEvent event) throws Exception {
//        chkLogin.setlogout();
//        ButtonsVisibility();
//
//        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
//        Stage stage = (Stage) btnLogOut.getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
//}


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

            // 🔥 Controller instance নাও
            LogIn_Controller controller = loader.getController();

            // 🔥 এখানে বলছি login success হলে কী হবে
            controller.setOnLoginSuccess(() -> ButtonsVisibility());


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

            // 🔥 Controller instance নাও
            SignUp_Controller controller = loader.getController();

            // 🔥 এখানে বলছি login success হলে কী হবে
            controller.setOnSignUpSuccess(() -> ButtonsVisibility());

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
    void logout_d(ActionEvent event) throws Exception {
        chkLogin.setlogout();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
