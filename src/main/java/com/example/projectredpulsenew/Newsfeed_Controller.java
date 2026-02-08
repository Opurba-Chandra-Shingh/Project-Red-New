package com.example.projectredpulsenew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Newsfeed_Controller implements Initializable {

    @FXML
    private Label appNameLabel;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnCreatePost;

    @FXML
    private Button btnNewsHome;

    @FXML
    private Button btnNewsFeed;

    @FXML
    private Button btnNotifications;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnLogOut;

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
    private VBox feedContainer;

    @FXML
    void NewstoDash(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnNewsHome.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NewstoCreat(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        Stage stage = (Stage) btnCreatePost.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NewstoNoti(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
        Stage stage = (Stage) btnNotifications.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NewstoChat(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Stage stage = (Stage) btnChat.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NewstoProf(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) btnProfile.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NewstoStng(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPosts();
    }

    private void loadPosts() {
        String filePath = "PostDetails.json";
        List<PostDetails> posts = new ArrayList<>();
        Gson gson = new Gson();

        try {
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                Reader reader = new FileReader(file);
                Type listType = new TypeToken<List<PostDetails>>(){}.getType();
                List<PostDetails> existingPosts = gson.fromJson(reader, listType);

                if (existingPosts != null && !existingPosts.isEmpty()) {
                    posts = existingPosts;
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading posts: " + e.getMessage());
        }

        // Clear any previous posts (keep only the header section)
        if (feedContainer.getChildren().size() > 1) {
            feedContainer.getChildren().remove(1, feedContainer.getChildren().size());
        }

        // If no posts, show a message
        if (posts.isEmpty()) {
            Label emptyLabel = new Label("No blood requests yet. Be the first to post!");
            emptyLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 16px;");
            VBox.setMargin(emptyLabel, new Insets(30, 0, 0, 0));
            feedContainer.getChildren().add(emptyLabel);
            return;
        }

        // Reverse to show newest posts first
        Collections.reverse(posts);

        // Add each post card
        for (PostDetails post : posts) {
            System.out.println("Adding post for: " + post.getUserName());
            addPostCard(post);
        }
    }

    private void addPostCard(PostDetails post) {
        // 1. Main Card Container
        VBox card = new VBox();
        card.setMaxWidth(800.0);
        card.getStyleClass().add("feed-card");
        card.setPadding(new Insets(20, 25, 20, 25));
        VBox.setMargin(card, new Insets(0, 0, 15, 0));

        // ================= Top Row: Avatar, Name, Time =================
        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER_LEFT);

        // Avatar Circle
        Circle avatar = new Circle(20);
        avatar.setFill(Color.web("#e0e0e0"));
        avatar.setStroke(Color.WHITE);
        avatar.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);

        // Name and Time Box
        VBox nameBox = new VBox();

        // ✅ Fixed: Get userName from post object only
        String displayName = post.getUserName(); // Already has fallback in getter

        Label userName = new Label(displayName);
        userName.setTextFill(Color.web("#1a1a1a"));
        userName.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label timeLabel = new Label("Recently posted");
        timeLabel.setTextFill(Color.web("#888888"));
        timeLabel.setFont(Font.font(12));

        nameBox.getChildren().addAll(userName, timeLabel);

        // Spacer
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Urgent Badge
        Label urgent = new Label("URGENT");
        urgent.getStyleClass().add("urgent-badge");

        topRow.getChildren().addAll(avatar, nameBox, spacer, urgent);

        // ================= Separator =================
        Separator sep = new Separator();
        sep.setStyle("-fx-opacity: 0.5;");
        VBox.setMargin(sep, new Insets(15, 0, 15, 0));

        // ================= Content Row: Blood Group + Grid =================
        HBox contentRow = new HBox(25);

        // Blood Group Indicator
        StackPane bloodPane = new StackPane();
        bloodPane.getStyleClass().add("blood-group-indicator");

        Circle bigCircle = new Circle(35);
        bigCircle.setFill(Color.web("#ffebee"));
        bigCircle.setStroke(Color.web("#ffcdd2"));
        bigCircle.setStrokeWidth(2);
        bigCircle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);

        Label bloodLabel = new Label(post.getBloodGroup());
        bloodLabel.setTextFill(Color.web("#d32f2f"));
        bloodLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        bloodPane.getChildren().addAll(bigCircle, bloodLabel);

        // Details Grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);
        HBox.setHgrow(grid, Priority.ALWAYS);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.SOMETIMES);
        col1.setMaxWidth(130);
        col1.setMinWidth(100);
        col1.setPrefWidth(110);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        col2.setMinWidth(10);

        grid.getColumnConstraints().addAll(col1, col2);

        addGridRow(grid, 0, "Hospital:", post.getLocation(), true);
        addGridRow(grid, 1, "Patient Name:", post.getPatientName(), false);
        addGridRow(grid, 2, "Date Needed:", post.getDateNeeded(), false);
        addGridRow(grid, 3, "Units (Bags):", post.getUnits(), false);
        addGridRow(grid, 4, "Contact:", post.getPhone(), false);

        contentRow.getChildren().addAll(bloodPane, grid);

        // ================= Notes Section =================
        VBox notesBox = new VBox(5);
        VBox.setMargin(notesBox, new Insets(10, 0, 0, 0));

        Label notesTitle = new Label("Notes:");
        notesTitle.getStyleClass().add("detail-label-title");

        Label notesText = new Label(post.getNotes());
        notesText.setTextFill(Color.web("#666666"));
        notesText.setWrapText(true);

        notesBox.getChildren().addAll(notesTitle, notesText);

        // ================= Action Buttons =================
        HBox actions = new HBox(15);
        actions.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(actions, new Insets(20, 0, 0, 0));

        Button btnInfo = new Button("Contact Info");
        btnInfo.getStyleClass().add("btn-outline");

        Button btnInterest = new Button("Interested");
        btnInterest.getStyleClass().add("btn-filled");

        actions.getChildren().addAll(btnInfo, btnInterest);

        // ================= Final Assembly =================
        card.getChildren().addAll(topRow, sep, contentRow, notesBox, actions);

        // Add card to feed container
        feedContainer.getChildren().add(card);
    }

    private void addGridRow(GridPane grid, int row, String titleText, String valueText, boolean isBold) {
        Label title = new Label(titleText);
        title.getStyleClass().add("detail-label-title");

        Label value = new Label(valueText);

        if (isBold) {
            value.setTextFill(Color.web("#444444"));
            value.setFont(Font.font("System", FontWeight.BOLD, 14));
        } else {
            value.setTextFill(Color.web("#555555"));
        }

        grid.add(title, 0, row);
        grid.add(value, 1, row);
    }

    @FXML
    void logout_n(ActionEvent event) throws Exception {
        chkLogin.setlogout();

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}