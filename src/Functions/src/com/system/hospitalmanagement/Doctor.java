package Functions.src.com.system.hospitalmanagement;

import java.util.ArrayList;
import java.util.List;

class Doctor extends Person {

    String specialty;
    protected static List<Doctor> doctors = new ArrayList<>();
    private static int nextId = 1;

    public Doctor(int id, String name, String address, String phoneNumber, String gender, String specialty) {
        super(id, name, address, phoneNumber, gender);
        this.specialty = specialty;
    }
    
    // Getter and setter for Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for address
    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    // Getter and setter for phoneNumber
    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

     // Getter and setter for gender
     public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
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
    public static void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.id == id);
    }

    public static void displayAllDoctors() {
        List<Doctor> doctors = Doctor.getAllDoctors();
    for (Doctor doctor : doctors) {
        System.out.println("ID: " + doctor.getId() + ", Name: " + doctor.getName() + ", Specialty: " + doctor.getSpecialty());
    }
    }

    public static List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors); // Returns a copy to avoid direct access to the private list
    }

    
}