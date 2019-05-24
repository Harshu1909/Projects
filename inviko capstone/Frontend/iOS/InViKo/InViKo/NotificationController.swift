//
//  NotificationController.swift
//  InViKo
//
//  Created by Student on 2019-03-18.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class NotificationController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    var notifications = [Notification]()
    func colorForIndex(_ index: Int) -> UIColor {
        if (index % 2 != 0) {
            return UIColor(red: 0, green: 0.588, blue: 1.0, alpha: 1.0)
        } else {
            return UIColor(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        }
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {

        return notifications.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "NotificationTableViewCell"
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? NotificationTableViewCell  else {
            fatalError("The dequeued cell is not an instance of NotificationTableViewCell.")
        }
        
        let notification = notifications[indexPath.row]
        cell.lbContent.text = notification.content
        cell.lbTime.text = notification.time
        cell.backgroundColor = colorForIndex(indexPath.row)
        
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        loadNotifications()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

    private func loadNotifications() {
        /*
         call API to get Json list -> parse to Notifications
         Add Notifications to array
         */
        var noti1 = Notification("You have a new appointment today", "1:00")
        var noti2 = Notification("You changed your appointment's time to 2:30", "2:21")
        var noti3 = Notification("You have a new appointment today", "3:12")
        var noti4 = Notification("You have a new appointment today", "6:22")
        var noti5 = Notification("You have a new appointment today", "7:45")
        var noti6 = Notification("You changed your appointment's time", "2:21")
        var noti7 = Notification("You changed your appointment's time", "1:12")
        var noti8 = Notification("You changed your appointment's time", "1:33")
        var noti9 = Notification("You changed your appointment's time", "1:20")
        var noti10 = Notification("You changed your appointment's time", "2:24")
        notifications += [noti1,noti2,noti3,noti4,noti5,noti6,noti7,noti8,noti9,noti10]
    }
}
