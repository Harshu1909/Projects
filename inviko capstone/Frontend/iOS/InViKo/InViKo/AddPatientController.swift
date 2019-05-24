//
//  AddPatientController.swift
//  InViKo
//
//  Created by Student on 2019-04-10.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class AddPatientController: UIViewController {

    @IBOutlet weak var txtFirstName: UITextField!
    @IBOutlet weak var txtLastName: UITextField!
    @IBOutlet weak var txtEmail: UITextField!
    @IBOutlet weak var txtAddress1: UITextField!
    @IBOutlet weak var txtAddress2: UITextField!
    @IBOutlet weak var txtCity: UITextField!
    @IBOutlet weak var txtPotal: UITextField!
    @IBOutlet weak var txtProvince: UITextField!
    @IBOutlet weak var txtHeight: UITextField!
    @IBOutlet weak var txtWeight: UITextField!
    @IBOutlet weak var txtAge: UITextField!
    @IBOutlet weak var txtPhone: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func btnAdd(_ sender: UIButton) {
        let fname = txtFirstName.text
        let lname = txtLastName.text
        let email = txtEmail.text
        let address1 = txtAddress1.text
        let address2 = txtAddress2.text
        let city = txtCity.text
        let postalcode = txtPotal.text
        let province = txtProvince.text
        let height = txtHeight.text
        let weight = txtWeight.text
        let age = txtAge.text
        let contact = txtPhone.text
        
        let parameter = [
            "PatientFirstName" : fname,
            "PatientLastName" : lname,
            "PatientEmail" : email,
            "PatientAdd1" : address1,
            "PatientAdd2" : address2,
            "City" : city,
            "PostalCode" : postalcode,
            "Province": province,
            "PatientHeight" : height,
            "PatientWeight" : weight,
            "PatientAge" : age,
            "Phone" : contact,
            "DoctorId": 1
            ] as [String : Any]
        guard let url = URL(string:"http://localhost:5000/api/add_patient")  else {
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
                            if let status = json["Status"] as? String, status == "200" {
                                
                                let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                                
                                let tabController = storyBoard.instantiateViewController(withIdentifier: "tabview") as! TabController
                                self.present(tabController, animated: true, completion: nil)
                            }
                            else {
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Add patient failed!", preferredStyle: UIAlertController.Style.alert)
                                alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default,handler: nil))
                                
                                self.present(alertController, animated: true, completion: nil)
                            }
                        }
                    }
                    catch let error {
                        print(error.localizedDescription)
                        let alertController = UIAlertController(title: "Alert", message:
                            "Add patient failed!", preferredStyle: UIAlertController.Style.alert)
                        alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default,handler: nil))
                        
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            }
            }.resume()
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
