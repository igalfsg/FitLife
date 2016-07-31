//
//  SplashViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/19/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

var globalnav = 0
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
        let currentUser = PFUser.currentUser()
        //print(currentUser);
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
            
        }
        else{
            self.performSegueWithIdentifier("go_login", sender: self)
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
