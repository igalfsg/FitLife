//
//  ChangeWeightViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/17/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse


class ChangeWeightViewController: UIViewController {
    var exercises: [String]?
    var weights: [Int]?
    var workout: String?
    @IBOutlet weak var weight_table: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
   
    
    
    
    //number of rows on the table
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        
        return self.exercises!.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.weight_table.dequeueReusableCellWithIdentifier("table_weights", forIndexPath: indexPath) as! changeWeightTableViewCell
        mycell.name_lbl.text = exercises![indexPath.row]
        mycell.img.image = UIImage(named: self.exercises![indexPath.row].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + ".jpg")
        if UIImage(named: self.exercises![indexPath.row].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + ".jpg") == nil{
            mycell.img.image = UIImage(named:"logo_icon.png")
        }
        mycell.weight_edittxt.placeholder = String(weights![indexPath.row])
        if mycell.weight_edittxt.delegate == nil{
            
        }
        print("hi")
        return mycell
    }
    
    override func prefersStatusBarHidden() -> Bool {
        return true
    }
    
    @IBAction func save_pressed(sender: AnyObject) {
        let user = PFUser.currentUser()!.username
        let weightquery = PFQuery(className: "my_wko")
        //weightquery.fromLocalDatastore()
        weightquery.whereKey("name", equalTo: self.workout!);
        weightquery.whereKey("user", equalTo: user!);
        weightquery.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            if object != nil {
                var i = 0
                while i < self.exercises?.count{
                    let currentCell = self.weight_table.cellForRowAtIndexPath(NSIndexPath(forRow: i, inSection: 0)) as! changeWeightTableViewCell
                    if currentCell.weight_edittxt.text! != ""{
                        object!["weight" + String(i)] = Int(currentCell.weight_edittxt.text!)
                        print(currentCell.weight_edittxt.text!)
                    }
                    else{
                        object!["weight" + String(i)] = self.weights![i]
                        print("array")
                    }
                    i += 1
                }//end while
                object?.saveInBackground()
            }//end if object
            else{
                let new_fav = PFObject(className: "my_wko")
                new_fav.setObject(user!, forKey: "user")
                new_fav.setObject(self.workout!, forKey: "name")
                var i = 0
                while i < self.exercises?.count{
                    let currentCell = self.weight_table.cellForRowAtIndexPath(NSIndexPath(forRow: i, inSection: 0)) as! changeWeightTableViewCell
                    if currentCell.weight_edittxt.text! != ""{
                        new_fav.setObject(Int(currentCell.weight_edittxt.text!)!, forKey: "weight" + String(i))
                        print(currentCell.weight_edittxt.text!)
                    }
                    else{
                        new_fav.setObject(self.weights![i], forKey: "weight" + String(i))
                        print("array")
                    }
                    i += 1
                }//end while
                new_fav.pinInBackground()
                new_fav.saveInBackgroundWithBlock {
                    (success: Bool?, error: NSError?) -> Void in
                        if error == nil{
                            //change buton
                                
                        }
                    }
                }//end if object == nil
                    
                    
                    
                })//end query

        self.dismissViewControllerAnimated(true, completion: {});
    }


    @IBAction func back_btn(sender: AnyObject) {
        self.dismissViewControllerAnimated(true, completion: {});
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
