package com.functions;

import java.util.ArrayList;
import java.util.List;

public class HospitalManagement {

    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

//    public static void displayAllDoctors() {
//        for (Doctor doctor : Doctor.getAllDoctors()) {
//            System.out.println("ID: " + doctor.id + ", Name: " + doctor.name + ", Specialty: " + doctor.getSpecialty());
//        }
//    }

 public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public boolean patientExists(Patient patient) {
        return patients.contains(patient);
    }

    public boolean doctorExists(Doctor doctor) {
        return doctors.contains(doctor);
    }

    
    public static void main(String[] args) {



    }
}
