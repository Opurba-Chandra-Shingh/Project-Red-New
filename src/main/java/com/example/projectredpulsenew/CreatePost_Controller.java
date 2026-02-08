package com.example.projectredpulsenew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreatePost_Controller {

    @FXML private Label appNameLabel;

    @FXML private Button btnChat;
    @FXML private Button btnCreatePost;
    @FXML private Button btnLogOut;
    @FXML private Button btnNewsFeed;
    @FXML private Button btnHome;
    @FXML private Button btnNotifications;
    @FXML private Button btnProfile;
    @FXML private Button btnSettings;

    @FXML private VBox sidebar;
    @FXML private VBox sidebarButtons;

    @FXML private HBox sidebarHeader;
    @FXML private HBox topBar;
    @FXML private HBox topBarRight;

    // Form fields
    @FXML private TextField patientNameField;
    @FXML private ComboBox<String> bloodGroupField;
    @FXML private TextField unitNeededField;
    @FXML private TextField locationField;
    @FXML private DatePicker dateNeededField;
    @FXML private TextField phoneField;
    @FXML private TextArea notesArea;
    @FXML private Button submitBtn;

    // ------------------- Navigation Buttons -------------------

    @FXML
    void CreatetoNews(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
        Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void CreatetoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void CreatetoNoti(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
        Stage stage = (Stage) btnNotifications.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void CreatetoChat(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Stage stage = (Stage) btnChat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void CreatetoProf(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) btnProfile.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void CreatetoStng(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // ------------------- Save Data to JSON -------------------

    private void savePostToJson(PostDetails post) {
//        String filePath = "PostDetails.json";
        String filePath = System.getProperty("user.home") + File.separator + "PostDetails.json";


       File file = null;
//        System.out.println("Saving to file at: " + file.getAbsolutePath());

        List<PostDetails> posts = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                Reader reader = new FileReader(file);
                Type listType = new TypeToken<List<PostDetails>>() {
                }.getType();
                List<PostDetails> existingPosts = gson.fromJson(reader, listType);
                if (existingPosts != null) posts = existingPosts;
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading existing posts: " + e.getMessage());
        }

        posts.add(post);

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(posts, writer);
            System.out.println("Post saved successfully for user: " + post.getUserName());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving post: " + e.getMessage());
        }
    }

    // ------------------- Submit Button -------------------

    @FXML
    public void submitPost(ActionEvent event) {
        try {
            String patient = patientNameField.getText();
            String blood = bloodGroupField.getValue();
            String unit = unitNeededField.getText();
            String loc = locationField.getText();
            String date = dateNeededField.getValue() != null ? dateNeededField.getValue().toString() : "";
            String phone = phoneField.getText();
            String notes = notesArea.getText();

            // Validation
            if (patient.isEmpty() || phone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please fill required fields (Patient Name and Phone).");
                alert.show();
                return;
            }

            if (blood == null || blood.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a blood group.");
                alert.show();
                return;
            }

            // ✅ Get current user's name - Only from LoginDetails
            String currentUserName = "Anonymous"; // Fallback

            try {
                User temp2 = LoginDetails.getUser();
                if (temp2 != null && temp2.getName() != null && !temp2.getName().isEmpty()) {
                    currentUserName = temp2.getName();
                }
            } catch (Exception e) {
                System.out.println("Could not get username: " + e.getMessage());
            }

            // Create post with userName
            PostDetails post = new PostDetails(
                    currentUserName,  // userName from logged-in user
                    patient,          // patientName
                    blood,            // bloodGroup
                    unit,             // units
                    loc,              // location
                    date,             // dateNeeded
                    phone,            // phone
                    notes             // notes
            );

            System.out.println("Creating post by: " + currentUserName);

            savePostToJson(post);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Post Created!");
            alert.setContentText("Your blood donation request has been posted to the newsfeed.");
            alert.showAndWait();

            clearForm();

            // Navigate to newsfeed
            Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to create post: " + e.getMessage());
            alert.show();
        }
    }

    private void clearForm() {
        patientNameField.clear();
        bloodGroupField.setValue(null);
        unitNeededField.clear();
        locationField.clear();
        dateNeededField.setValue(null);
        phoneField.clear();
        notesArea.clear();
    }

    @FXML
    void logout_c(ActionEvent event) throws Exception {
        chkLogin.setlogout();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}