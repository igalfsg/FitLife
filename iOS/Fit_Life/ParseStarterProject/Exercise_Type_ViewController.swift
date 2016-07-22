//
//  Exercise_Type_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/27/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse


class Exercise_Type_ViewController: UIViewController {

    //dont forget to also drag the data source and the delegate of the table to the view controller
    @IBOutlet weak var Exercise_tableView: UITableView!
    @IBOutlet weak var top_view: UIView!

    
    var wko_Type = [String]()
    var img_name = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // get workout types from local data store
        let queryWorkoutTypes = PFQuery(className: "Wk_Types")
        //queryWorkoutTypes.fromLocalDatastore()
        queryWorkoutTypes.whereKey("type", equalTo: 1);
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
                self.Exercise_tableView.reloadData()
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
                        self.Exercise_tableView.reloadData()
                        //print(self.wko_Type)
                    }
                    else{
                        
                    }
                }//end second query
            }//end else
        }//end first query
        
        self.top_view.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
        let currentDevice : UIDevice = UIDevice.currentDevice()
        if currentDevice.userInterfaceIdiom == UIUserInterfaceIdiom.Pad {
            self.Exercise_tableView.rowHeight = 220
        }
    }//end view did load
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //number of rows on the table
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        
        return self.wko_Type.count
    }
    
    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.Exercise_tableView.dequeueReusableCellWithIdentifier("exercise_cell_type", forIndexPath: indexPath) as! extercise_type_cell
        mycell.type_lbl.text = wko_Type[indexPath.row]
        mycell.img_lbl.image = UIImage(named: img_name[indexPath.row] + ".jpg")
        return mycell
    }
    
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
        //open storyboard Search_List_control
        let viewController: Search_list_blue_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Search_List_control") as! Search_list_blue_ViewController
        viewController.type = wko_Type[indexPath.row]
        viewController.thing = 1
        self.presentViewController(viewController, animated: true, completion: nil)
        
    }

}
