package com.example.harshupatel.inviko.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_Doctor {

    @SerializedName("doctor_id")
    @Expose
    private int doctorId;
    @SerializedName("doctor_firstname")
    @Expose
    private String doctorFirstname;
    @SerializedName("doctor_lastname")
    @Expose
    private String doctorLastname;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorFirstname() {
        return doctorFirstname;
    }

    public void setDoctorFirstname(String doctorFirstname) {
        this.doctorFirstname = doctorFirstname;
    }

    public String getDoctorLastname() {
        return doctorLastname;
    }

    public void setDoctorLastname(String doctorLastname) {
        this.doctorLastname = doctorLastname;
    }
}
