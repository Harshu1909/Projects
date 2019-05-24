//
//  Doctor.swift
//  InViKo
//
//  Created by Student on 2019-04-10.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit
class Doctor {
    var id: Int
    var firstName: String
    var lastName: String
    
    init() {
        id = 0
        firstName = ""
        lastName = ""
    }
    
    init(_ id: Int, _ firstName: String, _ lastName: String) {
        self.id = id
        self.firstName = firstName
        self.lastName = lastName
    }
}
