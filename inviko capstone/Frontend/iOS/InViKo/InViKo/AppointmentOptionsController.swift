//
//  AppointmentOptionsController.swift
//  InViKo
//
//  Created by Student on 2019-03-30.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class AppointmentOptionsController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    var dpDoctor = UIPickerView()
    var dpDate = UIDatePicker()
    var dpTime = UIPickerView()
    var appointment = Appointment()
    
    @IBOutlet weak var lbFname: UILabel!
    @IBOutlet weak var lbPhone: UILabel!
    @IBOutlet weak var lbAge: UILabel!
    @IBOutlet weak var lbEmail: UILabel!
    @IBOutlet weak var txtDoctor: UITextField!
    @IBOutlet weak var txtDate: UITextField!
    @IBOutlet weak var txtTime: UITextField!
    @IBOutlet weak var tvNote: UITextView!
    
    //var doctors = ["Alley", "Acne", "Serum"]
    let timeSlots = ["8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"]
    
    var doctors = [Doctor]()
    var selectedDoctor = Doctor()
    var selectedDate = ""
    var selectedTime = ""
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        var count = 0
        if (pickerView == dpDoctor) {
            count = doctors.count
        } else if (pickerView == dpTime) {
            count = timeSlots.count
        }
        return count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        var title = ""
        if (pickerView == dpDoctor) {
            title = doctors[row].firstName + " " + doctors[row].lastName
        } else if (pickerView == dpTime) {
            title = timeSlots[row]
        }
        return title
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (pickerView == dpDoctor) {
            selectedDoctor = doctors[row]
            txtDoctor.text = selectedDoctor.firstName + " " + selectedDoctor.lastName
        } else if (pickerView == dpTime) {
            selectedTime = timeSlots[row]
            txtTime.text = selectedTime
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        var label: UILabel
        if let view = view as? UILabel {
            label = view
        } else {
            label = UILabel()
        }
        label.textColor = .black
        label.textAlignment = .center
        label.font = UIFont.systemFont(ofSize: 20)
        if (pickerView == dpDoctor) {
            label.text = doctors[row].firstName + " " + doctors[row].lastName
        } else if (pickerView == dpTime) {
            label.text = timeSlots[row]
        }
        return label
    }
    
    private func createToolbar() {
        let toolBar = UIToolbar()
        toolBar.sizeToFit()
        
        toolBar.barTintColor = .gray
        toolBar.tintColor = .blue
        
        let btnDone = UIBarButtonItem(title: "DONE", style: .done, target: self, action: #selector(AppointmentOptionsController.dismissKeyboard))
        toolBar.setItems([btnDone], animated: false)
        toolBar.isUserInteractionEnabled = true
        
        let toolBar2 = UIToolbar()
        toolBar2.sizeToFit()
        
        toolBar2.barTintColor = .gray
        toolBar2.tintColor = .blue
        let btnDone2 = UIBarButtonItem(title: "DONE", style: .done, target: self, action: #selector(AppointmentOptionsController.dismissKeyboard2))
        toolBar2.setItems([btnDone2], animated: false)
        
        let toolBar3 = UIToolbar()
        toolBar3.sizeToFit()
        
        toolBar3.barTintColor = .gray
        toolBar3.tintColor = .blue
        let btnDone3 = UIBarButtonItem(title: "DONE", style: .done, target: self, action: #selector(AppointmentOptionsController.dismissKeyboard3))
        toolBar3.setItems([btnDone3], animated: false)
        
        txtDoctor.inputAccessoryView = toolBar
        txtTime.inputAccessoryView = toolBar2
        txtDate.inputAccessoryView = toolBar3
    }
    
    @objc func dismissKeyboard() {
        view.endEditing(true)
        if (dpDoctor.selectedRow(inComponent: 0) < 1) {
            pickerView(dpDoctor, didSelectRow: 0, inComponent: 0)
        }
        if (txtDate.text != "" && txtTime.text != "" && txtDoctor.text != "") {
            self.navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            self.navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }
    
    @objc func dismissKeyboard2() {
        view.endEditing(true)
        if (dpTime.selectedRow(inComponent: 0) < 1) {
            pickerView(dpTime, didSelectRow: 0, inComponent: 0)
        }
        if (txtDate.text != "" && txtTime.text != "" && txtDoctor.text != "") {
            self.navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            self.navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }
    
    @objc func dismissKeyboard3() {
        view.endEditing(true)
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MM/dd/yyyy"
        if (dateFormatter.string(from: dpDate.date) == dateFormatter.string(from: Date())) {
            dataChanged(dpDate)
        }
        if (txtDate.text != "" && txtTime.text != "" && txtDoctor.text != "") {
            self.navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            self.navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        dpDoctor.delegate = self
        dpTime.delegate = self
        loadPatient(appointment.patient.id)
        loadDoctors()
        txtDoctor.inputView = dpDoctor
        txtTime.inputView = dpTime
        dpDoctor.backgroundColor = .lightGray
        dpTime.backgroundColor = .lightGray
        tvNote.layer.borderWidth = 1
        tvNote.layer.borderColor = UIColor.lightGray.cgColor
        createToolbar()
        dpDate.datePickerMode = .date
        txtDate.inputView = dpDate
        dpDate.addTarget(self, action: #selector(AppointmentOptionsController.dataChanged(_:)), for: .valueChanged)
        setCalendar()
        self.navigationItem.rightBarButtonItem?.isEnabled = false
    }
    
    @objc func dataChanged(_ dpDate: UIDatePicker) {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MM/dd/yyyy"
        selectedDate = dateFormatter.string(from: dpDate.date)
        txtDate.text = selectedDate
    }
    
    func setCalendar() {
        let calendar = Calendar(identifier: .gregorian)
        var comps = DateComponents()
        comps.year = 1
        let maxDate = calendar.date(byAdding: comps, to: Date())
        comps.year = 0
        let minDate = calendar.date(byAdding: comps, to: Date())
        dpDate.maximumDate = maxDate
        dpDate.minimumDate = minDate
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        appointment.date = txtDate.text!
        appointment.time = txtTime.text!
        appointment.doctor = selectedDoctor
        //save appointment
        guard let url = URL(string:"http://localhost:5000/api/add_appointment")  else {
            return
        }
        let parameter = ["Patient_id":appointment.patient.id,"Doctor_id":appointment.doctor.id,"Time": appointment.time,"Date": appointment.date] as [String : Any]
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        guard let httpbody = try? JSONSerialization.data(withJSONObject: parameter, options:[]) else { return }
        
        request.httpBody = httpbody
        
        let session = URLSession.shared
        session.dataTask(with: request){(data,response,error) in
            
            DispatchQueue.main.async {
                
                if let data = data {
                    do {
                        //create json object from data
                        if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any] {
                            if let status = json["Status"] as? String, status == "200" {
                                let messageController = UIAlertController(title: "Add Appointment", message:
                                    "Appointment is created successfully!", preferredStyle: UIAlertController.Style.actionSheet)
                                self.present(messageController, animated: true) {
                                    Timer.scheduledTimer(withTimeInterval: 0.5, repeats: false, block: { (_) in
                                        messageController.dismiss(animated: true, completion: nil)})}
                            }
                            else {
                                let messageController = UIAlertController(title: "Add Appointment", message:
                                    "Add appointment failed!", preferredStyle: UIAlertController.Style.actionSheet)
                                self.present(messageController, animated: true) {
                                    Timer.scheduledTimer(withTimeInterval: 0.5, repeats: false, block: { (_) in
                                        messageController.dismiss(animated: true, completion: nil)})}
                            }
                        }
                    }
                    catch let error {
                        print(error.localizedDescription)
                    }
                }
            }
            }.resume()
    }
    
    func loadPatient(_ id: Int) {
        
        let parameter = ["Patient_id":id]
        
        guard let url = URL(string:"http://localhost:5000/api/Patient_Details_by_id")  else {
            return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        guard let httpbody = try? JSONSerialization.data(withJSONObject: parameter, options:[]) else { return }
        
        request.httpBody = httpbody
        
        let session = URLSession.shared
        session.dataTask(with: request){(data,response,error) in
            
            DispatchQueue.main.async {
                
                if let data = data {
                    do {
                        //create json object from data
                        if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any] {
                            if let status = json["status"] as? Int, status == 200 {
                                guard let patients = json["Patients_Details"] as? [[String: Any]] else {
                                    return
                                }
                                let patient = patients[0]
                                guard let email = patient["patient_email"] as? String else {return}
                                self.appointment.patient.email = email
                                self.lbFname.text = self.appointment.patient.fName + " " + self.appointment.patient.lName
                                self.lbPhone.text = self.appointment.patient.phone
                                self.lbEmail.text = self.appointment.patient.email
                            }
                            else {
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Load " + self.appointment.patient.fName +  "'s info failed!", preferredStyle: UIAlertController.Style.alert)
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
    }
    
    func loadDoctors() {
        
        guard let url = URL(string:"http://localhost:5000/api/list_doctor")  else {
            return
        }
        let parameter = ["Email": ""]
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        guard let httpbody = try? JSONSerialization.data(withJSONObject: parameter, options:[]) else { return }
        
        request.httpBody = httpbody
        
        let session = URLSession.shared
        session.dataTask(with: request){(data,response,error) in
            
            DispatchQueue.main.async {
                
                if let data = data {
                    do {
                        //create json object from data
                        if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any] {
                            if let status = json["status"] as? Int, status == 200 {
                                guard let docList = json["List_Doctor"] as? [[String: Any]] else {
                                    return
                                }
                                for doc in docList {
                                    guard let id = doc["doctor_id"] as? Int else {return}
                                    guard let firstName = doc["doctor_firstname"] as? String else {return}
                                    guard let lastName = doc["doctor_lastname"] as? String else {return}
                                    let doctor = Doctor(id, firstName, lastName)
                                    self.doctors.append(doctor)
                                }
                            }
                            else {
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Load doctors failed!", preferredStyle: UIAlertController.Style.alert)
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
    }
}
