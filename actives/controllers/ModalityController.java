package actives.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ModalityController {
    public Label codeLabel;
    AuthController authController = new AuthController();

    @FXML
    void initialize(){
        String code = authController.saveCode();
        codeLabel.setText(code.substring(0, 8));
    }
}
