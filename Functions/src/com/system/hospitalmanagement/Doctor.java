package com.system.hospitalmanagement;

import java.util.ArrayList;
import java.util.List;

class Doctor extends Person {

    String specialty;
    private static List<Doctor> doctors = new ArrayList<>();
    private static int nextId = 1;

    public Doctor(int id, String name, String address, String phoneNumber, String gender, String specialty) {
        super(id, name, address, phoneNumber, gender);
        this.specialty = specialty;
    }

    // Getter and setter for specialty

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // Method to add a new doctor to the array
    public static void addDoctor(String name, String address, String phoneNumber, String gender, String specialty) {
        doctors.add(new Doctor(nextId++, name, address, phoneNumber, gender, specialty));
    }

    // Method to edit doctor info
    public static void editDoctor(int id, String name, String address, String phoneNumber, String gender, String specialty) {
        for (Doctor doctor : doctors) {
            if (doctor.id == id) {
                doctor.name = name;
                doctor.address = address;
                doctor.phoneNumber = phoneNumber;
                doctor.gender = gender;
                doctor.specialty = specialty;
                return;
            }
        }
    }

    public static List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors); // Returns a copy to avoid direct access to the private list
    }
}