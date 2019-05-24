//
//  Patient.swift
//  InViKo
//
//  Created by Student on 2019-03-28.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit
class Patient {
    var id: Int
    var fName: String
    var lName: String
    var email: String
    var age: Int
    var address: String
    var gender: String
    var phone: String
    
    init() {
        id = 0
        fName = ""
        lName = ""
        email = ""
        age = 0
        address = ""
        gender = ""
        phone = ""
    }
    
    init(_ id: Int, _ fName: String, _ lName: String, _ email: String, _ age: Int, _ address: String, _ gender: String, _ phone: String) {
        self.id = id
        self.fName = fName
        self.lName = lName
        self.email = email
        self.age = age
        self.address = address
        self.gender = gender
        self.phone = phone
    }
}
