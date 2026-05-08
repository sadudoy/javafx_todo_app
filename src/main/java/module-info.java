module com.examplecom.todolist {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.examplecom.todolist to javafx.fxml;
    exports com.examplecom.todolist;
}
