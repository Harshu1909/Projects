//
//  DashboardController.swift
//  InViKo
//
//  Created by Student on 2019-03-11.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class DashboardController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tvAppoinments: UITableView!
    @IBOutlet weak var lbUser: UILabel!
    @IBOutlet weak var lbNumOfApps: UILabel!
    var user: String = "User"
    
    var appointments = [Appointment]()
    
    func colorForIndex(_ index: Int) -> UIColor {
        if (index % 2 == 0) {
            return UIColor(red: 0, green: 0.588, blue: 1.0, alpha: 1.0)
        } else {
            return UIColor(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return appointments.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "AppointmentTableViewCell"
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? AppointmentTableViewCell else {
            fatalError("The dequeued cell is not an instance of AppointmentTableViewCell.")
        }

        let appointment = appointments[indexPath.row]
        let doctorText = appointment.doctor.firstName + " " + appointment.doctor.lastName
        let patientText = appointment.patient.fName + " " + appointment.patient.lName
        let patientLength = patientText.count
        let doctorLength = doctorText.count
        cell.lbTime.text = appointment.time
        cell.lbDate.text = appointment.date
        cell.lbDoctor.text = doctorLength > 10 ? String(doctorText.prefix(10)) + ".." : doctorText
        cell.lbPatient.text = patientLength > 10 ? subString(patientText, 0 , 10) + ".." : patientText
        cell.backgroundColor = colorForIndex(indexPath.row)
        
        return cell
    }
    
    func subString(_ string: String, _ lowerBound: Int, _ upperBound: Int) -> String {
        let lowerBound = string.index(string.startIndex, offsetBy: lowerBound)
        let upperBound = string.index(string.startIndex, offsetBy: upperBound)
        let subString = string[lowerBound..<upperBound]
        return String(subString)
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        tvAppoinments.dataSource = self
        loadAppointments()
        let defaults = UserDefaults.standard
        user = defaults.string(forKey: "username")!
        lbUser.text = user

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
    
    @IBAction func unwindFromAddNewAppointment(_ sender: UIStoryboardSegue) {
        
    }
    
    @IBAction func unwindWhenAppointmentIsAdded(_ sender: UIStoryboardSegue) {
        if (sender.source is AppointmentOptionsController) {
            if let senderVC = sender.source as? AppointmentOptionsController {
                    appointments.append(senderVC.appointment)
            }
            tvAppoinments.reloadData()
            lbNumOfApps.text = String(appointments.count)
            
        }
    }
    
    public func loadAppointments() {
        /*
         call API to get Json list -> parse to Appointments
         Add appointments to array
         */
        
        guard let url = URL(string:"http://localhost:5000/api/list_appointment")  else {
            return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")

        guard let httpbody = try? JSONSerialization.data(withJSONObject: [], options:[]) else { return }

        request.httpBody = httpbody

        let session = URLSession.shared
        session.dataTask(with: request){(data,response,error) in

            DispatchQueue.main.async {

                if let data = data {
                    do {
                        //create json object from data
                        if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any] {
                            if let status = json["status"] as? Int, status == 200 {
                            guard let appList = json["List_Appointment"] as? [[String: Any]] else {
                                return
                           }
                            for app in appList {
                                guard let time = app["availablity_time"] as? String else {return}
                                guard let date = app["availablity_date"] as? String else {return}
                                guard let patientFirstName = app["patient_firstname"] as? String else {return}
                                guard let patientLastName = app["patient_lastname"] as? String else {return}
                                let patient = Patient()
                                patient.fName = patientFirstName
                                patient.lName = patientLastName
                                guard let doctorFirstName = app["doctor_firstname"] as? String else {return}
                                guard let doctorLastName = app["doctor_lastname"] as? String else {return}
                                let doctor = Doctor()
                                doctor.firstName = doctorFirstName
                                doctor.lastName = doctorLastName
                                let appointment = Appointment(time, date, patient, doctor)
                                self.appointments.append(appointment)
                            }
                            self.tvAppoinments.reloadData()
                            self.lbNumOfApps.text = String(self.appointments.count)
                            }
                            else {
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Load appointments failed!", preferredStyle: UIAlertController.Style.alert)
                                alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default,handler: nil))
                                
                                self.present(alertController, animated: true, completion: nil)
                            }
                        }
                    }
                    catch let error {
                        print(error.localizedDescription)
                    }
                }
            }
            }.resume()
//        var app1 = Appointment("1:30","04/13/2019",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"), "John Reko")
//        var app2 = Appointment("2:21","04/14/2019",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"Josh Joshua")
//        var app3 = Appointment("3:21","05/13/2019",Patient("Remy", "Laine", "remy@gmail.com", 1984, "1223 Old Carriage", "Female"),"John Reko")
//        var app4 = Appointment("5:32","06/11/2019",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"Rock Le")
//        var app5 = Appointment("2:21","09/13/2019",Patient("Remy", "Laine", "remy@gmail.com", 1984, "1223 Old Carriage", "Female"),"Josh Joshua")
//        var app6 = Appointment("6:24","10/13/2019",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"John Reko")
//        var app7 = Appointment("7:12","11/13/2019",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"Rock Le")
//        var app8 = Appointment("6:11","12/13/2019",Patient("Remy", "Laine", "remy@gmail.com", 1984, "1223 Old Carriage", "Female"),"Josh Joshua")
//        var app9 = Appointment("2:54","02/13/2020",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"John Reko")
//        var app10 = Appointment("9:52","04/13/2020",Patient("John", "Seko", "a@gmail.com", 1995, "123 Old Carriage", "Male"),"Josh Joshua")
//        appointments += [app1,app2,app3,app4,app5,app6,app7,app8,app9,app10]
    }

}
