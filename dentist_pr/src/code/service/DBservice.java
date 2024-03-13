package code.service;

import code.domain.Appointments;
import code.domain.Patient;

import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import code.repository.DataBaseRepository;

import java.util.stream.Collectors;

import java.util.List;

public class DBservice {
    private DataBaseRepository<Integer, Appointments<Integer>> arepo;
    private DataBaseRepository<Integer, Patient<Integer>> prepo;

    public DBservice(DataBaseRepository<Integer, Appointments<Integer>> arepo, DataBaseRepository<Integer, Patient<Integer>> prepo) {
        this.arepo = arepo;
        this.prepo = prepo;
    }
    public Appointments<Integer> getAppointment(Integer integer) throws ItemNotFound {
        return arepo.getItem(integer);
    }

    public Iterable<Appointments<Integer>> getAppointments() {
        return arepo.getAll();
    }

    public Patient<Integer> getPatient(Integer integer) throws ItemNotFound {
        return prepo.getItem(integer);
    }

    public Iterable<Patient<Integer>> getPatients() {

        return prepo.getAll();
    }

    public void addAppointment(Integer integer, Appointments<Integer> item) throws DuplicateItemException {
        arepo.add(item);
    }

    public void addPatient(Patient<Integer> item) throws DuplicateItemException {
        prepo.add(item);
    }

    public void removePatient(Integer integer) throws ItemNotFound {
        prepo.remove(integer);
    }
    public void removeAppointment(Integer integer) throws ItemNotFound {
        arepo.remove(integer);
    }

    public void updatePatient(Integer integer, Patient<Integer> newItem) throws ItemNotFound {
        prepo.update(integer, newItem);
    }

    public void updateAppointment(Integer integer, Appointments<Integer> newItem) throws ItemNotFound {
        arepo.update(integer, newItem);
    }

    // ------------------------------ reports

    //------------ all the appointments of a person
    public List<Appointments<Integer>> getAppointmentsofPatient(Integer patientId)
    {
        List<Appointments<Integer>> all= (List<Appointments<Integer>>) arepo.getAll();
        return all.stream()
                .filter(appoinment -> appoinment.getPatientID().equals(patientId))
                .collect(Collectors.toList());
    }

    // ---------- patients with the same illness
    public List<Patient<Integer>> getPatientsWithSameIllness(String illness)
    {
        List<Patient<Integer>> all= (List<Patient<Integer>>) prepo.getAll();
        return all.stream()
                .filter(patient -> patient.getIllness().equals(illness))
                .collect(Collectors.toList());
    }

    // ---------
    public int getAgeofPatient(Integer patientID)
    {
        List<Patient<Integer>> all= (List<Patient<Integer>>) prepo.getAll();
        return all.stream()
                .filter(patient -> patient.getId().equals(patientID))
                .map(Patient::getAge)
                .findAny() // if patient not found
                .orElseThrow((() -> new RuntimeException("Patient not found with ID: " + patientID)));
    }

    // ----- patient booked for an appointment
    public List<String> getBookedPersonsForAppointment(int appointmentId) {
        List<Appointments<Integer>> all= (List<Appointments<Integer>>) arepo.getAll();
        List<Patient<Integer>> patients= (List<Patient<Integer>>) prepo.getAll();

        return all.stream()
                .filter(appointment -> appointment.getId().equals(appointmentId))
                .map(appointment -> patients.stream()
                        .filter(patient -> patient.getId().equals(appointment.getPatientID()))
                        .map(Patient::getName)
                        .findFirst()
                        .orElse(null)
                )
                .collect(Collectors.toList());
    }

    // ---- all the appointments for the patients with the same illness
    public List<Appointments<Integer>> getAppointmentsForPatientsWithIllness(String illness) {
        List<Appointments<Integer>> allAppointments = (List<Appointments<Integer>>) arepo.getAll();
        List<Patient<Integer>> allPatients = (List<Patient<Integer>>) prepo.getAll();

        List<Integer> patientsWithIllnessIds = allPatients.stream()
                .filter(patient -> patient.getIllness().equalsIgnoreCase(illness))
                .map(Patient::getId)
                .collect(Collectors.toList());

        return allAppointments.stream()
                .filter(appointment -> patientsWithIllnessIds.stream().anyMatch(id -> id.equals(appointment.getPatientID())))
                .collect(Collectors.toList());
    }

    //------ all the appointments on a date
    public List<Appointments<Integer>> getAppointmentsOnDate(String date) {
        List<Appointments<Integer>> all_appointments= (List<Appointments<Integer>>) arepo.getAll();
        List<Patient<Integer>> all_patients= (List<Patient<Integer>>) prepo.getAll();

        return all_appointments.stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
