package com.example.projectredpulsenew;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class Notification_Controller {

    @FXML
    private Label appNameLabel;

    @FXML
    private Button btnChat, btnCreatePost, btnLogOut, btnNewsFeed, btnHome, btnNotifications, btnProfile, btnSettings;

    @FXML
    private VBox sidebar, sidebarButtons, notificationContainer;

    @FXML
    private HBox sidebarHeader, topBar, topBarRight;

    // ================== NAVIGATION BUTTONS ==================
    @FXML
    void NotitoNews(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Newsfeed.fxml"));
        Stage stage = (Stage) btnNewsFeed.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NotitoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NotitoCreate(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        Stage stage = (Stage) btnCreatePost.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NotitoChat(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Stage stage = (Stage) btnChat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NotitoProf(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) btnProfile.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NotitoStng(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void logout_noti(ActionEvent event) throws Exception {
        chkLogin.setlogout();
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // ================== INITIALIZE ==================
    @FXML
    public void initialize() {
        loadNotifications();
    }

    // ================== LOAD NOTIFICATIONS ==================
    private void loadNotifications() {
        try {
            Gson gson = new Gson();
            InputStream is = getClass().getResourceAsStream("/com/example/projectredpulsenew/Noti.json");
            Reader reader = new InputStreamReader(is);

            Type listType = new TypeToken<List<NotificationDetails>>(){}.getType();
            List<NotificationDetails> notiList = gson.fromJson(reader, listType);

            User Cur_user = LoginDetails.getUser();
            String currentEmail = Cur_user.getEmail();
            String currentPass  = Cur_user.getPassword();

            notificationContainer.getChildren().clear(); // Clean previous

            for (NotificationDetails n : notiList) {
                // Only show notifications for current logged-in user
                if (n.getReceiverEmail().equals(currentEmail)
                        && n.getReceiverPassword().equals(currentPass)) {

                    HBox card = createNotificationCard(n);
                    notificationContainer.getChildren().add(card);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading notifications: " + e.getMessage());
        }
    }

    // ================== CREATE DYNAMIC CARD ==================
    private HBox createNotificationCard(NotificationDetails n) {

        HBox card = new HBox(15);
        card.setMaxWidth(900);
        card.getStyleClass().add("noti-card");

        // Icon Background
        StackPane iconBg = new StackPane();
        iconBg.setPrefSize(50, 50);

        Label title = new Label();
        VBox infoBox = new VBox(2);

        if (n.getNotiType().equalsIgnoreCase("interested")) {
            iconBg.getStyleClass().add("noti-icon-bg-red");
            title.setText(n.getClickerName() + " showed interest in your post");

            Label emailLabel = new Label("Donor's email: " + n.getClickerEmail());
            Label numberLabel = new Label("Donor's number: " + n.getClickerNumber());
            Label bloodLabel = new Label("Donor's Blood group: " + n.getClickerBloodgroup());
            Label districtLabel = new Label("Donor's District: " + n.getClickerDistrict());

            emailLabel.getStyleClass().add("noti-subtitle");
            numberLabel.getStyleClass().add("noti-subtitle");
            bloodLabel.getStyleClass().add("noti-subtitle");
            districtLabel.getStyleClass().add("noti-subtitle");

            infoBox.getChildren().addAll(emailLabel, numberLabel, bloodLabel, districtLabel);

        } else if (n.getNotiType().equalsIgnoreCase("request")) {
            iconBg.getStyleClass().add("noti-icon-bg-gray");
            title.setText("New request blood request posted");

        }

        title.getStyleClass().add("noti-title");
//        subtitle.getStyleClass().add("noti-subtitle");

        VBox textBox = new VBox(4, title, infoBox);
        HBox.setHgrow(textBox, Priority.ALWAYS);

        Label time = new Label("Just now");
        time.getStyleClass().add("noti-time");

        card.getChildren().addAll(iconBg, textBox, time);

        // Optionally: click to open post if postId exists
        /*
        if (n.getPostId() != null) {
            card.setOnMouseClicked(e -> openPost(n.getPostId()));
        }
        */

        return card;
    }
}
