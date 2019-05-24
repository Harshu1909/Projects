//
//  AddPatientViewController.swift
//  InViKo
//
//  Created by Student on 2019-03-27.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class AddPatientViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    
    @IBOutlet weak var FirstName: UITextField!
    @IBOutlet weak var LastName: UITextField!
    @IBOutlet weak var Email: UITextField!
    @IBOutlet weak var Address1: UITextField!
    @IBOutlet weak var Address2: UITextField!
    @IBOutlet weak var City: UITextField!
    @IBOutlet weak var PostalCode: UITextField!
    @IBOutlet weak var Province: UITextField!
    @IBOutlet weak var Height: UITextField!
    @IBOutlet weak var Weight: UITextField!
    @IBOutlet weak var Age: UITextField!
    @IBOutlet weak var ContactNumber: UITextField!
    
    
    
    
    @IBAction func Submit(_ sender: UIButton) {
        
            
            let fname = FirstName.text
            let lname = LastName.text
            let email = Email.text
            let pass = "Password123"
            let cpass = "Password123"
            let address1 = Address1.text
            let address2 = Address2.text
            let city = City.text
            let postalcode = PostalCode.text
            let province = Province.text
            let height = Height.text
            let weight = Weight.text
            let age = Age.text
            let contact = ContactNumber.text
            
            let parameter = [
                "Firstname" : fname,
                "Lastname" : lname,
                "Email" : email,
                "Password" : pass,
                "ConfrimPassword" : cpass,
                "Address1" : address1,
                "Address2" : address2,
                "City" : city,
                "PostalCode" : postalcode,
                "Province": province,
                "Height" : height,
                "Weight" : weight,
                "Age" : age,
                "Contactnumber" : contact,
                "Role": "Patient"
            ]
            guard let url = URL(string:"http://localhost:5000/api/register")  else {
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
                                    
                                 //   let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                                    
                                 //   let LoginController = storyBoard.instantiateViewController(withIdentifier: "LoginScreen") as! LoginController
                                 //   self.present(LoginController, animated: true, completion: nil)
                                    
                                    _ = self.navigationController?.popViewController(animated: true)
                                    
                                }
                                else{
                                    
                                    let alertController = UIAlertController(title: "Login", message:
                                        "Login Done", preferredStyle: UIAlertController.Style.alert)
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
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
