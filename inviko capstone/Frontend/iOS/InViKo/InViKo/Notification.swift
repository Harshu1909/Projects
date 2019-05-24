//
//  Notification.swift
//  InViKo
//
//  Created by Student on 2019-03-28.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit
class Notification {
    var content: String
    var fromUser: String
    var toUser: String
    var addedByUser: String
    var time: String
    
    init() {
        content = ""
        fromUser = ""
        toUser = ""
        addedByUser = ""
        time = ""
    }
    
    init(_ content: String, _ time: String) {
        self.content = content
        self.fromUser = ""
        self.toUser = ""
        self.addedByUser = ""
        self.time = time
    }
    
    init(_ content: String, _ fromUser: String, _ toUser: String, _ addedByUser: String, _ time: String) {
        self.content = content
        self.fromUser = fromUser
        self.toUser = toUser
        self.addedByUser = addedByUser
        self.time = time
    }
}
