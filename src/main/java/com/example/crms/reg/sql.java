package com.example.crms.reg;

import com.example.crms.HelloApplication;
import com.example.crms.databaseconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class sql
{
        @FXML
        private Button Nextbutton;
        @FXML
        private TextField nametextfield;
        @FXML
        private TextField phonenotextfield;
        @FXML
        private TextField addresstextfield;
        @FXML
        private TextField usernametextfield;
        @FXML
        private TextField passwordtextfield;
        @FXML
        private Button registerbutton;
        //public void registerbuttononaction(ActionEvent event) throws Exception {
          //  registeruser();
        //}
        @FXML
        public void NextbuttonOnAction(ActionEvent event)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Feedback.fxml"));
            Stage window=(Stage) Nextbutton.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    public void registerbuttononaction(ActionEvent event) throws Exception {
        String name = nametextfield.getText();
        String username = usernametextfield.getText();
        String password = passwordtextfield.getText();
        String phoneno = phonenotextfield.getText();
        String address = addresstextfield.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer?SSL=false", "root", "sharvi");

            PreparedStatement pst = con.prepareStatement("insert into info(Name,Username,Password,Phoneno,Address)values(?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, phoneno);
            pst.setString(5, address);
            int status = pst.executeUpdate();

            if (status == 1) {
                System.out.println("record added");
                nametextfield.setText("");
                usernametextfield.setText("");
                passwordtextfield.setText("");
                phonenotextfield.setText("");
                addresstextfield.setText("");
                nametextfield.requestFocus();
                //messageaftersubmit.setText("request submitted");
            } else {
                System.out.println("record failed");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }


