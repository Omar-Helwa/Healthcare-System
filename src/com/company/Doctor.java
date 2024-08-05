package com.company;

public class Doctor extends Person{
    private String speciality;
    private final String type = "doctors";

    public Doctor(String name, String phoneNumber, String address, String speciality) {
        super(name, phoneNumber, address);
        this .speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getType() {
        return type;
    }
}
