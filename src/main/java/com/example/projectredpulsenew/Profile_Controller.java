package com.example.projectredpulsenew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;

/*
==========================================================
                PROFILE CONTROLLER
==========================================================
This controller:
1. Loads current logged-in user's profile dynamically
2. Shows profile picture in circular format
3. Displays personal & address information
4. Opens medical PDF file
5. Shows NID front & back images
6. Keeps sidebar navigation unchanged
==========================================================
*/

public class Profile_Controller {

    // ======================================================
    //                SIDEBAR NAVIGATION BUTTONS
    // (UNCHANGED - same as your previous design)
    // ======================================================

    @FXML private Button btnHome;
    @FXML private Button btnNewsFeed;
    @FXML private Button btnNotifications;
    @FXML private Button btnChat;
    @FXML private Button btnCreatePost;
    @FXML private Button btnSettings;
    @FXML private Button btnLogOut;

    // ======================================================
    //                PROFILE HEADER SECTION
    // ======================================================

    @FXML private Circle profileCircle;   // Round profile picture
    @FXML private Label lblUserName;// User name in header
    @FXML private Label lblHeaderDistrict;

    // ======================================================
    //                PERSONAL INFORMATION SECTION
    // ======================================================

    @FXML private Label lblFullName;
    @FXML private Label lblAge;
    @FXML private Label lblGender;
    @FXML private Label lblBloodGroup;
    @FXML private Label lblPhone;
    @FXML private Label lblEmail;

    // ======================================================
    //                ADDRESS SECTION
    // ======================================================

    @FXML private Label lblDistrict;   // Upazilla can be added later

    // ======================================================
    //                DOCUMENT VERIFICATION SECTION
    // ======================================================

    @FXML private Button btnMedicalPdf;  // Opens medical certificate PDF
    @FXML private ImageView imgNidFront; // NID front image
    @FXML private ImageView imgNidBack;// NID back image

    @FXML private Button btnEditProfile, btnUpdatePhoto, btnUpdateDocuments;

    // Default profile image if user has no image
    private final String DEFAULT_IMAGE_PATH =
            "D:\\project-redpulse-new\\files\\default.jpg";


    // ======================================================
    //                INITIALIZE METHOD
    // Called automatically when FXML loads
    // ======================================================

    @FXML
    public void initialize() {
        loadUserData();   // Load current logged-in user data
    }


    // ======================================================
    //                LOAD CURRENT USER DATA
    // Fetch data from LoginDetails and populate UI
    // ======================================================

    private void loadUserData() {

        User user = LoginDetails.getUser();

        if (user == null) {
            System.out.println("No logged-in user found!");
            return;
        }

        // Load profile picture
        setProfileImage(user.getProfilePicPath());

        // Header section
        lblUserName.setText(safe(user.getName()));
        lblHeaderDistrict.setText(safe(user.getDistrict()));

        // Personal Information
        lblFullName.setText(safe(user.getName()));
        lblAge.setText(safe(user.getAge()));
        lblGender.setText(safe(user.getGender()));
        lblBloodGroup.setText(safe(user.getBloodGroup()));
        lblPhone.setText(safe(user.getNumber()));
        lblEmail.setText(safe(user.getEmail()));

        // Address
        lblDistrict.setText(safe(user.getDistrict()));

        // Load document images
        loadImage(imgNidFront, user.getNidFrontPath());
        loadImage(imgNidBack, user.getNidBackPath());

        // Set PDF open action
        btnMedicalPdf.setOnAction(e -> openPdf(user.getMedicalPdfPath()));
    }


    // ======================================================
    //                SET PROFILE IMAGE (CIRCLE)
    // Uses ImagePattern to fill Circle
    // If no image -> default image used
    // ======================================================

    private void setProfileImage(String path) {
        try {
            File file = (path != null && !path.isEmpty()) ? new File(path) : null;

            if (file != null && file.exists()) {
                Image img = new Image("file:" + file.getAbsolutePath());
                profileCircle.setFill(new ImagePattern(img));
            } else {
                File defaultFile = new File(DEFAULT_IMAGE_PATH);
                if (defaultFile.exists()) {
                    Image img = new Image("file:" + defaultFile.getAbsolutePath());
                    profileCircle.setFill(new ImagePattern(img));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ======================================================
    //                LOAD IMAGE INTO IMAGEVIEW
    // Used for NID Front & Back images
    // ======================================================

    private void loadImage(ImageView view, String path) {
        try {
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                if (file.exists()) {
                    view.setImage(new Image("file:" + file.getAbsolutePath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ======================================================
    //                OPEN MEDICAL PDF FILE
    // Uses Desktop API to open PDF externally
    // ======================================================

    private void openPdf(String path) {
        try {
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("PDF not found!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ======================================================
    //                SAFE STRING CHECK
    // Prevents null or empty value display
    // ======================================================

    private String safe(String value) {
        return (value != null && !value.isEmpty()) ? value : "N/A";
    }


    @FXML
    void editprof(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProf.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Edit Profile");

            popupStage.initOwner(btnEditProfile.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void editPP(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfilePic.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Update Profile Picture");

            popupStage.initOwner(btnUpdatePhoto.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void editdoc(ActionEvent event)throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDocuments.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Update Documents");

            popupStage.initOwner(btnUpdateDocuments.getScene().getWindow());
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ======================================================
    //                SIDEBAR NAVIGATION METHODS
    // (COMPLETELY UNCHANGED)
    // ======================================================

    @FXML
    void ProftoHome(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ProftoNews(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
        Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ProftoNoti(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
        Stage stage = (Stage) btnNotifications.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ProftoChat(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Stage stage = (Stage) btnChat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ProftoCreate(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        Stage stage = (Stage) btnCreatePost.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ProftoStng(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void logout_p(ActionEvent event) throws Exception {
        chkLogin.setlogout();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
