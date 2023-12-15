package sample.DataBase;

import sample.model.Task;
import sample.model.User;

import javax.xml.transform.Source;
import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://" + dbHost + ":"
                +dbPort + "/"
                +dbName;
        Class.forName("org.postgresql.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser, dbPass);

        return dbConnection;
    }

    public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.TASKS_TABLE + " WHERE " +
                Const.STUDENT_ID + "=?" + " AND " + Const.TASKS_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, taskId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void signUpUser(User user) {

        String insert = "INSERT INTO "+Const.STUDENT_TABLE +"("+Const.STUDENT_FIRSTNAME
                +","+Const.STUDENT_LASTNAME+","+Const.STUDENT_USERNAME +","
                +Const.STUDENT_PASSWORD+","+Const.STUDENT_LOCATION+","
                +Const.STUDENT_GENDER+")" + "VALUES(?,?,?,?,?,?)";

        try  {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTaskByUser(int userId) {
        ResultSet resultTasks = null;

        String query = "SELECT * FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.STUDENT_ID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            preparedStatement.setInt(1, userId);

            resultTasks = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultTasks;
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.STUDENT_TABLE + " WHERE "
                    + Const.STUDENT_USERNAME + "=?" + " AND " + Const.STUDENT_PASSWORD
                    + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else  {
            System.out.println("Please enter your credentials");
        }

        return  resultSet;
    }

    public int getAllTasks(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.TASKS_STUDENT_ID + "=?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(1);

            }
            return resultSet.getInt(1);

    }


    public void insertTask(Task task) {

        String insert = "INSERT INTO "+Const.TASKS_TABLE +"("+Const.TASKS_STUDENT_ID
                +","+Const.TASKS_DATE
                +","+Const.TASKS_TASK+","+Const.TASKS_DESCRIPTION +")"
                + "VALUES(?,?,?,?)";

        try  {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1,task.getUserId());
            preparedStatement.setTimestamp(2, task.getDatecreated());
            preparedStatement.setString(3, task.getTask());
            preparedStatement.setString(4, task.getDescription());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}