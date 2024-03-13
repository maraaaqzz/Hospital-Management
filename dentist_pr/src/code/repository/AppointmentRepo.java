package code.repository;
import code.domain.Appointments;

import code.domain.*;

public class AppointmentRepo extends MemoryRepo<Integer, Appointments<Integer>>{
        public void addPatientAppointment(Integer id, Integer patientId) { items.get(id).addPatient(patientId);}
}
