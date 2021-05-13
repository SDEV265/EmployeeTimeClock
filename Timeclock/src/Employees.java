import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employees {
	
	StringProperty ClockNumber = new SimpleStringProperty();
	StringProperty Lastname = new SimpleStringProperty();
	StringProperty Firstname = new SimpleStringProperty();
	StringProperty Inactive = new SimpleStringProperty();
	StringProperty StreetAddress = new SimpleStringProperty();
	StringProperty City = new SimpleStringProperty();
	StringProperty State = new SimpleStringProperty();
	StringProperty Zipcode = new SimpleStringProperty();
	StringProperty Dependants = new SimpleStringProperty();
	StringProperty PayRate = new SimpleStringProperty();
	StringProperty HireDate = new SimpleStringProperty();
	StringProperty HealthIns = new SimpleStringProperty();
	StringProperty VisionIns = new SimpleStringProperty();
	StringProperty DentalIns = new SimpleStringProperty();
	StringProperty LifeIns = new SimpleStringProperty();
	StringProperty CountyCode = new SimpleStringProperty();
	
	//
	public StringProperty ClockNumberProperty() {
        return ClockNumber;
    }
	public StringProperty LastnameProperty() {
        return Lastname;
    }
	public StringProperty FirstnameProperty() {
        return Firstname;
    }
	public StringProperty InactiveProperty() {
        return Inactive;
    }
	public StringProperty StreetAddressProperty() {
        return StreetAddress;
    }
	public StringProperty CityProperty() {
        return City;
    }
	public StringProperty StateProperty() {
        return State;
    }
	public StringProperty ZipcodeProperty() {
        return Zipcode;
    }
	public StringProperty DependantsProperty() {
        return Dependants;
    }
	public StringProperty PayRateProperty() {
        return PayRate;
    }
	public StringProperty HireDateProperty() {
        return HireDate;
    }
	public StringProperty HealthInsProperty() {
        return HealthIns;
    }
	public StringProperty VisionInsProperty() {
        return VisionIns;
    }
	public StringProperty DentalInsProperty() {
        return DentalIns;
    }
	public StringProperty LifeInsProperty() {
        return LifeIns;
    }
	public StringProperty CountyCodeProperty() {
        return CountyCode;
    }
	
	//Users setters
	public void setClockNumber(String value) {
    	ClockNumber.set(value);
    }
    public void setLastname(String value) {
    	Lastname.set(value);
    }
    public void setFirstname(String value) {
    	Firstname.set(value);
    }
    public void setInactive(String value) {
    	Inactive.set(value);
    }
    public void setStreetAddress(String value) {
    	StreetAddress.set(value);
    }
    public void setCity(String value) {
    	City.set(value);
    }
    public void setState(String value) {
    	State.set(value);
    }
    public void setZipcode(String value) {
    	Zipcode.set(value);
    }
    public void setDependants(String value) {
    	Dependants.set(value);
    }
    public void setPayRate(String value) {
    	PayRate.set(value);
    }
    public void setHireDate(String value) {
    	HireDate.set(value);
    }
    public void setHealthIns(String value) {
    	HealthIns.set(value);
    }
    public void setVisionIns(String value) {
    	VisionIns.set(value);
    }
    public void setDentalIns(String value) {
    	DentalIns.set(value);
    }
    public void setLifeIns(String value) {
    	LifeIns.set(value);
    }
    public void setCountyCode(String value) {
    	CountyCode.set(value);
    }
    
    //Users getters
    public String getClockNumber() {
        return ClockNumber.get();
    }
    public String getLastname() {
        return Lastname.get();
    }
    public String getFirstname() {
        return Firstname.get();
    }
    public String getInactive() {
        return Inactive.get();
    }
    public String getStreetAddress() {
        return StreetAddress.get();
    }
    public String getCity() {
        return City.get();
    }
    public String getState() {
        return State.get();
    }
    public String getZipcode() {
        return Zipcode.get();
    }
    public String getDependants() {
        return Dependants.get();
    }
    public String getPayRate() {
        return PayRate.get();
    }
    public String getHireDate() {
        return HireDate.get();
    }
    public String getHealthIns() {
        return HealthIns.get();
    }
    public String getVisionIns() {
        return VisionIns.get();
    }
    public String getDentalIns() {
        return DentalIns.get();
    }
    public String getLifeIns() {
        return LifeIns.get();
    }
    public String getCountyCode() {
        return CountyCode.get();
    }
    
    public Employees(String ClockNumberValue, String LastnameValue, String FirstnameValue, String InactiveValue, String StreetAddressValue, String CityValue, String StateValue, String ZipcodeValue, String DependantsValue, String PayRateValue, 
    		String HireDateValue, String HealthInsValue, String VisionInsValue, String DentalInsValue, String LifeInsValue, String CountyCodeValue) {
    	ClockNumber.set(ClockNumberValue);
    	Lastname.set(LastnameValue);
    	Firstname.set(FirstnameValue);
    	Inactive.set(InactiveValue);
    	StreetAddress.set(StreetAddressValue);
    	City.set(CityValue);
    	State.set(StateValue);
    	Zipcode.set(ZipcodeValue);
    	Dependants.set(DependantsValue);
    	PayRate.set(PayRateValue);
    	HireDate.set(HireDateValue);
    	HealthIns.set(HealthInsValue);
    	VisionIns.set(VisionInsValue);
    	DentalIns.set(DentalInsValue);
    	LifeIns.set(LifeInsValue);
    	CountyCode.set(CountyCodeValue);
    }
    
    Employees(){}
    

}
