//
//  Favorites_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

class Favorites_ViewController: UIViewController {
    var wkoorprog = 0
    let user = PFUser.currentUser()!.username
    var workouts = [String]()
    @IBOutlet weak var wkos_Btn: UIButton!
    @IBOutlet weak var prog_btn: UIButton!
    @IBOutlet weak var disp_table: UITableView!
    @IBOutlet weak var top_view: UIView!
    @IBOutlet weak var title_view: UILabel!
    @IBOutlet weak var myTabBar: UITabBar!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if myTabBar.items != nil && myTabBar.items!.count >= globalnav{
            myTabBar.selectedItem = myTabBar.items![globalnav]
        }
        //hide dumbbar
        self.navigationController?.navigationBarHidden = true
        self.title_view.text = "Favorites"
        /*
        let queryWorkoutTypes = PFQuery(className: "my_wko")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("user", equalTo: user!);
        queryWorkoutTypes.orderByAscending("name")
        queryWorkoutTypes.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                for object in objects!{
                    
                    self.workouts.append(object["name"] as! String)
                    
                }
                self.disp_table.reloadData()
            }
            else{
                
            }//end else
            if self.workouts.count  == 0 {
                let alertController = UIAlertController(title: "Oops!", message: "No Favorite workouts were found please go to programs add workouts to your favorites", preferredStyle: .Alert)
                let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                alertController.addAction(OKAction)
                self.presentViewController(alertController, animated: true) { }
            }
        }//end first query
        */
        self.top_view.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
        let currentDevice : UIDevice = UIDevice.currentDevice()
        if currentDevice.userInterfaceIdiom == UIUserInterfaceIdiom.Pad {
            self.disp_table.rowHeight = 100
        }
    }//end viewdidload
    override func viewDidAppear(animated: Bool) {
        self.navigationController?.navigationBarHidden = true
        reloadtable()
    }
    func reloadtable(){
        if wkoorprog == 0 {//wko
            workouts = [String]()
            let queryWorkoutTypes = PFQuery(className: "my_wko")
            queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKey("user", equalTo: user!);
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    for object in objects!{
                        
                        self.workouts.append(object["name"] as! String)
                        
                    }
                    self.disp_table.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "my_wko")
                    //queryWorkoutTypes.fromLocalDatastore()
                    queryWorkoutTypes.whereKey("user", equalTo: self.user!);
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            for object in objects!{
                                
                                self.workouts.append(object["name"] as! String)
                                
                            }
                            self.disp_table.reloadData()
                        }
                        else{
                            
                        }//end else
                        if self.workouts.count  == 0 {
                            let alertController = UIAlertController(title: "Oops!", message: "No Favorite workouts were found please go to programs add workouts to your favorites", preferredStyle: .Alert)
                            let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                            alertController.addAction(OKAction)
                            self.presentViewController(alertController, animated: true) { }
                        }
                    }//end first query
                }//end else
                if self.workouts.count  == 0 {
                    let alertController = UIAlertController(title: "Oops!", message: "No Favorite workouts were found please go to programs add workouts to your favorites", preferredStyle: .Alert)
                    let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                    alertController.addAction(OKAction)
                    self.presentViewController(alertController, animated: true) { }
                }
            }//end first query
            
        }
        else if wkoorprog == 1 {//programs
            workouts = [String]()
            let queryWorkoutTypes = PFQuery(className: "my_prog")
            queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKey("user", equalTo: user!);
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    for object in objects!{
                        
                        self.workouts.append(object["name"] as! String)
                        
                    }
                    self.disp_table.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "my_prog")
                    //queryWorkoutTypes.fromLocalDatastore()
                    queryWorkoutTypes.whereKey("user", equalTo: self.user!);
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            for object in objects!{
                                
                                self.workouts.append(object["name"] as! String)
                                
                            }
                            self.disp_table.reloadData()
                        }
                        else{
                            
                        }//end else
                        if self.workouts.count  == 0 {
                            let alertController = UIAlertController(title: "Oops!", message: "No Favorite Programs were found please go to programs add programs to your favorites", preferredStyle: .Alert)
                            let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                            alertController.addAction(OKAction)
                            self.presentViewController(alertController, animated: true) { }
                        }
                    }//end second query
                }//end else
                if self.workouts.count  == 0 {
                    let alertController = UIAlertController(title: "Oops!", message: "No Favorite Programs were found please go to programs add programs to your favorites", preferredStyle: .Alert)
                    let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                    alertController.addAction(OKAction)
                    self.presentViewController(alertController, animated: true) { }
                }
            }//end first query
        }
    }
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
        let mycell =  self.disp_table.dequeueReusableCellWithIdentifier("fav_list", forIndexPath: indexPath) as! FavoritesCell
        mycell.name_fav.text = workouts[indexPath.row]
        mycell.img_fav.image = UIImage(named:"logo_icon.png")
        return mycell
    }
    
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        if wkoorprog == 0 {//wko
            //open storyboard
            let viewController: Wko_exercises_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("wko_exercise_list") as! Wko_exercises_ViewController
            viewController.workout = workouts[indexPath.row]
            self.navigationController?.pushViewController(viewController, animated: true)
        }
        else if wkoorprog == 1 {//programs
            //open storyboard
            let viewController: ListofWKOSViewController = self.storyboard?.instantiateViewControllerWithIdentifier("list_of_wko") as! ListofWKOSViewController
            viewController.program = workouts[indexPath.row]
            self.navigationController?.pushViewController(viewController, animated: true)
        }
        
        
    }
    
    func UIColorFromRGB(rgbValue: UInt) -> UIColor {
        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
    
    //buttons
    @IBAction func Wokouts_btn(sender: AnyObject) {
        //workouts btn pressed
        prog_btn.backgroundColor = UIColorFromRGB(0xD65A0B)//not selected
        wkos_Btn.backgroundColor = UIColorFromRGB(0xFD9459)//selected
        workouts = [String]()
        let queryWorkoutTypes = PFQuery(className: "my_wko")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("user", equalTo: user!);
        queryWorkoutTypes.orderByAscending("name")
        queryWorkoutTypes.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                for object in objects!{
                    
                    self.workouts.append(object["name"] as! String)
                    
                }
                self.disp_table.reloadData()
            }
            else{
                
            }//end else
            if self.workouts.count  == 0 {
                let alertController = UIAlertController(title: "Oops!", message: "No Favorite workouts were found please go to programs add workouts to your favorites", preferredStyle: .Alert)
                let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                alertController.addAction(OKAction)
                self.presentViewController(alertController, animated: true) { }
            }
        }//end first query
        wkoorprog = 0
    }
    
    
    
    @IBAction func Proframs_btn(sender: AnyObject) {
        //programs btn pressed
        
        wkos_Btn.backgroundColor =  UIColorFromRGB(0xD65A0B)//not selected
        prog_btn.backgroundColor = UIColorFromRGB(0xFD9459)//selected
        workouts = [String]()
        let queryWorkoutTypes = PFQuery(className: "my_prog")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("user", equalTo: user!);
        queryWorkoutTypes.orderByAscending("name")
        queryWorkoutTypes.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                for object in objects!{
                    
                    self.workouts.append(object["name"] as! String)
                    
                }
                self.disp_table.reloadData()
            }
            else{
                
            }//end else
            if self.workouts.count  == 0 {
                let alertController = UIAlertController(title: "Oops!", message: "No Favorite Programs were found please go to programs add programs to your favorites", preferredStyle: .Alert)
                let OKAction = UIAlertAction(title: "OK", style: .Default) { (action) in }
                alertController.addAction(OKAction)
                self.presentViewController(alertController, animated: true) { }
            }
        }//end first query
        wkoorprog = 1
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
    
    override func prefersStatusBarHidden() -> Bool {
        return true
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
