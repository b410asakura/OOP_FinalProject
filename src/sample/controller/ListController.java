package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.DataBase.DatabaseHandler;
import sample.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ListController {

    @FXML
    private ListView<Task> listTask;

    @FXML
    private TextField listTaskField;

    @FXML
    private TextField listDescriptionField;

    @FXML
    private Button listSaveTaskButton;

    private  ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        tasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTaskByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("task_id"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            tasks.addAll(task);
        }

        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());

        listSaveTaskButton.setOnAction(event -> {
            addNewTask();
        });

    }



    public void addNewTask() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        if (!listTaskField.getText().equals("")
                || !listDescriptionField.getText().equals("")) {
            Task myNewTask = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());


            myNewTask.setUserId(AddItemController.userId);
            myNewTask.setTask(listTaskField.getText().trim());
            myNewTask.setDescription(listDescriptionField.getText().trim());
            myNewTask.setDatecreated(timestamp);

            databaseHandler.insertTask(myNewTask);

            listTaskField.setText("");
            listDescriptionField.setText("");

            try {
                initialize();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
