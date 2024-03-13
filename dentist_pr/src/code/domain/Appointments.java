package code.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Appointments<Integer> extends Identifiable<Integer> implements Serializable {

    protected Integer patientID;
    private String date;


    public Appointments(Integer id, Integer patientI, String date) {
        super(id);
        this.patientID = patientI;
        this.date = date;
    }

    public Integer getPatientID() {return patientID;}
//    @Override
//    public Integer getId() {
//        return id;
//    }
    public void setPatientID(List<Integer> patientsIDs){ this.patientID = patientID;}
    public void addPatient(Integer patientId){ this.patientID = patientId;}

    public void setPatientsIDs(List<Integer> patientsIDs) {
        this.patientID = patientID;
    }
    public String getDate(){ return date;}
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Appointment : " +
                "id =" + id +
                ", Patient`s id =" + patientID +
                ", Date =" + date +
                '}';
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if(!(o instanceof Appointments<?> app)) return false;
        if (!super.equals(o)) return false;
        return  Objects.equals(patientID, app.patientID) && Objects.equals(date, app.date);
    }
}
