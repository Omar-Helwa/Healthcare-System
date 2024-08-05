package Functions.src.com.system.hospitalmanagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagement {
    

    public static void main(String[] args) {

        Doctor.addDoctor("Dr. Smith", "123 com.company.Main St", "555-1234", "Male", "Cardiology");
        Doctor.addDoctor("Dr. Johnson", "456 Elm St", "555-5678", "Female", "Neurology");

        Doctor.displayAllDoctors();

        
        // Delete a patient
        Patient.deletePatient(1);

        // Display all patients after deletion
        Patient.displayPatients();

    }


    protected static List<Appointment> appointments = new ArrayList<>();

    public void makeAppointment(int patientId, int doctorId) {
        Patient patient = Patient.patients.stream().filter(p -> p.id == patientId).findFirst().orElse(null);
        Doctor doctor = Doctor.doctors.stream().filter(d -> d.id == doctorId).findFirst().orElse(null);
        if (patient != null && doctor != null) {
            appointments.add(new Appointment(patient, doctor));
            System.out.println("Appointment made for patient " + patient.name + " with doctor " + doctor.name);
        } else {
            System.out.println("Invalid patient or doctor ID");
        }
    }

    public void displayAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println("Appointment: Patient " + appointment.getPatient().name + " with Doctor " + appointment.getDoctor().name);
        }
    }


    

}
