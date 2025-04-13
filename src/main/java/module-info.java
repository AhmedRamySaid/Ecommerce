module kyra.me.ecommerce {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires jbcrypt;

    opens kyra.me.ecommerce.Main to javafx.fxml;
    exports kyra.me.ecommerce.Main;
}