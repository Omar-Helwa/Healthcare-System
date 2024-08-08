package com.company;

import com.functions.Doctor;

public class Main {

    public static void main(String[] args) {



        Doctor doctor = new Doctor("Omar","Madinaty","01062198421", "Pediatrics");

        Doctor doctor1 = new Doctor("Ahmed","Madinaty","01065198421", "Pediatrics");

        Doctor doctor2 = new Doctor("Bima","Madinaty","01062178421", "Pediatrics");

        Doctor doctor3 = new Doctor("Marwan","Madinaty","01062192421", "Pediatrics");

        Doctor doctor4 = new Doctor("Salma","Madinaty","01062598421", "Pediatrics");

        Doctor doctor5 = new Doctor("Nada","Madinaty","01012198421", "Pediatrics");

        Doctor doctor6 = new Doctor("Helwa","Madinaty","01082198421", "Pediatrics");

        Doctor doctor7 = new Doctor("Nonn","Madinaty","01062197421", "Pediatrics");

        Doctor.save();

        for (Doctor i : Doctor.getDoctors()) {
            System.out.println(i.toString());
        }





//        doctor6.save();

//        EmergencyPatient patient = new EmergencyPatient("Bima","Tagamo3","01076523658", "female","ta3ban", "special way", "private",23);

//        patient.add(patient);
//        patient.delete(patient);


//        HospitalManagement hospitalManagement = new HospitalManagement();
//        hospitalManagement.displayDoctors();
//        hospitalManagement.displayPatients();


    }


}
