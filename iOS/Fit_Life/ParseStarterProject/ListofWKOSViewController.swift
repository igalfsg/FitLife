//
//  ListofWKOSViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

class ListofWKOSViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var workouts_table: UITableView!
    @IBOutlet weak var top_bar: UIView!
    @IBOutlet weak var title_bar: UILabel!
    @IBOutlet weak var myTabBar: UITabBar!
    
    
    var program: String?
    var imgarr = ["days1", "days2", "days3", "days4", "days5", "days6", "days7", "days8", "days9", "days10", "days11", "days12", "days13", "days14"];
    var workouts = [String]()
    override func viewDidAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = true
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if myTabBar.items != nil && myTabBar.items!.count >= globalnav{
            myTabBar.selectedItem = myTabBar.items![globalnav]
        }
        //hide dumbbar
        self.navigationController?.navigationBarHidden = true
        //print("hi")
        self.title_bar.text = program //set title
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
                self.workouts_table.delegate = self
                self.workouts_table.dataSource = self
                self.workouts_table.reloadData()
            }//end if error
            else{
                let queryWorkoutTypes = PFQuery(className: "Programs")
                //queryWorkoutTypes.fromLocalDatastore()
                queryWorkoutTypes.whereKey("name", equalTo: self.program!);
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
                        self.workouts_table.delegate = self
                        self.workouts_table.dataSource = self
                        self.workouts_table.reloadData()
                    }//end if error
                    else{
                        
                    }//end else
                    
                    
                })//end second query

            }//end else
            
            
        })//end first query
        
        self.top_bar.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
        let currentDevice : UIDevice = UIDevice.currentDevice()
        if currentDevice.userInterfaceIdiom == UIUserInterfaceIdiom.Pad {
            self.workouts_table.rowHeight = 70
        }

    }//end viewdidload

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //number of rows on the table
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return self.workouts.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.workouts_table.dequeueReusableCellWithIdentifier("wkos_list", forIndexPath: indexPath) as! listofWKOSTableViewCell
        mycell.name_lbl.text = workouts[indexPath.row]
        mycell.pic.image = UIImage(named: imgarr[indexPath.row] + ".jpg")
        return mycell
    }
    
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
        //open storyboard
        let viewController: Wko_exercises_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("wko_exercise_list") as! Wko_exercises_ViewController
        viewController.workout = workouts[indexPath.row]
        self.navigationController?.pushViewController(viewController, animated: true)
        
    }
    
    //back button
    @IBAction func back_btn(sender: AnyObject) {
        self.navigationController?.popViewControllerAnimated(true)
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
   
    override func prefersStatusBarHidden() -> Bool {
        return true
    }
    
    
    func tabBar(tabBar: UITabBar, didSelectItem item: UITabBarItem) {
        //This method will be called when user changes tab.
        //print(item.tag)
        print(item.title)
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
