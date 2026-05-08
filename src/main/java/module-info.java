module com.examplecom.todolist {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.todolist to javafx.fxml;
    exports com.todolist;
}
