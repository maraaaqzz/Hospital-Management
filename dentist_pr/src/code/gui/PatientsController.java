package code.gui;

import code.Main;
import code.domain.Appointments;
import code.domain.Patient;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import code.service.*;
import code.repository.*;

import code.repository.IRepository;
import code.service.DBservice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import code.service.Service;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PatientsController implements Initializable {
    private DBservice dbserv;
    public TableView<Patient<Integer>> patientsTableView;
    public TableColumn<Patient, Integer> patientId;
    public TableColumn<Patient, String> patientName;
    public TableColumn<Patient, String> patientIllness;
    public TableColumn<Patient, Integer> patientAge;

    public TableView<Appointments<Integer>> appointmentsTableView;
    public TableColumn<Appointments, Integer> appointmentsId;
    public TableColumn<Appointments, Integer> appointmentsPatientId;
    public TableColumn<Appointments, String> appointmentDate;
    public PatientsController() {

    }
    private void refreshPatient(){
        ObservableList<Patient<Integer>> patientObservableList = FXCollections.observableList((List<Patient<Integer>>)dbserv.getPatients());
        this.patientsTableView.getItems().clear();
        this.patientsTableView.setItems(patientObservableList);
    }
    private void refreshAppointments()
    {
        List<Appointments<Integer>> appointments = (List<Appointments<Integer>>) dbserv.getAppointments();
        ObservableList<Appointments<Integer>> appointmentsObservableList = FXCollections.observableList(appointments);
        this.appointmentsTableView.getItems().clear();
        this.appointmentsTableView.setItems(appointmentsObservableList);
    }
    private Optional<Patient> getSelectedPatient() throws ItemNotFound {
        List<Patient> selectedItems = new ArrayList<>(this.patientsTableView.getSelectionModel().getSelectedItems());

        for (Patient p : selectedItems) {
            Optional<Patient> dbUser = Optional.ofNullable(this.dbserv.getPatient(p.getAge()));
            if (dbUser.isEmpty()) continue;

            return Optional.of(p);
        }
        return Optional.empty();
    }
    public void initializeTableView()
    {
        this.patientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.patientIllness.setCellValueFactory(new PropertyValueFactory<>("illness"));
        this.patientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        this.refreshPatient();

        this.appointmentsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.appointmentsPatientId.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        this.appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.refreshAppointments();
    }
    public void onAddPatientButton() throws IOException, DuplicateItemException {
        FXMLLoader patientAddFXMLLoader = new FXMLLoader(Main.class.getResource("add_patient.fxml"));
        Scene patientAddScene = new Scene(patientAddFXMLLoader.load());
        PatientDialog patientAddDialog = patientAddFXMLLoader.getController();
        patientAddDialog.setScope(PatientDialog.DialogScope.SCOPE_ADD);

        Stage patientAddStage = new Stage();
        patientAddStage.setScene(patientAddScene);

        patientAddStage.setOnCloseRequest(event -> patientAddDialog.onCancelButton());
        patientAddStage.showAndWait();

        if(patientAddDialog.isClosed){
            return;
        }
        try{
            Optional<Patient<Integer>> patientOptional = patientAddDialog.getResult();
            if(patientOptional.isEmpty())
                return;

            Patient patient = patientOptional.get();
            this.dbserv.addPatient(patient);
            this.refreshPatient();
        }catch(DuplicateItemException e)
        {
            //todo
        }

    }

    public void onRemovePatientButton() throws ItemNotFound {
        List<Patient<Integer>> selectedItems = new ArrayList<>(this.patientsTableView.getSelectionModel().getSelectedItems());

        for (Patient<Integer> p : selectedItems) {
            this.dbserv.removePatient(p.getId());
        }

        this.refreshPatient();
        this.patientsTableView.getSelectionModel().clearSelection();
    }

    public void onUpdatePatientButton() throws IOException, ItemNotFound {
        FXMLLoader patientAddFXMLLoader = new FXMLLoader(Main.class.getResource("add_patient.fxml"));
        Scene patientAddScene = new Scene(patientAddFXMLLoader.load());
        PatientDialog userAddDialogue = patientAddFXMLLoader.getController();
        userAddDialogue.setScope(PatientDialog.DialogScope.SCOPE_UPDATE);

        Optional<Patient> selectedPatient = getSelectedPatient();
        if (selectedPatient.isEmpty())
            return;

        Integer patientId = (Integer) selectedPatient.get().getId();

        userAddDialogue.setIDLabel(patientId.toString());

        Stage patientAddStage = new Stage();
        patientAddStage.setScene(patientAddScene);

        patientAddStage.setOnCloseRequest(event -> userAddDialogue.onCancelButton());
        patientAddStage.showAndWait();

        if (userAddDialogue.isClosed) {
            return;
        }

        try {
            Optional<Patient<Integer>> patientOptional = userAddDialogue.getResult();
            //System.out.println(patientOptional.get());
            if(patientOptional.isEmpty())
                return;

            Patient<Integer> patient = patientOptional.get();
            this.dbserv.updatePatient(patient.getId(), patient);
            this.refreshPatient();

        } catch (ItemNotFound e) {
            //TODO
        }
    }



    public void onAddAppointmentButton() throws IOException, DuplicateItemException {
        FXMLLoader appointmentAddFXMLLoader = new FXMLLoader(Main.class.getResource("add_appointment.fxml"));
        Scene appointmentAddScene = new Scene(appointmentAddFXMLLoader.load());
        AppointmentDialog appointmentAddDialog = appointmentAddFXMLLoader.getController();
        appointmentAddDialog.setScope(AppointmentDialog.DialogScope.SCOPE_ADD);

        Stage appointmentAddStage = new Stage();
        appointmentAddStage.setScene(appointmentAddScene);

        appointmentAddStage.setOnCloseRequest(event -> appointmentAddDialog.onCancelButton());
        appointmentAddStage.showAndWait();

        if (appointmentAddDialog.isClosed) {
            return;
        }

        try {
            Optional<Appointments<Integer>> appointmentOptional = appointmentAddDialog.getResult();
            if (appointmentOptional.isEmpty())
                return;

            Appointments<Integer> appointment = appointmentOptional.get();
            this.dbserv.addAppointment(appointment.getId(), appointment);
            this.refreshAppointments();
        } catch (DuplicateItemException e) {
            // Handle the exception
        }
    }

    public void onRemoveAppointmentButton() throws ItemNotFound {
        List<Appointments<Integer>> selectedItems = new ArrayList<>(this.appointmentsTableView.getSelectionModel().getSelectedItems());

        for (Appointments<Integer> appointment : selectedItems) {
            this.dbserv.removeAppointment(appointment.getId());
        }

        this.refreshAppointments();
        this.appointmentsTableView.getSelectionModel().clearSelection();
    }

    public void onUpdateAppointmentButton() throws IOException, ItemNotFound {
        FXMLLoader appointmentAddFXMLLoader = new FXMLLoader(Main.class.getResource("add_appointment.fxml"));
        Scene appointmentAddScene = new Scene(appointmentAddFXMLLoader.load());
        AppointmentDialog appointmentAddDialog = appointmentAddFXMLLoader.getController();
        appointmentAddDialog.setScope(AppointmentDialog.DialogScope.SCOPE_UPDATE);

        Optional<Appointments<Integer>> selectedAppointment = getSelectedAppointment();
        if (selectedAppointment.isEmpty())
            return;

        Integer appointmentId = selectedAppointment.get().getId();
        appointmentAddDialog.setIDLabel(appointmentId.toString());

        Stage appointmentAddStage = new Stage();
        appointmentAddStage.setScene(appointmentAddScene);

        appointmentAddStage.setOnCloseRequest(event -> appointmentAddDialog.onCancelButton());
        appointmentAddStage.showAndWait();

        if (appointmentAddDialog.isClosed) {
            return;
        }

        try {
            Optional<Appointments<Integer>> appointmentOptional = appointmentAddDialog.getResult();
            if (appointmentOptional.isEmpty())
                return;

            Appointments<Integer> appointment = appointmentOptional.get();
            this.dbserv.updateAppointment(appointment.getId(), appointment);
            this.refreshAppointments();

        } catch (ItemNotFound e) {
            // Handle the exception
        }
    }

    private Optional<Appointments<Integer>> getSelectedAppointment() throws ItemNotFound {
        List<Appointments<Integer>> selectedItems = new ArrayList<>(this.appointmentsTableView.getSelectionModel().getSelectedItems());

        for (Appointments p : selectedItems) {
            Optional<Appointments<Integer>> dbApp= Optional.ofNullable(this.dbserv.getAppointment((Integer)p.getId()));
            if (dbApp.isEmpty()) continue;

            return Optional.of(p);
        }
        return Optional.empty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientsDBRepository prepo = new PatientsDBRepository("Patients");
        AppointmentsDBRepository arepo = new AppointmentsDBRepository("Appointments");
        dbserv = new DBservice(arepo, prepo);

        initializeTableView();
    }
}
