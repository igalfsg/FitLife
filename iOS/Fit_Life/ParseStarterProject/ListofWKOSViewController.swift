//
//  ListofWKOSViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

class ListofWKOSViewController: UIViewController {

    @IBOutlet weak var navbar: UINavigationBar!
    @IBOutlet weak var workouts_table: UITableView!
    var program: String?
    var imgarr = ["day 1", "day 2", "day 3", "day 4", "day 5", "day 6", "day 7", "day 8", "day 9", "day 10", "day 11", "day 12", "day 13", "day 14"];
    var workouts = [String]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //print("hi")
        navbar.topItem!.title = program //set title 
        let queryWorkoutTypes = PFQuery(className: "Programs")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("name", equalTo: program!);
        queryWorkoutTypes.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            if error == nil {
                self.workouts.append(object!["wk0"] as! String)
                self.workouts.append(object!["wk1"] as! String)
                self.workouts.append(object!["wk2"] as! String)
                self.workouts.append(object!["wk3"] as! String)
                self.workouts.append(object!["wk4"] as! String)
                self.workouts.append(object!["wk5"] as! String)
                self.workouts.append(object!["wk6"] as! String)
                self.workouts.append(object!["wk7"] as! String)
                self.workouts.append(object!["wk8"] as! String)
                self.workouts.append(object!["wk9"] as! String)
                self.workouts.append(object!["wk10"] as! String)
                self.workouts.append(object!["wk11"] as! String)
                self.workouts.append(object!["wk12"] as! String)
                self.workouts.append(object!["wk13"] as! String)
                self.workouts_table.reloadData()
            }//end if error
            else{
                
            }//end else
            
            
        })//end query

    }//end viewdidload

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //number of rows on the table
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        
        return self.workouts.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.workouts_table.dequeueReusableCellWithIdentifier("wkos_list", forIndexPath: indexPath) as! listofWKOSTableViewCell
        mycell.name_lbl.text = workouts[indexPath.row]
        mycell.pic.image = UIImage(named: "day1.jpg") //imgarr[indexPath.row] + ".jpg")
        return mycell
    }
    
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
        //open storyboard
        let viewController: Wko_exercises_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("wko_exercise_list") as! Wko_exercises_ViewController
        viewController.workout = workouts[indexPath.row]
        self.presentViewController(viewController, animated: true, completion: nil)
        
    }
    
    //back button
    @IBAction func back_btn(sender: AnyObject) {
        self.dismissViewControllerAnimated(true, completion: {});
    }
    
    
    
    //favorite button
    @IBAction func favorite_btn(sender: AnyObject) {
        let user = PFUser.currentUser()!.username
        
        let queryWorkoutTypes = PFQuery(className: "my_prog")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("name", equalTo: program!);
        queryWorkoutTypes.whereKey("user", equalTo: user!);
        queryWorkoutTypes.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            if object == nil {// object does not exist create favorite
                let new_fav = PFObject(className: "my_prog")
                new_fav.setObject(user!, forKey: "user")
                new_fav.setObject(self.program!, forKey: "name")
                new_fav.pinInBackground()
                new_fav.saveInBackgroundWithBlock {
                    (success: Bool?, error: NSError?) -> Void in
                    if error == nil{
                        //change buton
                        
                    }
                }
            }//end if object == nil
                
            else{
                //if we decide to delete object maybe do an alert?
                object?.deleteInBackground()
                object?.unpinInBackground()
                //change button
                
            }//end else
            
            
        })//end query

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
