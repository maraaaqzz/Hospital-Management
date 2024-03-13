package code.gui;

import code.domain.Appointments;
import code.domain.Patient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class AppointmentDialog {
    public TextField IdField;
    public TextField PatientIdField;
    public TextField DateField;

    public Label label;
    public Label IDLabel;

    public Button addButton;

    public boolean isClosed = false;

    public enum DialogScope { SCOPE_ADD, SCOPE_UPDATE }

    private Optional<Appointments<Integer>> result;

    public void setScope(AppointmentDialog.DialogScope scope) {
        switch (scope) {
            case SCOPE_ADD:
                label.setText("Add appointment");
                addButton.setText("Add");
                break;

            case SCOPE_UPDATE:
                label.setText("Update appointment");
                addButton.setText("Update");
                break;
        }
    }

    public void setIDLabel(String id) {
        IDLabel.setText(id);
    }

    public void onAddButton() {
        String idText = IdField.getText();
        String pidText = PatientIdField.getText();
        System.out.println(IdField.getText());
        System.out.println(DateField.getText());
        if (idText.isEmpty()) {
            System.err.println("ID field is empty");
            return;
        }
        if (pidText.isEmpty()) {
            System.err.println("PatientID field is empty");
            return;
        }

        Integer id = Integer.valueOf(idText);
        Integer pid = Integer.valueOf(pidText);
        String date = DateField.getText();


        Stage stage = (Stage) this.DateField.getScene().getWindow();
        stage.close();

        result = Optional.of(new Appointments(id, pid, date));
    }


    public void onCancelButton() {
        isClosed = true;
        Stage stage = (Stage) this.DateField.getScene().getWindow();
        stage.close();
    }

    public Optional<Appointments<Integer>> getResult() {
        return result;
    }
}
