package Functions.src.com.system.hospitalmanagement;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    protected String symptoms;
    protected String paymentMethod;
    protected String diagnosis;
    protected boolean insuranceInfo;

    public Patient(int id, String name, String address, String phoneNumber, String gender, String symptoms, String paymentMethod, String diagnosis, boolean insuranceInfo) {
        super(id, name, address, phoneNumber, gender);
        
        this.symptoms = symptoms;
        this.paymentMethod = paymentMethod;
        this.diagnosis = diagnosis;
        this.insuranceInfo = insuranceInfo;
        
    }

    protected static List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void editPatient(int id, Patient updatedPatient) {
        for (Patient patient : patients) {
            if (patient.id == id) {
                patient.name = updatedPatient.name;
                patient.address = updatedPatient.address;
                patient.phoneNumber = updatedPatient.phoneNumber;
                patient.gender = updatedPatient.gender;
                patient.symptoms = updatedPatient.symptoms;
                patient.paymentMethod = updatedPatient.paymentMethod;
                patient.diagnosis = updatedPatient.diagnosis;
                if (patient instanceof EmergencyPatient) {
                    ((EmergencyPatient) patient).setRoomNumber(((EmergencyPatient) updatedPatient).getRoomNumber());
                }
                return;
            }
        }
    }


    public static void deletePatient(int id) {
        patients.removeIf(patient -> patient.id == id);
    }

    public static void displayPatients() {
        for (Patient patient : patients) {
            if (patient instanceof EmergencyPatient) {
                System.out.println("Emergency Patient - ID: " + patient.id + ", Name: " + patient.name);
            } else {
                System.out.println("Normal Patient - ID: " + patient.id + ", Name: " + patient.name);
            }
        }
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

    public boolean isInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(boolean insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    
}
