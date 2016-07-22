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
    //let user = PFUser.currentUser()!.username
    var workouts = [String]()
    @IBOutlet weak var wkos_Btn: UIButton!
    @IBOutlet weak var prog_btn: UIButton!
    @IBOutlet weak var disp_table: UITableView!
    @IBOutlet weak var top_view: UIView!
    @IBOutlet weak var title_view: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title_view.text = "Favorites"
        let queryWorkoutTypes = PFQuery(className: "my_wko")
        //queryWorkoutTypes.fromLocalDatastore()
        //queryWorkoutTypes.whereKey("user", equalTo: user!);
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
        }//end first query
        
        self.top_view.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
        let currentDevice : UIDevice = UIDevice.currentDevice()
        if currentDevice.userInterfaceIdiom == UIUserInterfaceIdiom.Pad {
            self.disp_table.rowHeight = 100
        }
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
            self.presentViewController(viewController, animated: true, completion: nil)
        }
        else if wkoorprog == 1 {//programs
            //open storyboard
            let viewController: ListofWKOSViewController = self.storyboard?.instantiateViewControllerWithIdentifier("list_of_wko") as! ListofWKOSViewController
            viewController.program = workouts[indexPath.row]
            self.presentViewController(viewController, animated: true, completion: nil)
        }
        
        
    }

    
    
    //buttons
    @IBAction func Wokouts_btn(sender: AnyObject) {
    //workouts btn pressed
        wkos_Btn.backgroundColor = UIColor.magentaColor()
        prog_btn.backgroundColor = UIColor(red: 0.4, green: 1.0, blue: 0.2, alpha: 0.5)
        workouts = [String]()
        let queryWorkoutTypes = PFQuery(className: "my_wko")
        //queryWorkoutTypes.fromLocalDatastore()
        //queryWorkoutTypes.whereKey("user", equalTo: user!);
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
        }//end first query
        wkoorprog = 0
    }

    
    
    @IBAction func Proframs_btn(sender: AnyObject) {
        //programs btn pressed
        prog_btn.backgroundColor = UIColor.magentaColor()
        wkos_Btn.backgroundColor = UIColor(red: 0.4, green: 1.0, blue: 0.2, alpha: 0.5)
        workouts = [String]()
        let queryWorkoutTypes = PFQuery(className: "my_prog")
        //queryWorkoutTypes.fromLocalDatastore()
        //queryWorkoutTypes.whereKey("user", equalTo: user!);
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
        }//end first query
        wkoorprog = 1
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
