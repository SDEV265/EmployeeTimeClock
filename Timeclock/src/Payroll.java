import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Payroll {
	
	StringProperty ClockNumber = new SimpleStringProperty();
	StringProperty Lastname = new SimpleStringProperty();
	StringProperty Firstname = new SimpleStringProperty();
	StringProperty StreetAddress = new SimpleStringProperty();
	StringProperty City = new SimpleStringProperty();
	StringProperty State = new SimpleStringProperty();
	StringProperty Zipcode = new SimpleStringProperty();
	StringProperty Dependants = new SimpleStringProperty();
	StringProperty PayRate = new SimpleStringProperty();
	StringProperty Hours = new SimpleStringProperty();
	StringProperty OTPayRate = new SimpleStringProperty();
	StringProperty OTHours = new SimpleStringProperty();
	StringProperty GrossPay = new SimpleStringProperty();
	StringProperty HealthIns = new SimpleStringProperty();
	StringProperty VisionIns = new SimpleStringProperty();
	StringProperty DentalIns = new SimpleStringProperty();
	StringProperty LifeIns = new SimpleStringProperty();
	StringProperty Total = new SimpleStringProperty();
	StringProperty CountyTax = new SimpleStringProperty();
	StringProperty StateTax = new SimpleStringProperty();
	StringProperty FederalTax = new SimpleStringProperty();
	StringProperty SocialSecurityTax= new SimpleStringProperty();
	StringProperty MedicareTax = new SimpleStringProperty();
	StringProperty NetPay = new SimpleStringProperty();
	
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
	public StringProperty HoursProperty() {
        return Hours;
    }
	public StringProperty OTPayRateProperty() {
        return OTPayRate;
    }
	public StringProperty OTHoursProperty() {
        return OTHours;
    }
	public StringProperty GrossPayProperty() {
        return GrossPay;
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
	public StringProperty TotalProperty() {
        return Total;
    }
	public StringProperty CountyTaxProperty() {
        return CountyTax;
    }
	public StringProperty StateTaxProperty() {
        return StateTax;
    }
	public StringProperty FederalTaxProperty() {
        return FederalTax;
    }
	public StringProperty SocialSecurityTaxProperty() {
        return SocialSecurityTax;
    }
	public StringProperty MedicareTaxProperty() {
        return MedicareTax;
    }
	public StringProperty NetPayProperty() {
        return NetPay;
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
    public void setHours(String value) {
    	Hours.set(value);
    }
    public void setOTPayRate(String value) {
    	OTPayRate.set(value);
    }
    public void setOTHours(String value) {
    	OTHours.set(value);
    }
    public void setGrossPay(String value) {
    	GrossPay.set(value);
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
    public void setTotal(String value) {
    	Total.set(value);
    }
    public void setCountyTax(String value) {
    	CountyTax.set(value);
    }
    public void setStateTax(String value) {
    	StateTax.set(value);
    }
    public void setFederalTax(String value) {
    	FederalTax.set(value);
    }
    public void setSocialSecurityTax(String value) {
    	SocialSecurityTax.set(value);
    }
    public void setMedicareTax(String value) {
    	MedicareTax.set(value);
    }
    public void setNetPay(String value) {
    	NetPay.set(value);
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
    public String getHours() {
        return Hours.get();
    }
    public String getOTPayRate() {
        return OTPayRate.get();
    }
    public String getOTHours() {
        return OTHours.get();
    }
    public String getGrossPay() {
        return GrossPay.get();
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
    public String getTotal() {
        return Total.get();
    }
    public String getCountyTax() {
        return CountyTax.get();
    }
    public String getStateTax() {
        return StateTax.get();
    }
    public String getFederalTax() {
        return FederalTax.get();
    }
    public String getSocialSecurityTax() {
        return SocialSecurityTax.get();
    }
    public String getMedicareTax() {
        return MedicareTax.get();
    }
    public String getNetPay() {
        return NetPay.get();
    }
    
    public Payroll(String ClockNumberValue, String LastnameValue, String FirstnameValue, String StreetAddressValue, String CityValue, String StateValue, String ZipcodeValue, String PayRateValue, String HoursValue,
    		String OTPayRateValue, String GrossPayValue, String OTHoursValue, String HealthInsValue, String VisionInsValue, String DentalInsValue, String LifeInsValue , String TotalValue, String CountyTaxValue, String StateTaxValue, String FederalTaxValue, 
    		String SocialSecurityTaxValue, String MedicareTaxValue, String NetPayValue) {
    	ClockNumber.set(ClockNumberValue);
    	Lastname.set(LastnameValue);
    	Firstname.set(FirstnameValue);
    	StreetAddress.set(StreetAddressValue);
    	City.set(CityValue);
    	State.set(StateValue);
    	Zipcode.set(ZipcodeValue);
    	PayRate.set(PayRateValue);
    	Hours.set(HoursValue);
    	OTPayRate.set(OTPayRateValue);
    	OTHours.set(OTHoursValue);
    	GrossPay.set(GrossPayValue);
    	HealthIns.set(HealthInsValue);
    	VisionIns.set(VisionInsValue);
    	DentalIns.set(DentalInsValue);
    	LifeIns.set(LifeInsValue);
    	Total.set(TotalValue);
    	CountyTax.set(CountyTaxValue);
    	StateTax.set(StateTaxValue);
    	FederalTax.set(FederalTaxValue);
    	SocialSecurityTax.set(SocialSecurityTaxValue);
    	MedicareTax.set(MedicareTaxValue);
    	NetPay.set(NetPayValue);
    }
    
    Payroll(){}
    

}
