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
    
    
    var bool_weight = 0
    var bool_height = 0
    var type = 1
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //kgorlb
    @objc @IBAction private func kgorlb(radioButton : DLRadioButton) {
        
        if radioButton.selectedButton()!.titleLabel!.text! == "kg"{
            bool_weight = 0
        }
        else{
            bool_weight = 1
        }
       print(bool_weight)
    }
    
    //height
    @objc @IBAction private func height_info(radioButton : DLRadioButton) {
        
        if radioButton.selectedButton()!.titleLabel!.text! == "cm"{
            bool_height = 0
        }
        else{
            bool_height = 1
        }
        print(bool_height)
    }

    @IBAction func hr_btn_press(sender: AnyObject) {
        kg.hidden = true
        lb.hidden = true
        cm.hidden = true
        inch.hidden = true
        type = 2
    }
    
    @IBAction func bmi_pressed(sender: AnyObject) {
        kg.hidden = false
        lb.hidden = false
        cm.hidden = false
        inch.hidden = false
        type = 1
    }

    @IBAction func rep_pressed(sender: AnyObject) {
        kg.hidden = true
        lb.hidden = true
        cm.hidden = true
        inch.hidden = true
        type = 0
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
