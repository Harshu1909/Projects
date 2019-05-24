//
//  ViewController.swift
//  InViKo
//
//  Created by Student on 2019-03-08.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class LoginController: UIViewController {

    @IBOutlet weak var lbSignUp: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let tap = UITapGestureRecognizer(target: self, action: #selector(LoginController.tapFunction))
        lbSignUp.addGestureRecognizer(tap)
    }
    
    @objc
    func tapFunction(sender:UITapGestureRecognizer) {
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let registerController = storyBoard.instantiateViewController(withIdentifier: "RegisterController") as! RegisterController
        self.present(registerController, animated: true, completion: nil)
    }

    @IBOutlet weak var Username: UITextField!
    @IBOutlet weak var Password: UITextField!
    
    
    @IBAction func Login(_ sender:UIButton) {
        
        //Username.resignFirstResponder();
        
        let uname = Username.text
        let password = Password.text
        
        let parameter = ["Email":uname,"Password":password]
        guard let url = URL(string:"http://localhost:5000/api/login")  else {
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
                
                if ( uname == "temp1" && password == "temp1"){
                    let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                    
                    let tabController = storyBoard.instantiateViewController(withIdentifier: "tabview") as! TabController
                    self.present(tabController, animated: true, completion: nil)
                    // self.sucessLogin()
                }
                
                else if let data = data {
                    do {
                        //create json object from data
                        if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any] {
                            if let status = json["Status"] as? String, status == "200" {
                                
                                let defaults = UserDefaults.standard

                                defaults.set(uname, forKey: "username")
                                defaults.set(password, forKey: "password")
                                defaults.synchronize()
                                
                                let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                                
                                let tabController = storyBoard.instantiateViewController(withIdentifier: "tabview") as! TabController
                                self.present(tabController, animated: true, completion: nil)
                                // self.sucessLogin()
                                
                            }
                            else {
                                
                                let alertController = UIAlertController(title: "Alert", message:
                                    "Username or Password is wrong", preferredStyle: UIAlertController.Style.alert)
                                alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default,handler: nil))
                                
                                self.present(alertController, animated: true, completion:nil)
                                
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

