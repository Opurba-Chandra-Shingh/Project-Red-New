package com.example.projectredpulsenew;

public class PostDetails {
    private String userName;      // ✅ Changed from UserName to userName (lowercase)
    private String patientName;
    private String bloodGroup;
    private String units;
    private String location;
    private String dateNeeded;
    private String phone;
    private String notes;

    // Default constructor for Gson
    public PostDetails() {}

    // Constructor with all fields
    public PostDetails(String userName, String patientName, String bloodGroup,
                       String units, String location, String dateNeeded,
                       String phone, String notes) {
        this.userName = userName;
        this.patientName = patientName;
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.location = location;
        this.dateNeeded = dateNeeded;
        this.phone = phone;
        this.notes = notes;
    }

    // Getters with null safety
    public String getUserName() {
        return userName != null && !userName.isEmpty() ? userName : "Anonymous";
    }

    public String getPatientName() {
        return patientName != null && !patientName.isEmpty() ? patientName : "N/A";
    }

    public String getBloodGroup() {
        return bloodGroup != null && !bloodGroup.isEmpty() ? bloodGroup : "N/A";
    }

    public String getUnits() {
        return units != null && !units.isEmpty() ? units : "N/A";
    }

    public String getLocation() {
        return location != null && !location.isEmpty() ? location : "N/A";
    }

    public String getDateNeeded() {
        return dateNeeded != null && !dateNeeded.isEmpty() ? dateNeeded : "N/A";
    }

    public String getPhone() {
        return phone != null && !phone.isEmpty() ? phone : "N/A";
    }

    public String getNotes() {
        return notes != null && !notes.isEmpty() ? notes : "No additional notes";
    }

    // Setters (useful for future modifications)
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateNeeded(String dateNeeded) {
        this.dateNeeded = dateNeeded;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "PostDetails{" +
                "userName='" + userName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}