package Functions.src;

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

    
}
