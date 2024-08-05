package com.functions;

public class HospitalManagement {
    public static void displayAllDoctors() {
        for (Doctor doctor : Doctor.getAllDoctors()) {
            System.out.println("ID: " + doctor.id + ", Name: " + doctor.name + ", Specialty: " + doctor.getSpecialty());
        }
    }

    public static void main(String[] args) {

        Doctor.addDoctor("Dr. Smith", "123 com.company.Main St", "555-1234", "Male", "Cardiology");
        Doctor.addDoctor("Dr. Johnson", "456 Elm St", "555-5678", "Female", "Neurology");

        displayAllDoctors();
    }
}
