package com.example.model.notification;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Information extends Confirmation {
    public Information(String title, String content) {
        super(title, content);
        this.showNotification();
    }

    public void showNotification() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(this.getTitle());
        alert.setContentText(this.getContent());
        alert.show();
    }
}
