//
//  AddNewpatientController.swift
//  InViKo
//
//  Created by Student on 2019-03-19.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class AddNewAppointmentController: UIViewController, UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate {
    
    @IBOutlet weak var tvPatient: UITableView!
    @IBOutlet weak var sbPatient: UISearchBar!
    var patients = [Patient]()
    var filteredPatients = [Patient]()
    var appointment = Appointment()
    var selectedPatient = Patient()
    
    func colorForIndex(_ index: Int) -> UIColor {
        if (index % 2 == 0) {
            return UIColor(red: 0, green: 0.588, blue: 1.0, alpha: 1.0)
        } else {
            return UIColor(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredPatients.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "PatientTableViewCell"
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? PatientTableViewCell else {
            fatalError("The dequeued cell is not an instance of PatientTableViewCell.")
        }
        
        let patient = filteredPatients[indexPath.row]
        cell.lbFname.text = patient.fName + " " + patient.lName
        cell.lbPhone.text = patient.phone
        cell.backgroundColor = colorForIndex(indexPath.row)
        
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tvPatient.dataSource = self
        sbPatient.delegate = self
        loadPatients()
        self.navigationItem.rightBarButtonItem?.isEnabled = false
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if (searchText.isEmpty) {
            filteredPatients = patients
        } else {
            filteredPatients = patients.filter({ (patient: Patient) -> Bool in
                return (patient.fName + " " + patient.lName).range(of: searchText, options: .caseInsensitive) != nil
            })
        }
        tvPatient.reloadData()
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        selectedPatient = filteredPatients[indexPath.row]
        self.navigationItem.rightBarButtonItem?.isEnabled = true
    }
    
    @IBAction func unwindFromAppointmentOptions(_ sender: UIStoryboardSegue) {
        self.navigationItem.rightBarButtonItem?.isEnabled = false
    }
    
    
    @IBAction func btnNext(_ sender: UIBarButtonItem) {
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let AppointmentOptions = storyBoard.instantiateViewController(withIdentifier: "AppointmentOptions") as! AppointmentOptionsController
        AppointmentOptions.appointment.patient = selectedPatient
        self.navigationController?.pushViewController(AppointmentOptions, animated: true)
    }
    
    private func loadPatients() {
        /*
         call API to get Json list -> parse to patients
         Add patients to array
         */
        guard let url = URL(string:"http://localhost:5000/api/list_patients")  else {
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
                                guard let appList = json["List_Patients"] as? [[String: Any]] else {
                                    return
                                }
                                for app in appList {
                                    guard let phone = app["phone_no"] as? String else {return}
                                    guard let id = app["patient_id"] as? Int else {return}
                                    guard let firstName = app["patient_firstname"] as? String else {return}
                                    guard let lastName = app["patient_lastname"] as? String else {return}
                                    let patient = Patient(id, firstName, lastName, "", 0, "", "", phone)
                                    self.patients.append(patient)
                                }
                                self.filteredPatients = self.patients
                                self.tvPatient.reloadData()
                            }
                            else {
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Load patients failed!", preferredStyle: UIAlertController.Style.alert)
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
        
//        var pat1 = Patient("John","Seko","a@gmail.com",1956,"123 Old Drive","Male")
//        var pat2 = Patient("Hudson","Bay","b@gmail.com",1999,"15 Old Drive","Female")
//        var pat3 = Patient("Johny","Reka","a@gmail.com",2005,"123 Old Drive","Male")
//        var pat4 = Patient("John","Seko","a@gmail.com",1982,"123 Old Drive","Male")
//        var pat5 = Patient("Hudson","Bay","b@gmail.com",1970,"15 Old Drive","Female")
//        var pat6 = Patient("John","Reko","a@gmail.com",2005,"123 Old Drive","Male")
//        var pat7 = Patient("Johny","Seko","a@gmail.com",1999,"123 Old Drive","Male")
//        var pat8 = Patient("Hudsot","Bay","b@gmail.com",1982,"15 Old Drive","Female")
//        var pat9 = Patient("John","Seko","a@gmail.com",2005,"123 Old Drive","Male")
//        var pat10 = Patient("John","Seko","a@gmail.com",1999,"123 Old Drive","Male")
//        patients += [pat1,pat2,pat3,pat4,pat5,pat6,pat7,pat8,pat9,pat10]
    }
}
