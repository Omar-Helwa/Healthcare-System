package com.functions;

public class EmergencyPatient extends Patient {


    public EmergencyPatient(String name, String address, String phoneNumber, String gender, String symptoms, String paymentMethod, String diagnosis, int roomNumber) {
        super(name, address, phoneNumber, gender, symptoms, paymentMethod, diagnosis, roomNumber);
        setEmergency(true);
    }
}
