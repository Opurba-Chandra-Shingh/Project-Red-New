package com.example.projectredpulsenew;

public class User {
    private String name;
    private String age;
    private String gender;
    private String bloodGroup;
    private String district;
    private String email;
    private String password;
    private String Number;


    public User() {
    }

    public User(String name, String age, String gender, String bloodGroup, String district, String Number, String email, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.district = district;
        this.Number = Number;
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public String getName() { return name; }
    //public void setName(String name) { this.name = name; }

    public String getAge() { return age; }
    //public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }
    //public void setGender(String gender) { this.gender = gender; }

    public String getBloodGroup() { return bloodGroup; }
    //public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getDistrict() { return district; }
    //public void setDistrict(String district) { this.district = district; }

    public String getNumber() { return Number; }
    //public void setNumber(String Number) { this.district = district; }

    public String getEmail() { return email; }
    //public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    //public void setPassword(String password) { this.password = password; }


}

