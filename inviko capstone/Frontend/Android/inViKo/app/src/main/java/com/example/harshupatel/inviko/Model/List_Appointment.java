package com.example.harshupatel.inviko.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_Appointment {

    @SerializedName("patient_lastname")
    @Expose
    private String patientLastname;
    @SerializedName("patient_firstname")
    @Expose
    private String patientFirstname;
    @SerializedName("doctor_firstname")
    @Expose
    private String doctorFirstname;
    @SerializedName("availablity_time")
    @Expose
    private String availablityTime;
    @SerializedName("patient_id")
    @Expose
    private int patientId;
    @SerializedName("availablity_date")
    @Expose
    private String availablityDate;
    @SerializedName("appointment_id")
    @Expose
    private int appointmentId;
    @SerializedName("doctor_id")
    @Expose
    private int doctorId;
    @SerializedName("doctor_lastname")
    @Expose
    private String doctorLastname;

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public String getPatientFirstname() {
        return patientFirstname;
    }

    public void setPatientFirstname(String patientFirstname) {
        this.patientFirstname = patientFirstname;
    }

    public String getDoctorFirstname() {
        return doctorFirstname;
    }

    public void setDoctorFirstname(String doctorFirstname) {
        this.doctorFirstname = doctorFirstname;
    }

    public String getAvailablityTime() {
        return availablityTime;
    }

    public void setAvailablityTime(String availablityTime) {
        this.availablityTime = availablityTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getAvailablityDate() {
        return availablityDate;
    }

    public void setAvailablityDate(String availablityDate) {
        this.availablityDate = availablityDate;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorLastname() {
        return doctorLastname;
    }

    public void setDoctorLastname(String doctorLastname) {
        this.doctorLastname = doctorLastname;
    }

}