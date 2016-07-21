//
//  FitnessCalViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/17/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import DLRadioButton


class FitnessCalViewController: UIViewController {

    @IBOutlet weak var rep_btn: UIButton!
    @IBOutlet weak var BMI_btn: UIButton!
    @IBOutlet weak var HR_btn: UIButton!
    @IBOutlet weak var top_txt: UITextField!
    @IBOutlet weak var bottom_txt: UITextField!
    @IBOutlet weak var kg: DLRadioButton!
    @IBOutlet weak var cm: DLRadioButton!
    @IBOutlet weak var lb: DLRadioButton!
    @IBOutlet weak var inch: DLRadioButton!
    @IBOutlet weak var result_txt: UITextView!
    @IBOutlet weak var calculate_btn: UIButton!
    @IBOutlet weak var nav_bar: UINavigationBar!
    var weightbmi = 0.0;
    var heightbmi = 0.0;
    var type = 1
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        self.nav_bar.setBackgroundImage(UIImage(named: "header.png"), forBarMetrics: .Default)
        self.view.addSubview(self.nav_bar)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func textFieldShouldReturn(textField: UITextField) -> Bool // called when 'return' key pressed. return false to ignore.
        //dont forget to delegate the textfields on viewdidload
        //and inherit the textfield thing for the class
    {
        if textField == self.top_txt{
            self.bottom_txt.becomeFirstResponder()
        }
        else if textField == self.bottom_txt{
            self.calculate_magic(calculate_btn)
        }
                return true
    }
    
    //kgorlb
    @objc @IBAction private func kgorlb(radioButton : DLRadioButton) {
        
        if radioButton.selectedButton()!.titleLabel!.text! == "kg"{
            weightbmi = 1.0
        }
        else{
            weightbmi = 0.453592
        }
    }
    
    //height
    @objc @IBAction private func height_info(radioButton : DLRadioButton) {
        
        if radioButton.selectedButton()!.titleLabel!.text! == "cm"{
            heightbmi = 1.0
        }
        else{
            heightbmi = 2.54
        }
        
    }

    @IBAction func hr_btn_press(sender: AnyObject) {
        kg.hidden = true
        lb.hidden = true
        cm.hidden = true
        inch.hidden = true
        top_txt.placeholder = "Age"
        bottom_txt.placeholder = "Resting Heart Rate"
        print("hr")
        result_txt.text = ""
        top_txt.text = ""
        bottom_txt.text = ""
        type = 2
    }
    
    @IBAction func bmi_pressed(sender: AnyObject) {
        kg.hidden = false
        lb.hidden = false
        cm.hidden = false
        inch.hidden = false
        top_txt.placeholder = "Weight"
        bottom_txt.placeholder = "Height"
        type = 1
        result_txt.text = ""
        top_txt.text = ""
        bottom_txt.text = ""
        print("Yoooooo")
    }

    @IBAction func rep_pressed(sender: AnyObject) {
        kg.hidden = true
        lb.hidden = true
        cm.hidden = true
        inch.hidden = true
        top_txt.placeholder = "Repetitions"
        bottom_txt.placeholder = "Weight"
        print("rep")
        result_txt.text = ""
        top_txt.text = ""
        bottom_txt.text = ""
        type = 0
    }
    

    @IBAction func calculate_magic(sender: AnyObject) {
        if top_txt.text == "" || bottom_txt == ""{
            if type == 0{//1 rep max
                result_txt.text = " Please enter the number of repetitions and weight used"
            }
            else if type == 1{//bmi
                result_txt.text = " Please enter your height and weight and select the correct units"
            }
            else{//hr
                result_txt.text = " Please enter your resting heartrate and your age"
            }
        }
        else if type == 0{//1 rep max
            let weight = Double(bottom_txt.text!)
            let reps = Double(top_txt.text!)
            let max = weight! / (1.0278 - (0.0278 * reps!))
            let six = Double(round((max * 0.83) * 100.0) / 100.0)
            let eight = Double(round((max * 0.78) * 100.0) / 100.0)
            let ten = Double(round((max * 0.75) * 100.0) / 100.0)
            let twelve = Double(round((max * 0.70) * 100.0) / 100.0)
            let fifteen = Double(round((max * 0.67) * 100.0) / 100.0)
            
            result_txt.text = "1 Rep:  " + String(Double(round(max * 100.0) / 100.0)) +
            "\n 6 Reps: " + String(six) +
            "\n 8 Reps: " + String(eight) +
            "\n 10 Reps:" + String(ten) +
            "\n 12 Reps:" + String(twelve) +
            "\n 15 Reps:" + String(fifteen)
        }
        else if type == 1{//bmi
            let weight = Double(bottom_txt.text!)! * weightbmi
            let height = Double(top_txt.text!)! * heightbmi
            let result = Double(round(weight / ((height / 100.0) * (height / 100.0)) * 100.0) / 100.0)
            if result < 18.5 {
                result_txt.text = String(result) + " Underweight"
            }
            else if result < 25{
                result_txt.text = String(result) + " Normal weight"
            }
            else if result < 30{
                result_txt.text = String(result) + " Overweight"
            }
            else{
                result_txt.text = String(result) + " Obese"
            }
        }
        else{//hr
            let resthr = Int(bottom_txt.text!)
            let age = Int(top_txt.text!)
            let zone1 = Int(0.59 * Double(220 - age! - resthr!)) + resthr!
            let zone2_min = Int(0.60 * Double(220 - age! - resthr!)) + resthr!
            let zone2_max = Int(0.78 * Double(220 - age! - resthr!)) + resthr!
            let zone3_min = Int(0.79 * Double(220 - age! - resthr!)) + resthr!
            let zone3_max = Int(0.90 * Double(220 - age! - resthr!)) + resthr!
            
            result_txt.text = "Health \t\t\t up to: " + String(zone1) + " bpm\n" +
            "Weight Loss: \t\t from: " + String(zone2_min) + " to " + String(zone2_max) + " bpm\n" +
            "Cardio improvement: \t\t from: " + String(zone3_min) + " to " + String(zone3_max) + " bpm\n"
        }

    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
