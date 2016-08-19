/**
* Copyright (c) 2015-present, Parse, LLC.
* All rights reserved.
*
* This source code is licensed under the BSD-style license found in the
* LICENSE file in the root directory of this source tree. An additional grant
* of patent rights can be found in the PATENTS file in the same directory.
*/

import UIKit
import Parse

class ViewController: UIViewController,UITextFieldDelegate {
    @IBOutlet weak var pass: UITextField!

    @IBOutlet weak var username: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.hideKeyboardWhenTappedAround()
        // Do any additional setup after loading the view, typically from a nib.
        pass.delegate = self
        username.delegate = self
        //hide dumbbar
        self.navigationController?.navigationBarHidden = true
        //self.view.backgroundColor = UIColor(patternImage: UIImage(named: "bg_register.png")!)
    }
    
    @IBAction func login_pressed(sender: AnyObject) {
        let usern = username.text ?? ""
        let password = pass.text ?? ""
        
        if usern.isEmpty  {
            let errorAlert = UIAlertController (title: "Oops!", message: "Username is empty", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
        else if password.isEmpty {
            let errorAlert = UIAlertController (title: "Oops!", message: "Password is empty", preferredStyle: UIAlertControllerStyle.Alert)
            errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
            self.presentViewController(errorAlert, animated: true, completion: nil);
        }
        else{
            PFUser.logInWithUsernameInBackground(usern, password: password, block: {
                (user: PFUser?, error: NSError?) -> Void in
                if user != nil{
                    
                    let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                    
                    viewController.tabdisp = 0
                    self.navigationController?.pushViewController(viewController, animated: true)
 //                   self.presentViewController(viewController, animated: true, completion: nil)

                }
                else{
                    
                    let errorAlert = UIAlertController (title: "Oops!", message: String(format: "%@ ", error!.userInfo), preferredStyle: UIAlertControllerStyle.Alert)
                    errorAlert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Cancel, handler: nil));
                    self.presentViewController(errorAlert, animated: true, completion: nil);
                }
            })
        }//end else
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool // called when 'return' key pressed. return false to ignore.
    {
        if textField == self.username{
            self.pass.becomeFirstResponder()
        }
        else if textField == self.pass{
            textField.resignFirstResponder()
        }
        return true
    }
}
