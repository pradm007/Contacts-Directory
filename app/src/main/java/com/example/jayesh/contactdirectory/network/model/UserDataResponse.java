package com.example.jayesh.contactdirectory.network.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by root on 12/11/16.
 */

public class UserDataResponse {
    @SerializedName("id")
    private Integer id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("managerId")
    private String managerId;

    @SerializedName("managerName")
    private String managerName;

    @SerializedName("title")
    private String title;

    @SerializedName("department")
    private String department;

    @SerializedName("business_unit")
    private String business_unit;

    @SerializedName("band")
    private String band;

    @SerializedName("grade")
    private String grade;

    @SerializedName("employee_code")
    private String employee_code;

    @SerializedName("employee_type")
    private String employee_type;

    @SerializedName("office")
    private String office;

    @SerializedName("cellPhone")
    private String cellPhone;

    @SerializedName("officePhone")
    private String officePhone;

    @SerializedName("date_confirmation")
    private String date_confirmation;

    @SerializedName("email")
    private String email;

    @SerializedName("dob")
    private String dob;

    @SerializedName("doj")
    private String doj;

    @SerializedName("pic25")
    private String pic25;

    @SerializedName("pic48")
    private String pic48;

    @SerializedName("pic320")
    private String pic320;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public String getBusiness_unit() {
        return business_unit;
    }

    public String getBand() {
        return band;
    }

    public String getGrade() {
        return grade;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public String getOffice() {
        return office;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public String getDate_confirmation() {
        return date_confirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getDoj() {
        return doj;
    }

    public String getPic25() {
        return pic25;
    }

    public String getPic48() {
        return pic48;
    }

    public String getPic320() {
        return pic320;
    }
}
