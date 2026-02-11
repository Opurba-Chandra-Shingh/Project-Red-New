package com.example.projectredpulsenew;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditDocuments_Controller {

    @FXML private Button btnMedicalPdf, btnNidFront, btnNidBack, btnSave, btnCancel;

    private String medicalPdfPath, nidFrontPath, nidBackPath;

    private final String FILE_PATH = "D:\\project-redpulse-new\\src\\main\\resources\\com\\example\\projectredpulsenew\\UserDetails.json";

    // ================== FILE CHOOSERS ==================
    @FXML
    void chooseMedicalPdf(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fc.showOpenDialog(btnMedicalPdf.getScene().getWindow());
        if(file != null) medicalPdfPath = file.getAbsolutePath();
    }

    @FXML
    void chooseNidFront(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg"));
        File file = fc.showOpenDialog(btnNidFront.getScene().getWindow());
        if(file != null) nidFrontPath = file.getAbsolutePath();
    }

    @FXML
    void chooseNidBack(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg"));
        File file = fc.showOpenDialog(btnNidBack.getScene().getWindow());
        if(file != null) nidBackPath = file.getAbsolutePath();
    }

    // ================== SAVE CHANGES ==================
    @FXML
    void handleSaveDoc(ActionEvent event){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();

            ArrayList<User> users = gson.fromJson(new FileReader(FILE_PATH), listType);
            if(users == null) users = new ArrayList<>();

            User current = LoginDetails.getUser();  // Current logged-in user

            for(User u : users){
                if(u.getEmail().equals(current.getEmail()) && u.getPassword().equals(current.getPassword())){
                    // ================= UPDATE JSON & CURRENT OBJECT SIMULTANEOUSLY =================
                    if(medicalPdfPath != null){
                        u.setMedicalPdfPath(medicalPdfPath);
                        current.setMedicalPdfPath(medicalPdfPath);
                    }
                    if(nidFrontPath != null){
                        u.setNidFrontPath(nidFrontPath);
                        current.setNidFrontPath(nidFrontPath);
                    }
                    if(nidBackPath != null){
                        u.setNidBackPath(nidBackPath);
                        current.setNidBackPath(nidBackPath);
                    }
                    break;
                }
            }

            // Save updated list back to JSON
            try(FileWriter writer = new FileWriter(FILE_PATH)){
                gson.toJson(users, writer);
            }

            System.out.println("Documents updated successfully!");

            // Go back to Profile page
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // ================== CANCEL ==================
    @FXML
    void handleCancelDoc(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
