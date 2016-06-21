//
//  SplashViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/19/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse


class SplashViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(animated: Bool) {
        let currentUser = PFUser.currentUser()
        print(currentUser);
        if currentUser != nil {
            self.performSegueWithIdentifier("go_main", sender: self)
            print("why im i here")
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
