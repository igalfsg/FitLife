//
//  RegisterViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/16/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import DLRadioButton
import Parse

class RegisterViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var EmailTextField: UITextField!
    @IBOutlet weak var PasswordTextField: UITextField!
    @IBOutlet weak var BirthdateTextField: UITextField!
    @IBOutlet weak var WeightTextField: UITextField!
    @IBOutlet weak var kgBtn: DLRadioButton!
    @IBOutlet weak var interest_button: DLRadioButton!
    @IBOutlet weak var interest_view: UIStackView!
    
    var sex = ""
    var peso = ""
    var interests = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.hideKeyboardWhenTappedAround()
        // Do any additional setup after loading the view.
        self.interest_button.multipleSelectionEnabled = true;
        
        usernameTextField.delegate = self
        EmailTextField.delegate = self
        PasswordTextField.delegate = self
        BirthdateTextField.delegate = self
        /*
        let errorAlert = UIAlertController (title: "Welcome to the Fit Life Family!", message: "Only username and password are req", preferredStyle: UIAlertControllerStyle.Alert)
        errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
        self.presentViewController(errorAlert, animated: true, completion: nil);
         */
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    
    //kg or pund selected
    @objc @IBAction private func logSelectedButton(radioButton : DLRadioButton) {
       
        if (radioButton.multipleSelectionEnabled) {
            for button in radioButton.selectedButtons() {
                print(String(format: "%@ q selected. 2\n", button.titleLabel!.text!));
                view.endEditing(true)
            }
        } else {
            peso = radioButton.selectedButton()!.titleLabel!.text!
            view.endEditing(true)
        }
    }
    
    //male or female
    @objc @IBAction private func maleorfemale(radioButton : DLRadioButton) {
       
        if (radioButton.multipleSelectionEnabled) {
            for button in radioButton.selectedButtons() {
                print(String(format: "%@ q selected. 2\n", button.titleLabel!.text!));
                view.endEditing(true)
            }
        } else {
            sex = radioButton.selectedButton()!.titleLabel!.text!
            view.endEditing(true)
        }
    }
    
    //interests
    @objc @IBAction private func loginteretsts(radioButton : DLRadioButton) {
   
        if (radioButton.multipleSelectionEnabled) {
            interests = ""
            for button in radioButton.selectedButtons() {
                interests +=  button.titleLabel!.text!            }
        } else {
            print(String(format: "%@ is selected.\n", radioButton.selectedButton()!.titleLabel!.text!));
        }
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool // called when 'return' key pressed. return false to ignore.
        //dont forget to delegate the textfields on viewdidload
        //and inherit the textfield thing for the class
    {
        if textField == self.usernameTextField{
            self.EmailTextField.becomeFirstResponder()
        }
        else if textField == self.EmailTextField{
            self.PasswordTextField.becomeFirstResponder()
        }
        else if textField == self.PasswordTextField{
            self.BirthdateTextField.becomeFirstResponder()
        }
        else if textField == self.BirthdateTextField{
            self.WeightTextField.becomeFirstResponder()
        }
        return true
    }
    
    //register button clicked
    @IBAction func register_Btn(sender: AnyObject) {
        let username = usernameTextField.text ?? ""
        let email = EmailTextField.text ?? ""
        let password = PasswordTextField.text ?? ""
        let bday = BirthdateTextField.text ?? ""
        let weight = WeightTextField.text ?? ""
        
        if username.isEmpty {
            //one of the fiends is empty display error
            let errorAlert = UIAlertController (title: "Oops!", message: "Username is empty", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
        else if password.isEmpty {
            let errorAlert = UIAlertController (title: "Oops!", message: "Password is empty", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
            /*
        else if(email.isEmpty || !isValidEmail(email) ){
            let errorAlert = UIAlertController (title: "Oops!", message: "Please enter a valid email", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
        else if(bday.isEmpty || !isVaildbday(bday)){
            let errorAlert = UIAlertController (title: "Oops!", message: "Please enter a birth day with format: mm/dd/yyyy", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
        else if(weight.isEmpty ){
            let errorAlert = UIAlertController (title: "Oops!", message: "Please enter your weight", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }*/
        else if((!weight.isEmpty) && peso.isEmpty ){
            let errorAlert = UIAlertController (title: "Oops!", message: "Please Select the weight units", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }/*
        else if(sex.isEmpty ){
            let errorAlert = UIAlertController (title: "Oops!", message: "Please Select your gender", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }*/
        else{
            let user = PFUser()
            user.username = username
            user.password = password
            if(email.isEmpty){
                user.email = username + "@fitlife.com"
            }
            else{
                user.email = email
            }
            
            user["birthday"] = bday
            user["weight"] = weight
            //stuff from buttons
            user["peso"] = peso
            user["interests"] = interests
            user["sex"] = sex
            user.signUpInBackgroundWithBlock {
                (succeeded: Bool, error: NSError?) -> Void in
                if error == nil {
                    // Hooray! Let them use the app now.
                    let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                    viewController.tabdisp = 0
                    self.navigationController?.pushViewController(viewController, animated: true)
                    
                } else {
                    // Show the errorString somewhere and let the user try again.
                    let errorAlert = UIAlertController (title: "Oops!", message: String(format: "%@ ", error!.userInfo), preferredStyle: UIAlertControllerStyle.Alert)
                    errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
                    self.presentViewController(errorAlert, animated: true, completion: nil);
                }
            }
            
        }//end else
        
    }
    
    func isValidEmail(testStr:String) -> Bool {
        // print("validate calendar: \(testStr)")
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailTest.evaluateWithObject(testStr)
    }
    
    func isVaildbday(testStr:String) -> Bool {
        // print("validate calendar: \(testStr)")
        let emailRegEx = "[0-9]{1,2}/{1}[0-9]{1,2}/{1}[0-9]{4}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailTest.evaluateWithObject(testStr)
    }
    override func prefersStatusBarHidden() -> Bool {
        return true
    }
    
}

extension UIViewController {
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
    
    func dismissKeyboard() {
        view.endEditing(true)
    }
}
