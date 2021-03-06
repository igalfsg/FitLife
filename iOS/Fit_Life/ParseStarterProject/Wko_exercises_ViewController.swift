//
//  Wko_exercises_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

class Wko_exercises_ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var Calories_lbl: UILabel!
    @IBOutlet weak var exercise_table: UITableView!
    @IBOutlet weak var title_view: UILabel!
    @IBOutlet weak var top_view: UIView!
    @IBOutlet weak var myTabBar: UITabBar!
    
    
    var workout: String?
    var numberofexercises = 0
    var exercises = [String]()
    var sets = [String]()
    var reps = [String]()
    var weights = [String]()
    var pesos = [Int]()
    var tField: UITextField!
    override func viewDidAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = true
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        title_view.text = workout //set title
        if myTabBar.items != nil && myTabBar.items!.count >= globalnav{
            myTabBar.selectedItem = myTabBar.items![globalnav]
        }
        //hide dumbbar
        self.navigationController?.navigationBarHidden = true
        
        
        loadtable ()
        //UIBS
        self.top_view.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
        let currentDevice : UIDevice = UIDevice.currentDevice()
        if currentDevice.userInterfaceIdiom == UIUserInterfaceIdiom.Pad {
            self.exercise_table.rowHeight = 120
        }
        
    }//end viewdidload
    func loadtable (){
        let queryWorkoutTypes = PFQuery(className: "Workouts")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("name", equalTo: workout!);
        queryWorkoutTypes.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            
            if error == nil {
                self.numberofexercises = object!["number_exercises"] as! Int
                self.Calories_lbl.text = "Calories: " + String(object!["Calories"] as! Int)
                var exname = String()
                var i = 0
                while i < self.numberofexercises{
                    exname = object!["exercise" + String(i)] as! String
                    self.exercises.append(exname)
                    if exname == "Cycling" || exname == "Elliptical" || exname == "Jump Rope" || exname == "Running" || exname == "Rowing" || exname == "Swimming" {
                        
                        self.sets.append("intensity: " + String(object!["set" + String(i)] as! Int))
                        self.reps.append("length min: " + String(object!["rep" + String(i)] as! Int))
                        self.weights.append(" ")
                        self.pesos.append(0)
                        
                    }//end if cardio
                    else if exname == "Super set" {
                        self.sets.append("intensity: " + String(object!["set" + String(i)] as! Int))
                        self.reps.append(" ")
                        self.weights.append(" ")
                        self.pesos.append(0)
                    }
                    else {
                        if object!["set" + String(i)] as! Int == 93 {
                            self.sets.append("Super Set")
                            self.reps.append("Reps: " + String(object!["rep" + String(i)] as! Int))
                            self.weights.append("Weight: 0")
                            self.pesos.append(0)
                        }
                        else {
                            self.sets.append("Sets: " + String(object!["set" + String(i)] as! Int))
                            self.reps.append("Reps: " + String(object!["rep" + String(i)] as! Int))
                            self.weights.append("Weight: 0")
                            self.pesos.append(0)
                        }
                    }
                    
                    
                    i += 1
                }//end while
                
                
                //get weights
                let user = PFUser.currentUser()!.username
                let weightquery = PFQuery(className: "my_wko")
                //weightquery.fromLocalDatastore()
                weightquery.whereKey("name", equalTo: self.workout!);
                weightquery.whereKey("user", equalTo: user!);
                weightquery.getFirstObjectInBackgroundWithBlock({
                    (object:PFObject?, error:NSError?)  in
                    if object != nil {
                        var i = 0
                        while i < self.numberofexercises{
                            if self.weights[i] == "Weight: 0"{
                                if object!["weight" + String(i)] != nil{
                                    self.weights[i] = "Weight: " + String(object!["weight" + String(i)] as! Int)
                                    self.pesos[i] = object!["weight" + String(i)] as! Int
                                }
                                else{
                                    self.weights[i] = "Weight: 0"
                                    self.pesos[i] = 0
                                }
                                
                            }
                            
                            i += 1
                        }//end while
                        self.exercise_table.reloadData()
                    }//end if object
                })//end query
                
                self.exercise_table.reloadData()
            }//end if error
            else{
                
            }//end else
            
            
        })//end query
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    //number of rows on the table
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        
        return self.exercises.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.exercise_table.dequeueReusableCellWithIdentifier("WKO_exercise_list", forIndexPath: indexPath) as! WKO_exerciseTableViewCell
        mycell.name_lbl.text = exercises[indexPath.row]
        mycell.pic.image = UIImage(named: self.exercises[indexPath.row].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + ".jpg")
        if UIImage(named: self.exercises[indexPath.row].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + ".jpg") == nil{
            mycell.pic.image = UIImage(named:"logo_icon.png")
        }
        //print(weights[indexPath.row])
        mycell.reps_lbl.text = reps[indexPath.row]
        mycell.sets_lbl.text = sets[indexPath.row]
        mycell.weight_lbl.text = weights[indexPath.row]
        mycell.changeweight_btn.tag = indexPath.row
        mycell.changeweight_btn.addTarget(self, action: #selector(Wko_exercises_ViewController.changeAction(_:)), forControlEvents: .TouchUpInside)
        return mycell
    }
    //change weight functions
    
    //button pressed
    @IBAction func changeAction(sender: UIButton){
        //print(sender.tag)
        let alert = UIAlertController(title: "Enter Weight for" + exercises[sender.tag], message: "", preferredStyle: UIAlertControllerStyle.Alert)
        
        alert.addTextFieldWithConfigurationHandler(configurationTextField)
        alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertActionStyle.Cancel, handler:handleCancel))
        alert.addAction(UIAlertAction(title: "Done", style: UIAlertActionStyle.Default, handler:{ (UIAlertAction)in
            //print("Done !!")
            //print("Item : \(self.tField.text)")
            let user = PFUser.currentUser()!.username
            let weightquery = PFQuery(className: "my_wko")
            //weightquery.fromLocalDatastore()
            weightquery.whereKey("name", equalTo: self.workout!);
            weightquery.whereKey("user", equalTo: user!);
            weightquery.getFirstObjectInBackgroundWithBlock({
                (object:PFObject?, error:NSError?)  in
                if object != nil {
                    if self.tField.text != ""{
                        object!["weight" + String(sender.tag)] = Int(self.tField.text!)
                    }
                    object?.saveInBackground()
                    object?.pinInBackground()
                }//end if object
                else{
                    let new_fav = PFObject(className: "my_wko")
                    new_fav.setObject(user!, forKey: "user")
                    new_fav.setObject(self.workout!, forKey: "name")
                    if self.tField.text != ""{
                        new_fav.setObject(Int(self.tField.text!)!, forKey: "weight" + String(sender.tag))
                    }
                    new_fav.pinInBackground()
                    new_fav.saveInBackgroundWithBlock {
                        (success: Bool?, error: NSError?) -> Void in
                        if error == nil{
                            //change buton
                            
                        }
                    }
                }//end if object == nil
                
                
                self.loadtable ()
            })//end query
        }))//end alert
        self.presentViewController(alert, animated: true, completion: {
            //print("completion block")
        })
    }
    
    
    //alert functions
    
    
    func configurationTextField(textField: UITextField!)
    {
        //print("generating the TextField")
        textField.placeholder = "Enter Weight"
        textField.keyboardType = UIKeyboardType.NumberPad
        tField = textField
    }
    
    
    func handleCancel(alertView: UIAlertAction!)
    {
        //print("Cancelled !!")
    }
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        //open storyboard
        let viewController: ExerciseViewController = self.storyboard?.instantiateViewControllerWithIdentifier("exercise_detail") as! ExerciseViewController
        viewController.exercise = exercises[indexPath.row]
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    override func prefersStatusBarHidden() -> Bool {
        return true
    }
    
    //butons
    
    
    
    //back button
    @IBAction func back_Btn(sender: AnyObject) {
        self.navigationController?.popViewControllerAnimated(true)
    }
    
    
    
    @IBAction func favorite_btn(sender: AnyObject) {
        let user = PFUser.currentUser()!.username
        
        let queryWorkoutTypes = PFQuery(className: "my_wko")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("name", equalTo: workout!);
        queryWorkoutTypes.whereKey("user", equalTo: user!);
        queryWorkoutTypes.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            if object == nil {// object does not exist create favorite
                let new_fav = PFObject(className: "my_wko")
                new_fav.setObject(user!, forKey: "user")
                new_fav.setObject(self.workout!, forKey: "name")
                new_fav.pinInBackground()
                new_fav.saveInBackgroundWithBlock {
                    (success: Bool?, error: NSError?) -> Void in
                    if error == nil{
                        //change buton
                        let alertController = UIAlertController(title: "Added " + self.workout! + " to Favorites", message: "", preferredStyle: .Alert)
                        let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                        alertController.addAction(OKAction)
                        self.presentViewController(alertController, animated: true) { }
                    }
                }
            }//end if object == nil
                
            else{
                //if we decide to delete object maybe do an alert?
                object?.deleteInBackground()
                object?.unpinInBackground()
                //change button
                let alertController = UIAlertController(title: "Removed " + self.workout! + " from Favorites", message: "", preferredStyle: .Alert)
                let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                alertController.addAction(OKAction)
                self.presentViewController(alertController, animated: true) { }
            }//end else
            
            
        })//end query
    }
    
    func tabBar(tabBar: UITabBar, didSelectItem item: UITabBarItem) {
        //This method will be called when user changes tab.
        //print(item.tag)
        //print(item.title)
        if item.title == "Exercises"{
            globalnav = 0
            self.navigationController?.popToRootViewControllerAnimated(false)
        }
        else if item.title == "Fitness Calculator" {
            globalnav = 1
            self.navigationController?.popToRootViewControllerAnimated(false)
        }
        else if item.title == "Favorites" {
            globalnav = 2
            self.navigationController?.popToRootViewControllerAnimated(false)
        }
        else if item.title == "Programs" {
            globalnav = 3
            self.navigationController?.popToRootViewControllerAnimated(false)
        }
        else if item.title == "Workouts" {
            globalnav = 4
            self.navigationController?.popToRootViewControllerAnimated(false)
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
