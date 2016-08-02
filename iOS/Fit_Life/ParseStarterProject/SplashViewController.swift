//
//  SplashViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/19/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

var globalnav = 9
class SplashViewController: UIViewController {
    
    @IBOutlet weak var bg_image: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        //hide dumbbar
        self.navigationController?.navigationBarHidden = true
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(animated: Bool) {
        let currentUser = PFUser.currentUser()?.username
        print(currentUser);
        bg_image.image = nil
        if currentUser != nil {
            if globalnav == 0 {//exercises
                let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                viewController.tabdisp = 0
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            else if globalnav == 1 {//calculator
                let viewController: FitnessCalViewController = self.storyboard?.instantiateViewControllerWithIdentifier("magic_calculator") as! FitnessCalViewController
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            else if globalnav == 2 {//favorite
                let viewController: Favorites_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("tots_my_fav") as! Favorites_ViewController
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            else if globalnav == 3 {//programs
                let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                viewController.tabdisp = 1
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            else if globalnav == 4 {//workouts
                let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                viewController.tabdisp = 2
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            else if globalnav == 9 {//opening the app
                updateparse()
                globalnav = 0
                let viewController: Program_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Program_type") as! Program_types_ViewController
                viewController.tabdisp = 0
                self.navigationController?.pushViewController(viewController, animated: true)
            }
            
        }
        else{
            self.performSegueWithIdentifier("go_login", sender: self)
        }
    }
    
    func updateparse(){
        //pin stuff
        let user = PFUser.currentUser()!.username
        //workouts
        let queryWorkoutTypes = PFQuery(className: "Workouts")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.orderByAscending("name")
        queryWorkoutTypes.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
            }//end else
        }//end first query
        //WK_Types
        let queryWorkoutTypes1 = PFQuery(className: "Wk_Types")
        queryWorkoutTypes1.orderByAscending("name")
        queryWorkoutTypes1.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
            }//end else
        }//end first query
        //programs
        let queryWorkoutTypes2 = PFQuery(className: "Programs")
        queryWorkoutTypes2.orderByAscending("name")
        queryWorkoutTypes2.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
                
            }//end else
        }//end first query
        //exercises
        let queryWorkoutTypes3 = PFQuery(className: "Exercises")
        queryWorkoutTypes3.limit = 1000
        queryWorkoutTypes3.orderByAscending("name")
        queryWorkoutTypes3.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
                let queryWorkoutTypes = PFQuery(className: "Exercises")
                queryWorkoutTypes.fromLocalDatastore()
                queryWorkoutTypes.limit = 1000
                queryWorkoutTypes.orderByAscending("name")
                queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                    (objects: [PFObject]?, error: NSError?) -> Void in
                    if error == nil {
                        
                    }
                    else{
                        let alertController = UIAlertController(title: "Oops!", message: "We can't contact the server at this time and no saved exercises were found please make sure you have an internet connection and try again. you can still use the app but some features might be unavailable", preferredStyle: .Alert)
                        let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                        alertController.addAction(OKAction)
                        self.presentViewController(alertController, animated: true) { }
                    }//end else
                }//end first query
            }//end else
        }//end first query
        //my_programs
        let queryWorkoutTypes4 = PFQuery(className: "my_prog")
        queryWorkoutTypes4.whereKey("user", equalTo: user!);
        queryWorkoutTypes4.orderByAscending("name")
        queryWorkoutTypes4.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
            }//end else
        }//end first query
        //my_workouts
        let queryWorkoutTypes5 = PFQuery(className: "my_wko")
        queryWorkoutTypes5.whereKey("user", equalTo: user!);
        queryWorkoutTypes5.orderByAscending("name")
        queryWorkoutTypes5.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                PFObject.pinAllInBackground(objects)
            }
            else{
                
            }//end else
        }//end first query
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