package com.functions;

import Functions.src.com.system.hospitalmanagement.Doctor;
import Functions.src.com.system.hospitalmanagement.Patient;

public class Appointment {

    private Functions.src.com.system.hospitalmanagement.Patient patient;
    private Functions.src.com.system.hospitalmanagement.Doctor doctor;

    public Appointment(Functions.src.com.system.hospitalmanagement.Patient patient, Functions.src.com.system.hospitalmanagement.Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;

    }

    public Functions.src.com.system.hospitalmanagement.Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Functions.src.com.system.hospitalmanagement.Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Functions.src.com.system.hospitalmanagement.Doctor doctor) {
        this.doctor = doctor;
    }

    
}
    

