//
//  Workout_types_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/20/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse


class Workout_types_ViewController: UIViewController, UITableViewDelegate {
    
    //dont forget to also drag the data source and the delegate of the table to the view controller
    @IBOutlet weak var workouts_tableview: UITableView!
    
    var wko_Type = [String]()
    var img_name = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // get workout types from local data store
        let queryWorkoutTypes = PFQuery(className: "Wk_Types")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("type", equalTo: 3);
        queryWorkoutTypes.orderByAscending("name")
        queryWorkoutTypes.findObjectsInBackgroundWithBlock{
            (objects: [PFObject]?, error: NSError?) -> Void in
            if error == nil {
                for object in objects!{
                    //print(object);
                    self.wko_Type.append(object["name"] as! String)
                    self.img_name.append(object["img_name"] as! String)
                }
                print(self.wko_Type)
            }
            else{
                //get it from the server
                let queryWorkoutTypes = PFQuery(className: "Wk_Types")
                queryWorkoutTypes.whereKey("type", equalTo: 3);
                queryWorkoutTypes.orderByAscending("name")
                queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                    (objects: [PFObject]?, error: NSError?) -> Void in
                    if error == nil {
                        for object in objects!{
                            //print(object);
                            self.wko_Type.append(object["name"] as! String)
                            self.img_name.append(object["img_name"] as! String)
                        }
                        //print(self.wko_Type)
                    }
                    else{
                        
                    }
                }
            }
        }
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //number of rows on the table
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        print(self.wko_Type.count)
        return 7 //self.wko_Type.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.workouts_tableview.dequeueReusableCellWithIdentifier("workoutCell", forIndexPath: indexPath) as! TypeTableViewCell
        mycell.typelable.text = wko_Type[indexPath.row]
        mycell.imagecell.image = UIImage(named: "check.png")
        return mycell
    }
    /*
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
    }
*/
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
