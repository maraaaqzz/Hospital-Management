package code.UI;

import code.domain.Appointments;
import code.domain.Patient;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;
import code.service.DBservice;
import code.service.FileService;
import code.service.Service;
import code.settings.Settings;

import code.settings.*;

import java.util.Objects;
import java.util.Scanner;

public class ui {

    private final Service serv;
    private final FileService fserv;
    private final DBservice dbserv;
    private Settings settings = new Settings();

    public ui(Settings settings, Service serv, FileService fserv, DBservice dbserv) {
        this.settings = settings;
        this.serv = serv;
        this.fserv = fserv;
        this.dbserv = dbserv;
    }

    public void printMemoryMenu()
    {
        System.out.println("0 : Exit");
        System.out.println("1 : Patients Options: ");
        System.out.println("2 : Appointments Options: ");
    }

    public void printPatientsMenu(){
        System.out.println("0 - Exit");
        System.out.println("1 - See all patient: ");
        System.out.println("2 - See patient: ");
        System.out.println("3 - Add patient: ");
        System.out.println("4 - Update patient: ");
        System.out.println("5 - Remove patient: ");
    }
    public void printAppointmentsMenu(){
        System.out.println("0 - Exit");
        System.out.println("1 - See all appointments: ");
        System.out.println("2 - See appointment: ");
        System.out.println("3 - Add appointment: ");
        System.out.println("4 - Add patient to appointment: ");
        System.out.println("5 - Update appointment: ");
        System.out.println("6 - Cancel appointment: ");
    }
    public void runMemoryMenu() throws DuplicateItemException, ItemNotFound {
        printMemoryMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option) {
            case 0:
                return;
            case 1:
                runPatientsMenu();
                break;
            case 2:
                runAppoinmentsMenu();
                break;
        }
    }

    public void runPatientsMenu() throws ItemNotFound, DuplicateItemException{
        printPatientsMenu();
        printMemoryMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option) {
            case 0:
                return;
            case 1: {
                try {
                    System.out.println(serv.getAllPatients());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Enter the id of the patient you want to see: ");
                Integer id = scan.nextInt();
                try {
                    System.out.println(serv.getPatient(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3: {
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String name = scan.next();
                System.out.println("Input patient`s illness: ");
                String illness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer age = scan.nextInt();
                try {
                    serv.addPatient(new Patient<Integer>(id, name, illness, age));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4: {
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String newname = scan.next();
                System.out.println("Input patient`s illness: ");
                String newillness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer newage = scan.nextInt();
                Patient<Integer> p = new Patient<>(id, newname, newillness, newage);
                try {
                    serv.updatePatient(id, p);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 5: {
                System.out.println("Enter the id of the patient you want to see: ");
                Integer id = scan.nextInt();
                try {
                    serv.deletePatient(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
        }
    }
    public void runAppoinmentsMenu() throws ItemNotFound, DuplicateItemException{
        printAppointmentsMenu();
        printMemoryMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option)
        {
            case 0:
                return;
            case 1: {
                try {
                    System.out.println(serv.getAllAppointments());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Enter the id of the appointment you want to see: ");
                Integer id = scan.nextInt();
                try {
                    System.out.println(serv.getAppointment(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3: {
                System.out.println("Input appointment id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s id: ");
                Integer patid = scan.nextInt();
                System.out.println("Input date of the appointment: ");
                String date = scan.next();
                try {
                    serv.addAppointment(new Appointments<Integer>(id, patid, date));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4: {
                System.out.println("Enter the id of the appointment: ");
                Integer id = scan.nextInt();
                System.out.println("Desired patient to add: ");
                Integer patientId = scan.nextInt();
                serv.addPatientAppointment(id, patientId);
            }
            break;
            case 5: {
                System.out.println("Input the appointment`s id: ");
                Integer id = scan.nextInt();
                System.out.println("Desired patient`s id: ");
                Integer patientid = scan.nextInt();
                System.out.println("Input appointment`s date: ");
                String newdate = scan.next();

                Appointments<Integer> a = new Appointments<>(id, patientid, newdate);
                try {
                    serv.updateAppointment(id, a);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 6: {
                System.out.println("Enter the id of the appointment you want to cancel: ");
                Integer id = scan.nextInt();
                try {
                    serv.deleteAppointment(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;

        }
    }

    public void printFileMenu(){
        System.out.println("0 : Exit");
        System.out.println("1 : Appointments Options: ");
        System.out.println("2 : Patients Options: ");
        System.out.println("3 : Settings: ");
    }

    public void runFileMenu() throws ItemNotFound {
        printFileMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option){
            case 0:
                return;
            case 1:
                runAppointmentFile();
                break;
            case 2:
                runPatientFile();
                break;
            case 3: {
                System.out.println("Current Repository Type: " + settings.getRepositoryType());
                System.out.println("Current Patient File: " + settings.getPatientsFile());
                System.out.println("Current Appointments Type: " + settings.getAppointmentsFile());

                System.out.println("Do you want to change the file type?");
                Scanner scan2 = new Scanner(System.in);
                String c = scan.next();

                if (Objects.equals(c, "yes")) {
                    if (Objects.equals(settings.getRepositoryType(), "text")) {
                        settings.updateRepositoryType("binary");
                        settings.updatePatientsFile("dentist_pr/patients.bin");
                        settings.updateAppointmentsFile("dentist_pr/appointments.bin");
                    } else if (Objects.equals(settings.getRepositoryType(), "binary")) {
                        settings.updateRepositoryType("text");
                        settings.updatePatientsFile("dentist_pr/patients.txt");
                        settings.updateAppointmentsFile("dentist_pr/appointments.txt");
                    }
                } else
                    return;
                System.out.println("\nUpdated Repository Type: " + settings.getRepositoryType());
                System.out.println("Updated Patients File: " + settings.getPatientsFile());
                System.out.println("Updated Appointments File: " + settings.getAppointmentsFile());
            }
            break;
        }
    }

    public void runPatientFile() throws ItemNotFound {
        printPatientsMenu();
        System.out.print("Please input your option: ");
        Scanner scan = new Scanner(System.in);
        int command = scan.nextInt();
        switch (command)
        {
            case 0:
                return;
            case 1:{
                System.out.println(fserv.getAllPatients());
            }
            break;
            case 2:{
                System.out.println("Enter patient id:");
                Integer id = scan.nextInt();
                System.out.println(fserv.getPatient(id));
            }
            break;
            case 3:{
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String pName = scan.next();
                System.out.println("Input patient`s illness: ");
                String illness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer age = scan.nextInt();
                try{
                    fserv.addPatient(id, new Patient<Integer>(id, pName, illness, age));
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4:{
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String pName = scan.next();
                System.out.println("Input patient`s illness: ");
                String illness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer age = scan.nextInt();

                Patient<Integer> o = new Patient<>(id, pName, illness, age);
                try{
                    fserv.updatePatient(id, o);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 5:{
                System.out.println("Enter the id of the patient: ");
                Integer id = scan.nextInt();
                try {
                    fserv.removePatient(id);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public void runAppointmentFile(){
        printAppointmentsMenu();
        System.out.print("Please input your option: ");
        Scanner scan = new Scanner(System.in);
        int command = scan.nextInt();
        switch (command)
        {
            case 0:
                return;
            case 1: {
                try {
                    System.out.println(fserv.getAllAppointments());
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2:{
                System.out.println("Enter the id of the appointment: ");
                Integer id = scan.nextInt();
                try {
                    System.out.println(fserv.getAppointment(id));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3:{
                System.out.println("Input appointment id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s id: ");
                Integer patId = scan.nextInt();
                System.out.println("Input appointment`s date: ");
                String date = scan.next();

                try {
                    fserv.addAppointment(id, new Appointments<>(id, patId, date));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 5:{
                System.out.println("Enter the id of the appointment: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s id: ");
                Integer patId = scan.nextInt();
                System.out.println("Input appointment`s date: ");
                String date = scan.next();

                Appointments<Integer> o = new Appointments<>(id, patId, date);
                try {
                    fserv.updateAppointment(id, o);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 6:{
                System.out.println("Enter the id of the appointment: ");
                Integer id = scan.nextInt();
                try {
                    fserv.removeAppointment(id);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
        }

    }
    public void printDBMenu()
    {
        System.out.println("0 : Exit");
        System.out.println("1 : Patients Options: ");
        System.out.println("2 : Appointments Options: ");
    }

    public void printPatientsDBMenu(){
        System.out.println("0 - Exit");
        System.out.println("1 - See all patient: ");
        System.out.println("2 - See patient: ");
        System.out.println("3 - Add patient: ");
        System.out.println("4 - Update patient: ");
        System.out.println("5 - Remove patient: ");
        System.out.println("  ----Reports----");
        System.out.println("6 - See the age of a patient");
        System.out.println("7 - See all the patients with the same illness");

    }
    public void printAppointmentsDBMenu(){
        System.out.println("0 - Exit");
        System.out.println("1 - See all appointments: ");
        System.out.println("2 - See appointment: ");
        System.out.println("3 - Add appointment: ");
        System.out.println("4 - Add patient to appointment: ");
        System.out.println("5 - Update appointment: ");
        System.out.println("6 - Cancel appointment: ");
        System.out.println("  ----Reports----");
        System.out.println("7 - See all the appointments of a patient");
        System.out.println("8 - See the patient booked for a certain appointment");
        System.out.println("9 - See all the appointments on a certain date");
        System.out.println("10 - See all the appointments for the patients with the same illness");
    }
    public void runDBMenu() throws DuplicateItemException, ItemNotFound {
        printMemoryMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option) {
            case 0:
                return;
            case 1:
                runPatientsDBMenu();
                break;
            case 2:
                runAppoinmentsDBMenu();
                break;
        }
    }
    public void runPatientsDBMenu() throws ItemNotFound {
        printPatientsDBMenu();
        System.out.print("Please input your option: ");
        Scanner scan = new Scanner(System.in);
        int command = scan.nextInt();
        switch (command){
            case 0:
                return;
            case 1: {
                try {
                    System.out.println(dbserv.getPatients());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Enter the id of the patient you want to see: ");
                Integer id = scan.nextInt();
                try {
                    System.out.println(dbserv.getPatient(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3: {
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String name = scan.next();
                System.out.println("Input patient`s illness: ");
                String illness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer age = scan.nextInt();
                try {
                    dbserv.addPatient(new Patient<Integer>(id, name, illness, age));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4: {
                System.out.println("Input patient id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s name: ");
                String newname = scan.next();
                System.out.println("Input patient`s illness: ");
                String newillness = scan.next();
                System.out.println("Input patient`s age: ");
                Integer newage = scan.nextInt();
                Patient<Integer> p = new Patient<>(id, newname, newillness, newage);
                try {
                    dbserv.updatePatient(id, p);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 5: {
                System.out.println("Enter the id of the patient you want to remove: ");
                Integer id = scan.nextInt();
                try {
                    dbserv.removePatient(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 6:
            {
                System.out.println("Enter the id of the patient whose age you want to see: ");
                Integer id = scan.nextInt();
                try{
                    System.out.println(dbserv.getAgeofPatient(id));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }
            break;
            case 7:
            {
                System.out.println("Input the illness: ");
                String illness = scan.next();
                try{
                    System.out.println(dbserv.getPatientsWithSameIllness(illness));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            break;
        }
    }
    public void runAppoinmentsDBMenu() throws ItemNotFound, DuplicateItemException{
        printAppointmentsDBMenu();
        System.out.print("Input your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option)
        {
            case 0:
                return;
            case 1: {
                try {
                    System.out.println(dbserv.getAppointments());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Enter the id of the appointment you want to see: ");
                Integer id = scan.nextInt();
                try {
                    System.out.println(dbserv.getAppointment(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3: {
                System.out.println("Input appointment id: ");
                Integer id = scan.nextInt();
                System.out.println("Input patient`s id: ");
                Integer patid = scan.nextInt();
                System.out.println("Input date of the appointment: ");
                String date = scan.next();
                try {
                    dbserv.addAppointment(patid, new Appointments<Integer>(id, patid, date));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4: {
                System.out.println("Desired patient to add: ");
                Integer patientId = scan.nextInt();
                Integer id = scan.nextInt();
                System.out.println("Input patient`s id: ");
                Integer patid = scan.nextInt();
                System.out.println("Input date of the appointment: ");
                String date = scan.next();
                dbserv.addAppointment(patientId,new Appointments<Integer>(id, patid, date) );
            }
            break;
            case 5: {
                System.out.println("Input the appointment`s id: ");
                Integer id = scan.nextInt();
                System.out.println("Desired patient`s id: ");
                Integer patientid = scan.nextInt();
                System.out.println("Input appointment`s date: ");
                String newdate = scan.next();

                Appointments<Integer> a = new Appointments<>(id, patientid, newdate);
                try {
                    dbserv.updateAppointment(id, a);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 6: {
                System.out.println("Enter the id of the appointment you want to cancel: ");
                Integer id = scan.nextInt();
                try {
                    dbserv.removeAppointment(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 7:
            {
                System.out.println("Enter the patient`s id whose appointments you want to see: ");
                Integer id = scan.nextInt();
                try{
                    System.out.println(dbserv.getAppointmentsofPatient(id));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 8:
            {
                System.out.println("Enter the appointment`s id to see the patient: ");
                int id = scan.nextInt();
                try{
                    System.out.println(dbserv.getBookedPersonsForAppointment(id));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 9:
            {
                System.out.println("Input the date you want to see all appointments: ");
                String date = scan.next();
                try{
                    System.out.println(dbserv.getAppointmentsOnDate(date));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 10:
            {
                System.out.println("Input the illness you want to see all appointments for: ");
                String illness = scan.next();
                try{
                    System.out.println(dbserv.getAppointmentsForPatientsWithIllness(illness));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void run() throws DuplicateItemException, ItemNotFound {
        while (true)
        {
            System.out.print("Options: \n");
            System.out.println(" 1   ---   Memory java.repository");
            System.out.println(" 2   ---   File java.repository");
            System.out.println(" 3   ---   DataBase java.repository");
            System.out.println(" 0   ---   Exit");
            System.out.print("Please input your option: ");
            Scanner scan = new Scanner(System.in);
            int command = scan.nextInt();
            switch (command)
            {
                case 0:
                    return;
                case 1:
                    runMemoryMenu();
                    break;
                case 2:
                    runFileMenu();
                    break;
                case 3:
                    runDBMenu();
                    break;
            }
        }
    }
}