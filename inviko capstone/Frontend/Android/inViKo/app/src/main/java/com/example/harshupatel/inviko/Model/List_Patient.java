package com.example.harshupatel.inviko.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_Patient {



        @SerializedName("patient_firstname")
        @Expose
        private String patientFirstname;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("patient_lastname")
        @Expose
        private String patientLastname;
        @SerializedName("patient_id")
        @Expose
        public int patientId;

        public String getPatientFirstname() {
            return patientFirstname;
        }

        public void setPatientFirstname(String patientFirstname) {
            this.patientFirstname = patientFirstname;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getPatientLastname() {
            return patientLastname;
        }

        public void setPatientLastname(String patientLastname) {
            this.patientLastname = patientLastname;
        }
        public int getPatientId() {
            return patientId;
        }

        public void setPatientId(int patientId) {
            this.patientId = patientId;
        }

}
