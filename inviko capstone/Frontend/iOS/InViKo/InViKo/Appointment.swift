//
//  Appointment.swift
//  InViKo
//
//  Created by Student on 2019-03-27.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class Appointment {
    var patient: Patient
    var doctor: Doctor
    var time: String
    var date: String
    
    init() {
        patient = Patient()
        doctor = Doctor()
        time = ""
        date = ""
    }
    
    init(_ time: String, _ date: String, _ patient: Patient, _ doctor: Doctor) {
        self.patient = patient
        self.doctor = doctor
        self.time = time
        self.date = date
    }
}
