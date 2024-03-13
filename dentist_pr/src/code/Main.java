package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DentistGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dentist cabinet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
//    public static <Ui> void java.main(String[] args) throws DuplicateItemException, ItemNotFound {
//        PatientRepository patientRepo = new PatientRepository();
//        AppointmentRepo appointmentRepo = new AppointmentRepo();
//
//        PatientsDBRepository prepo = new PatientsDBRepository("Patients");
//        AppointmentsDBRepository arepo = new AppointmentsDBRepository("Appointments");
//
//        FileRepository<Integer, Appointments<Integer>> arbf = null;
//        FileRepository<Integer, Patient<Integer>> prbf = null;
//
//        Settings set = new Settings();
//
//        if (Objects.equals(set.getRepositoryType(), "binary")) {
//            arbf = new AppointmentRepositoryBinaryFile(set.getAppointmentsFile());
//            prbf = new PatientRepositoryBinaryFile(set.getPatientsFile());
//            System.out.println(set.getAppointmentsFile());
//        }
//        if (Objects.equals(set.getRepositoryType(), "text")) {
//            arbf = new AppointmentRepositoryTextFile(set.getAppointmentsFile());
//            prbf = new PatientRepositoryTextFile(set.getPatientsFile());
//        }
//        if (Objects.equals(set.getRepositoryType(), "database"))
//        {
//            prepo = new PatientsDBRepository("Patients");
//            arepo = new AppointmentsDBRepository("Appointments");
//        }
//        FileService fserv = new FileService(arbf, prbf);
//        DBservice dbserv = new DBservice(arepo, prepo);
//        Service serv = new Service(patientRepo, appointmentRepo);
//
//        ui ui = new ui(set, serv, fserv, dbserv);
//
//        ui.run();
//    }
}
