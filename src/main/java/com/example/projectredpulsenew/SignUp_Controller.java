package com.example.projectredpulsenew;

import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SignUp_Controller implements Initializable {

    @FXML
    private Button signupBtn;

    @FXML
    private TextField age;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirm_pass;

    @FXML
    private ComboBox<String> genderItems;

    @FXML
    private ComboBox<String> bloodGroupOption;

    @FXML
    private ComboBox<String> district_items;

    @FXML
    private Label signupMassage;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderItems.setItems(FXCollections.observableArrayList("Male", "Female"));
        bloodGroupOption.setItems(FXCollections.observableArrayList("O+", "A+", "B+", "AB+", "O-", "A-", "B-", "AB-"));

        district_items.setItems(FXCollections.observableArrayList(
                "Bagerhat", "Bandarban", "Barguna", "Barishal", "Bhola", "Bogura",
                "Brahmanbaria", "Chandpur", "Chapainawabganj", "Chattogram", "Chuadanga",
                "Cox's Bazar", "Cumilla", "Dhaka", "Dinajpur", "Faridpur", "Feni",
                "Gaibandha", "Gazipur", "Gopalganj", "Habiganj", "Jamalpur", "Jashore",
                "Jhalokati", "Jhenaidah", "Joypurhat", "Khagrachhari", "Khulna",
                "Kishoreganj", "Kurigram", "Kushtia", "Lakshmipur", "Lalmonirhat",
                "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar",
                "Munshiganj", "Mymensingh", "Naogaon", "Narail", "Narayanganj",
                "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali", "Pabna",
                "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi",
                "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur",
                "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon"
        ));

    }

    private void GenderSelect(ActionEvent event) {
        String gender = genderItems.getValue();
        if (gender == null || gender.isEmpty()) {
            System.out.println("Please select a gender.");
            return;
        }
        //System.out.println("Selected gender: " + gender);
    }

    private void bloodGroupSelect(ActionEvent event) {
        String Group = bloodGroupOption.getValue();
        if (Group == null || Group.isEmpty()) {
            System.out.println("Please select a blood group.");
            return;
        }
        //System.out.println("Selected gender: " + gender);
    }

    private void districtSelect(ActionEvent event) {
        String district = district_items.getValue();
        if (district == null || district.isEmpty()) {
            System.out.println("Please select a district.");
            return;
        }
        //System.out.println("Selected gender: " + gender);
    }


    private void saveUserToJson(User user) {
        try {
            File file = new File("D:\\Projects\\Redpulse\\src\\main\\resources\\com\\example\\projectdemo3\\UserDetails.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<User> userList;

            if (file.exists()) {
                Reader reader = new FileReader(file);
                Type listType = new TypeToken<List<User>>(){}.getType();
                userList = gson.fromJson(reader, listType);
                if (userList == null) {
                    userList = new ArrayList<>();
                }
                reader.close();
            } else {
                userList = new ArrayList<>();
            }


            userList.add(user);


            // Write pretty JSON
            Writer writer = new FileWriter(file);
            gson.toJson(userList, writer);
            writer.close();

            System.out.println("User saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void signup(ActionEvent event) throws Exception{
        try {

            String nameInput = name.getText();
            String ageInput = age.getText();
            String genderInput = genderItems.getValue();
            String bloodInput = bloodGroupOption.getValue();
            String districtInput = district_items.getValue();
            String emailInput = email.getText();
            String passwordInput = password.getText();
            String confirmPasswordInput = confirm_pass.getText();



            if (nameInput.isEmpty()) {
                signupMassage.setText("Please Enter your Name!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (ageInput.isEmpty()) {
                signupMassage.setText("Please Enter your Age!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (genderInput == null || genderInput.isEmpty()) {
                signupMassage.setText("Please Select your Gender!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (bloodInput == null || bloodInput.isEmpty()) {
                signupMassage.setText("Please Select your Blood Group!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (districtInput == null || districtInput.isEmpty()) {
                signupMassage.setText("Please Enter your Address!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (emailInput.isEmpty()) {
                signupMassage.setText("Please Enter a valid Email!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (passwordInput == null || passwordInput.isEmpty()) {
                signupMassage.setText("Please enter a Valid Password!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (confirmPasswordInput == null || confirmPasswordInput.isEmpty()) {
                signupMassage.setText("Please Write again to Confirm the Password!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }
            else if (!passwordInput.equals(confirmPasswordInput)) {
                signupMassage.setText("Passwords do not match!");
                signupMassage.setStyle("-fx-text-fill: red;");
                return;
            }



            User newUser = new User(nameInput, ageInput, genderInput, bloodInput, districtInput, emailInput, passwordInput);

            saveUserToJson(newUser);

            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Stage stage = (Stage)signupBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

