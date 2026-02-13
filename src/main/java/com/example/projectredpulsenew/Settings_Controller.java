package com.example.projectredpulsenew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Settings_Controller {

    @FXML
    private Label appNameLabel;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnCreatePost;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNewsFeed;

    @FXML
    private Button btnNotifications;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnProfEdit;

    @FXML
    private Button btnProfPic;


    @FXML
    private Button btnDocEdit;

    @FXML
    private Button btnChangePass;

    @FXML
    private Button btnSettings;

    @FXML
    private VBox sidebar;

    @FXML
    private VBox sidebarButtons;

    @FXML
    private HBox sidebarHeader;

    @FXML
    private HBox topBar;

    @FXML
    private HBox topBarRight;


    @FXML
    void StngtoHome(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void StngtoNews(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
        Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Stngtochat(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Stage stage = (Stage) btnChat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void StngtoCreate(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        Stage stage = (Stage) btnCreatePost.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void StngtoNoti(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
        Stage stage = (Stage) btnNotifications.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void StngtoProf(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) btnProfile.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void gotoEdit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProf.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Edit Profile");

            popupStage.initOwner(btnProfEdit.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoProfPic(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfilePic.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Update Profile Picture");

            popupStage.initOwner(btnProfPic.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoDocEdit(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDocuments.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Update Documents");

            popupStage.initOwner(btnDocEdit.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoChangePass(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePass.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setTitle("Change Password");
            popupStage.setScene(new Scene(root));

            // Block background
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void logout_stng(ActionEvent event) throws Exception {
        chkLogin.setlogout();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
