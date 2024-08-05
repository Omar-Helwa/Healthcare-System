package com.functions;

import Functions.src.com.system.hospitalmanagement.Patient;

public class NormalPatient extends Patient {
    public NormalPatient(int id, String name, String address, String phoneNumber, String gender, String symptoms, String paymentMethod, String diagnosis, boolean insuranceInfo) {
        super(id, name, address, phoneNumber, gender, symptoms, paymentMethod, diagnosis, insuranceInfo);
    }
    
}
