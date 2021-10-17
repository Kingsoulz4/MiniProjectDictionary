package com.example.model.notification;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Confirmation {
    private boolean isConfirmed;
    private String title = "";
    private String content = "";

    public Confirmation(String title, String content) {
        this.title = title;
        this.content = content;
        this.showNotification();
    }

    public boolean isConfirmed() {
        return this.isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.isConfirmed = confirmed;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void showNotification() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(this.title);
        alert.setContentText(this.content);
        ButtonType btnTypeYes = new ButtonType("Yes");
        ButtonType btnTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(new ButtonType[]{btnTypeYes, btnTypeNo});
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == btnTypeYes) {
            this.setConfirmed(true);
        } else {
            this.setConfirmed(false);
        }

    }
}
