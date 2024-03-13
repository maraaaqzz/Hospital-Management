package code.service;

import code.domain.Appointments;
import code.domain.Patient;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import code.repository.FileRepository;

public class FileService {
    private FileRepository<Integer, Appointments<Integer>> appointmentsRepo;
    private FileRepository<Integer, Patient<Integer>> patientsRepo;
    public FileService(FileRepository<Integer, Appointments<Integer>> appointmentsRepo, FileRepository<Integer, Patient<Integer>> patientsRepo){
        this.appointmentsRepo = appointmentsRepo;
        this.patientsRepo = patientsRepo;
    }
    public void addPatient(Integer id, Patient<Integer> elem) throws DuplicateItemException{
        patientsRepo.addItem(id, elem);
    }
    public void removePatient(Integer id) throws ItemNotFound{
        patientsRepo.removeItem(id);
    }
    public void updatePatient(Integer id, Patient<Integer> newItem) throws ItemNotFound{
        patientsRepo.updateItem(id, newItem);
    }
    public Patient<Integer> getPatient(Integer id) throws ItemNotFound{
        return patientsRepo.findItem(id);
    }
    public Iterable<Patient<Integer>> getAllPatients(){return patientsRepo.getALlItems(); }

    public void addAppointment(Integer id, Appointments<Integer> elem) throws DuplicateItemException{
        appointmentsRepo.addItem(id, elem);
    }
    public void removeAppointment(Integer id) throws ItemNotFound{
        appointmentsRepo.removeItem(id);
    }
    public void updateAppointment(Integer id, Appointments<Integer> newItem) throws ItemNotFound{
        appointmentsRepo.updateItem(id, newItem);
    }
    public Appointments<Integer> getAppointment(Integer id) throws ItemNotFound{
        return appointmentsRepo.findItem(id);
    }
    public Iterable<Appointments<Integer>> getAllAppointments(){
        return appointmentsRepo.getALlItems();
    }
}
