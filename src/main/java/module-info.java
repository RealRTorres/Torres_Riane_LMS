module edu.valencia.lms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens edu.valencia.lms to javafx.fxml;
    exports edu.valencia.lms.server.chapter33;
    exports edu.valencia.lms.client.chapter33;
}