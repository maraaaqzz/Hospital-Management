package code.service;

import code.domain.Appointments;
import code.domain.Patient;
import code.repository.PatientRepository;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import code.repository.AppointmentRepo;

public class Service {
    private PatientRepository prepo;
    private AppointmentRepo arepo;
    public Service(PatientRepository prepo, AppointmentRepo arepo) {
        this.prepo = prepo;
        this.arepo = arepo;
    }


    public Iterable<Patient<Integer>> getAll() {
        return this.prepo.getALlItems();
    }
    public void addPatient(Patient<Integer> item) throws DuplicateItemException
    {
        prepo.addItem(item.getId(), item);
    }

    public void updatePatient(Integer id, Patient<Integer> newItem) throws ItemNotFound
    {
        prepo.updateItem(id, newItem);
    }
    public void deletePatient(Integer id) throws ItemNotFound{
        prepo.removeItem(id);
    }

    public Patient<Integer> getPatient(Integer id) throws ItemNotFound
    {
        return prepo.findItem(id);
    }
    public Iterable<Patient<Integer>> getAllPatients() { return prepo.getALlItems();}

    public Iterable<Appointments<Integer>> getAllAppointments() {
        return this.arepo.getALlItems();
    }
    public void addAppointment(Appointments<Integer> item) throws DuplicateItemException {
        arepo.addItem(item.getId(), item);
    }
    public void addPatientAppointment(Integer id, Integer patId) {
        arepo.addPatientAppointment(id, patId);
    }
    public Appointments<Integer> getAppointment(Integer id) throws ItemNotFound
    {
        return arepo.findItem(id);
    }
    public void deleteAppointment(Integer id) throws ItemNotFound{
        arepo.removeItem(id);
    }
    public void updateAppointment(Integer id, Appointments<Integer> newItem) throws ItemNotFound
    {
        arepo.updateItem(id, newItem);
    }
}
