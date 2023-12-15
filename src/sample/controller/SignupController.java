package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase.DatabaseHandler;
import sample.model.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private TextField signUpPassword;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {


        signUpButton.setOnAction(event -> {
            createUser();

            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));


            try {
                loader.load();


            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        });

    }

    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();

        String gender;
        if (signUpCheckBoxFemale.isSelected()){
            gender = "Female";
        }else gender = "Male";

        User user = new User(firstName, lastName, userName, password, location,gender);


        databaseHandler.signUpUser(user);
    }
}