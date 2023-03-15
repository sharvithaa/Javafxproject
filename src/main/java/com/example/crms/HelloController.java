package com.example.crms;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;


public class HelloController {
    @FXML
    private Label loginmessagelabel;
    @FXML
    private Button cancelbutton;
    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernametext;
    @FXML
    private PasswordField passwordtext;
    @FXML
    private Button loginbutton;

    public void loginbuttonOnAction(ActionEvent event) throws Exception {
                validateLogin();
    }
    public void signupbuttonOnAction(ActionEvent event) throws Exception {
        createAccountForm();
    }

    public void cancelbuttonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() throws Exception{
        databaseconnection connectnow=new databaseconnection();
        Connection connectdb=connectnow.getConnection();
        String verifyLogin = "SELECT count(1) FROM info WHERE Username='" + usernametext.getText() + "' AND Password='" + passwordtext.getText() + "'";
        try {
            Statement statement =connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);
            while(queryResult.next())
            {
                if(queryResult.getInt(1)==1)
                {
                    //createAccountForm();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Feedback.fxml"));
                    Stage window = (Stage) loginbutton.getScene().getWindow();
                    try {
                        window.setScene(new Scene(fxmlLoader.load()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //loginmessagelabel.setText("Welcome");
                }
                else
                {
                    loginmessagelabel.setText("Invalid Login. please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccountForm()
    {
        //registercontroller r=new registercontroller();
        try
        {
            Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Details.fxml")));
            Stage registerStage=new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private RadioButton yes1;
    @FXML
    private RadioButton no1;
    @FXML
    private RadioButton yes2;
    @FXML
    private RadioButton no2;
    @FXML
    private RadioButton yes3;
    @FXML
    private RadioButton no3;
    @FXML
    private Button Endbutton;
    @FXML
    private Button Closebutton;
    @FXML
    private Label lone;
    @FXML
    private Label ltwo;

    public void EndbuttonOnAction(ActionEvent event) {

        if(yes1.isSelected() && yes2.isSelected()||yes1.isSelected()&&yes3.isSelected()||yes2.isSelected()&&yes3.isSelected())
        {
            lone.setText("Thank you for your feedback!");
        }
        else if (no1.isSelected()&&no2.isSelected()) {
            ltwo.setText("We will improve quality and deliver on time.");
        }
        else if(no2.isSelected()&&no3.isSelected()){
            ltwo.setText("We will improve deliver time and reduce the cost.");
        }
        else if(no1.isSelected()&&no3.isSelected())
        {
            ltwo.setText("We will improve quality and reduce cost");
        }
        else {
            lone.setText("We will try our best next time.");
        }
    }
    public void ClosebuttonOnAction(ActionEvent event) {
        Stage stage = (Stage) Closebutton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


}


