package actives.controllers;

import actives.database.DatabaseHandler;
import actives.entities.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class AuthController {
    public TextField idTextField;
    public TextField passwordTextField;
    public TextField codeTextField;
    public Button cancelButton;
    public Button authButton;
    public ImageView getCodeImg;
    public Label errorLabel;

    public ObservableList<User> onInputId() {
        errorLabel.setText("");
        DatabaseHandler handler = new DatabaseHandler();
        if (!idTextField.getText().equals("")) {
            int id = Integer.parseInt(idTextField.getText());
            User user = new User();
            user.setId_user(id);
            ObservableList<User> users = handler.getUserById(user);
            if (users.size() > 0) {
                passwordTextField.setDisable(false);
                authButton.setDisable(false);
                getCodeImg.setDisable(false);
                codeTextField.setDisable(false);
                return users;
            } else {
                errorLabel.setText("Пользователь с указанным номером не найден");
            }
        } else {
            errorLabel.setText("Пустое поле ввода номера");
        }
        return null;
    }


    public void onClickGetCode() throws IOException {
        errorLabel.setText("");
        if (!passwordTextField.getText().equals("")) {
            String password = passwordTextField.getText();
            ObservableList<User> users = onInputId();
            String userPassword = users.get(0).getPassword();
            if (userPassword.equals(password)) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/actives/views/modality.fxml"));
                Parent root = fxmlLoader.load();
                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(new Scene(root, 300, 180));
                stage.setTitle("Код");
                stage.setResizable(false);
                stage.show();
            } else {
                errorLabel.setText("Введен неверный пароль");
            }
        } else {
            errorLabel.setText("Пустое поле ввода пароля");
        }
    }

    public void onClickCancel() {
    }

    public void onClickAuth() throws IOException {
        errorLabel.setText("");
        if (!codeTextField.getText().equals("")) {
            ModalityController modalityController = new ModalityController();
            String code = codeTextField.getText();
            String codes = saveCode().toString();
            if (code.equals(codes)) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/actives/views/new.fxml"));
                Parent root = fxmlLoader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root, 600, 400));
                stage.setTitle("Успешная авторизация");
                stage.setResizable(false);
                stage.show();
            } else {
                errorLabel.setText("Введен неверный код");
            }
        } else {
            errorLabel.setText("Пустое поле ввода кода");
        }
    }

    public String createCode() {
        String all = "1";
        String code = "";
        StringBuilder result = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            result.append(all.charAt(rand.nextInt(all.length())));
            code += result.toString();
        }
        return code;
    }

    public String saveCode() {
        final String code = createCode().substring(0, 8);
        return code;
    }
}
