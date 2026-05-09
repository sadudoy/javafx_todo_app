package com.todolist;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    int total = 0;
    int completed = 0;
    @Override
    public void start(Stage stage) throws IOException {
        HBox label = new HBox(10);
        label.setStyle("-fx-background-color: skyblue");
        label.setPadding(new Insets(10));
        Text labelText = new Text("To-Do List"); 
        labelText.setStyle("-fx-font-size: 25; -fx-font-weight: bold;" );
        label.setAlignment(Pos.CENTER);
        label.getChildren().addAll(labelText);
        label.setPrefWidth(Double.MAX_VALUE);

        HBox totalTask = new HBox(20);
        totalTask.setMaxWidth(Double.MAX_VALUE);
        totalTask.setPadding(new Insets(2, 0, 2, 5));
        totalTask.setStyle("-fx-background-color: #98ff9573");
        Text totalText = new Text("Total Task " + total);
        totalText.setStyle("-fx-font-size: 15; -fx-fill: #00178c; -fx-font-weight: bold;" );
        Text CompletedText = new Text("Completed " + completed);
        CompletedText.setStyle("-fx-font-size: 15; -fx-fill: #005b0b; -fx-font-weight: bold;" );
        totalTask.getChildren().addAll(totalText, CompletedText);

        HBox task = new HBox(20);
        task.setPadding(new Insets(5, 0, 5, 0));
        task.setPrefWidth(Double.MAX_VALUE);
        TextField taskText = new TextField();
        taskText.setStyle("-fx-font-size: 15; -fx-background-radius: 5; -fx-border-color: skyblue; -fx-border-radius: 5; ");
        taskText.setPromptText("Enter a new task..");
        HBox.setHgrow(taskText, Priority.ALWAYS);
        taskText.setMaxWidth(Double.MAX_VALUE);    

        Button addTask = new Button("ADD");
        addTask.setPrefWidth(70);
        addTask.setStyle("-fx-font-size: 15; -fx-background-radius: 5; -fx-background-insets: 0.5; -fx-border-radius: 5; -fx-background-color: #9ad5ff; -fx-font-weight: bold; -fx-border-weight: 1; -fx-border-color: black;");
        task.getChildren().addAll(taskText, addTask);


        VBox taskList = new VBox(15);
        ScrollPane scrollPane = new ScrollPane(taskList);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background-radius: 10;");
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(10);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        root.setPadding(new Insets(10));
        root.setFillWidth(true);
        root.getChildren().addAll(label, totalTask, task, scrollPane);


        Scene scene = new Scene(root, 450, 600);
        stage.setTitle("To-Do List");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/todologo.png")));
        stage.setScene(scene);
        stage.show();


        addTask.setOnAction(e->{

            if (taskText.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Task");
                alert.setContentText("Please enter a task before adding!");
                alert.showAndWait();
                return; 
            }

            total++;
            totalText.setText("Total Task " + total);
            

            HBox task1 = new HBox();
            task1.setPrefHeight(30);
            task1.setStyle("-fx-background-color: #D1F0FF ; -fx-background-radius: 5;");
            task1.setAlignment(Pos.CENTER);
            task1.setPadding(new Insets(5));
            CheckBox taskText2 = new CheckBox(taskText.getText());

            Button deleteTask = new Button("DELETE");
            deleteTask.setStyle("-fx-background-color: #F97316; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-background-insets: 0.5; -fx-border-radius: 5; -fx-border-color: black; ");
            deleteTask.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            deleteTask.setAlignment(Pos.CENTER_RIGHT);
            HBox.setHgrow(taskText2, Priority.ALWAYS);
            taskText2.setMaxWidth(Double.MAX_VALUE);
            taskText2.setStyle("-fx-opacity: 1.0; -fx-font-size: 15; -fx-box-color: white;" );
            task1.getChildren().addAll(taskText2, deleteTask);
            taskList.getChildren().add(task1);
            taskText.clear();


            deleteTask.setOnAction(ev->{
                taskList.getChildren().remove(task1);
                total--;
                totalText.setText("Total Task " + total);
                if(taskText2.isSelected()) {
                    completed--;
                    CompletedText.setText("Completed " + completed);
                }
            });


           taskText2.setOnAction(event -> {
                if (taskText2.isSelected()) {

                    completed++;
                    CompletedText.setText("Completed " + completed);

                    taskText2.setStyle( "-fx-opacity: 1; -fx-font-size: 15; -fx-box-color: #BBF7D0; -fx-mark-color: #009637;" );
                    task1.setStyle("-fx-background-color: #BBF7D0; -fx-background-radius: 5;");
                    } else {
                        completed--;
                        CompletedText.setText("Completed " + completed);               

                        taskText2.setStyle("-fx-opacity: 1.0; -fx-font-size: 15; -fx-box-color: white; ");
                        task1.setStyle("-fx-background-color: #D1F0FF; -fx-background-radius: 5;");
                    }

                Node text = taskText2.lookup(".text");
                if (taskText2.isSelected()) text.setStyle("-fx-strikethrough: true; -fx-opacity: 0.5" ); 
                else  text.setStyle("-fx-strikethrough: false; -fx-opacity: 1.0" ) ;   
            });

        });
    }

    public static void main(String[] args) {
        launch();
    }

}

