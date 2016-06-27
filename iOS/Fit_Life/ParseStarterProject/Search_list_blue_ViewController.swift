//
//  Search_list_blue_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/26/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class Search_list_blue_ViewController: UIViewController {

    
    
    var type: String?
    var thing: Int?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(type )
        print(thing)
        if thing == 6{
            self.title = "Select up to 10 Exercies"
        }
        else {
            self.title = type
        }
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
   

   
    
    @IBAction func Back_b(sender: AnyObject) {
        if thing == 1{//exercise
            
        }
        else if thing == 2{//pre programs
            
        }
        else if thing == 3{//pre workouts
            let viewController: Workout_types_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Workout_type") as! Workout_types_ViewController
            self.presentViewController(viewController, animated: true, completion: nil)
            
        }
        else if thing == 6{//create workout
            //add whenver we make the view
            //remember to add storyboard id
            
            
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
