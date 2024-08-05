package Functions.src;

public class EmergencyPatient extends Patient {
    private int roomNumber;

    public EmergencyPatient(int id, String name, String address, String phoneNumber, String gender, 
    String symptoms, String paymentMethod, String diagnosis, boolean insuranceInfo) {
        super(id, name, address, phoneNumber, gender, symptoms, paymentMethod, diagnosis, insuranceInfo);
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}
