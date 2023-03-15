package com.example.crms;
import com.example.crms.HelloApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class registercontroller {
        @FXML
        private Button Nextbutton;
        @FXML
        private TextField nametextfield;
        @FXML
        private TextField usernametextfield;
        @FXML
        private TextField passwordtextfield;
        @FXML
        private TextField addresstextfield;
        @FXML
        private TextField phonenotextfield;
        @FXML
        private Button registerbutton;

        Connection con;
        PreparedStatement pst;
        @FXML
        public void NextbuttonOnAction(ActionEvent e)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Feedback.fxml"));
            Stage window=(Stage) Nextbutton.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        @FXML
        public void registerbuttonOnAction(ActionEvent event)
        {
            String name = nametextfield.getText();
            String username = usernametextfield.getText();
            String phoneno = phonenotextfield.getText();
            String password = passwordtextfield.getText();
            String address = addresstextfield.getText();

            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con =DriverManager.getConnection("jdbc:mysql://localhost:3306/customer?SSL=false","root","sharvi");

                pst = con.prepareStatement("insert into info(Name,Username,Password,Phoneno,Address)values(?,?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,username);
                pst.setString(3,password);
                pst.setString(4,phoneno);
                pst.setString(5,address);
                int status= pst.executeUpdate();

                if(status==1)
                {
                    System.out.println("Record added");
                    nametextfield.setText("");
                    usernametextfield.setText("");
                    passwordtextfield.setText("");
                    phonenotextfield.setText("");
                    addresstextfield.setText("");
                    nametextfield.requestFocus();

                }
                else {
                    System.out.println("Record failed");
                }
            }
            catch(ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            catch(SQLException e)
            {
                throw new RuntimeException(e);
            }
        }

    }




