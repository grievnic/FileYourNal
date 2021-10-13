module de.entsesselt.de.fileyournal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens de.entsesselt.de.fileyournal to javafx.fxml;
    exports de.entsesselt.de.fileyournal;
}