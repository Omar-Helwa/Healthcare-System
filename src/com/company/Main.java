package com.company;

import com.functions.Doctor;

public class Main {

    public static void main(String[] args) {
        Doctor doctor = new Doctor("Omar","Madinaty","01062198421", "Pediatrics");
        doctor.addD(doctor);
    }
}
