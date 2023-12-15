package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {

    private int userId;
    private DatabaseHandler databaseHandler;

    @FXML
    private Label successLabel;

    @FXML
    private Button todosButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveTaskButton;
    private Task task;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();
        Task task = new Task();

        saveTaskButton.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {

                System.out.println("User Id: " + AddItemController.userId);

                task.setUserId(AddItemController.userId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                databaseHandler.insertTask(task);

                successLabel.setVisible(true);

                todosButton.setVisible(true);
                int taskNumber = 0;
                try {
                    taskNumber = databaseHandler.getAllTasks(AddItemController.userId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                todosButton.setText("My 2Do's: " + "("+taskNumber+")");

                taskField.setText("");
                descriptionField.setText("");

                todosButton.setOnAction(event1 ->{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/list.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                });


            }else {
                System.out.println("Nothing added!");
            }

        });

    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println(this.userId);

    }

}