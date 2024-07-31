package com.company;

public class Patient extends Person {

    private final String type = "patients";
    private String gender;
    private String symptoms;
    private String paymentMethod;
    private String diagnosis;
    private boolean emergency;
    private int roomNumber;

    public Patient(String name, String phoneNumber, String address, String gender, String symptoms, String paymentMethod, boolean emergency, int roomNumber) {
        super(name, phoneNumber, address);
        this.gender = gender;
        this.symptoms = symptoms;
        this.paymentMethod = paymentMethod;
        this.diagnosis = diagnosis;
        this.emergency = emergency;
        this.roomNumber = roomNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }
}
