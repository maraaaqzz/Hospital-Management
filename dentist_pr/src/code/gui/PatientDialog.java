package code.gui;

import code.domain.Patient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class PatientDialog {
    public TextField NameField;
    public TextField illnessField;
    public TextField ageField;
    public TextField IdField;

    public Label label;
    public Label IDLabel;

    public Button addButton;

    public boolean isClosed = false;

    public enum DialogScope { SCOPE_ADD, SCOPE_UPDATE }

    private Optional<Patient<Integer>> result;

    public void setScope(DialogScope scope) {
        switch (scope) {
            case SCOPE_ADD:
                label.setText("Add patient");
                addButton.setText("Add");
                break;

            case SCOPE_UPDATE:
                label.setText("Update patient");
                addButton.setText("Update");
                break;
        }
    }

    public void setIDLabel(String id) {
        IDLabel.setText(id);
    }

    public void onAddButton() {
        String idText = IdField.getText();
        System.out.println(IdField.getText());
        System.out.println(NameField.getText());
        System.out.println(illnessField.getText());
        System.out.println(ageField.getText());
        if (idText.isEmpty()) {
            System.err.println("ID field is empty");
            return;
        }

        Integer id = Integer.valueOf(idText);
        String name = NameField.getText();
        String illness = illnessField.getText();

        int age = 0;
        String ageText = ageField.getText();
        if (!ageText.isEmpty()) {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                System.err.println("Invalid age format");
                return;
            }
        }

        Stage stage = (Stage) this.NameField.getScene().getWindow();
        stage.close();

        result = Optional.of(new Patient(id, name, illness, age));
    }


    public void onCancelButton() {
        isClosed = true;
        Stage stage = (Stage) this.NameField.getScene().getWindow();
        stage.close();
    }

    public Optional<Patient<Integer>> getResult() {
        return result;
    }
}
