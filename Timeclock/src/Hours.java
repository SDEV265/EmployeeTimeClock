import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hours {
	
	StringProperty LogId = new SimpleStringProperty();
	StringProperty Datefield = new SimpleStringProperty();
	StringProperty ClockNumber = new SimpleStringProperty();
	StringProperty DailyIn = new SimpleStringProperty();
	StringProperty TimeOut = new SimpleStringProperty();
	StringProperty TimeIn = new SimpleStringProperty();
	StringProperty DailyOut = new SimpleStringProperty();
	StringProperty Notes = new SimpleStringProperty();
	StringProperty Approved = new SimpleStringProperty();
	StringProperty TotalHours = new SimpleStringProperty();
	
	public StringProperty LogIdProperty() {
        return LogId;
    }
	public StringProperty DatefieldProperty() {
        return Datefield;
    }
	public StringProperty ClockNumberProperty() {
        return ClockNumber;
    }
	public StringProperty DailyInProperty() {
        return DailyIn;
    }
	public StringProperty TimeOutProperty() {
        return TimeOut;
    }
	public StringProperty TimeInProperty() {
        return TimeIn;
    }
	public StringProperty DailyOutProperty() {
        return DailyOut;
    }
	public StringProperty NotesProperty() {
        return Notes;
    }
	public StringProperty ApprovedProperty() {
        return Approved;
    }
	public StringProperty TotalHoursProperty() {
        return TotalHours;
    }
	public void setLogId(String value) {
    	LogId.set(value);
    }
	public void setDatefield(String value) {
    	Datefield.set(value);
    }
	public void setClockNumber(String value) {
    	ClockNumber.set(value);
    }
	public void setDailyIn(String value) {
    	DailyIn.set(value);
    }
	public void setTimeOut(String value) {
    	TimeOut.set(value);
    }
	public void setTimeIn(String value) {
    	TimeIn.set(value);
    }
	public void setDailyOut(String value) {
    	DailyOut.set(value);
    }
	public void setNotes(String value) {
    	Notes.set(value);
    }
	public void setApproved(String value) {
    	Approved.set(value);
    }
	public void setTotalHours(String value) {
    	TotalHours.set(value);
    }
	public String getLogId() {
        return LogId.get();
    }
	public String getDatefield() {
        return Datefield.get();
    }
	public String getClockNumber() {
        return ClockNumber.get();
    }
	public String getDailyIn() {
        return DailyIn.get();
    }
	public String getTimeOut() {
        return TimeOut.get();
    }
    public String getTimeIn() {
        return TimeIn.get();
    }
    public String getDailyOut() {
        return DailyOut.get();
    }
    public String getNotes() {
        return Notes.get();
    }
    public String getApproved() {
        return Approved.get();
    }
    public String getTotalHours() {
        return TotalHours.get();
    }

}
