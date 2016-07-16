//
//  Search_list_blue_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/26/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse
import QuartzCore

class Search_list_blue_ViewController: UIViewController {

    
    @IBOutlet weak var navbar: UINavigationBar!
    @IBOutlet weak var disp_tableView: UITableView!
    let searchController = UISearchController(searchResultsController: nil)
    var type: String?
    var thing: Int?
    var wko_Type = [String]()
    var img_name = [String]()
    var filtered_stuff = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navbar.topItem!.title = type
        //search stuff
        searchController.searchResultsUpdater = self
        searchController.dimsBackgroundDuringPresentation = false
        definesPresentationContext = true
        disp_tableView.tableHeaderView = searchController.searchBar
        
        
        
        if thing == 1{//exercises
            let queryWorkoutTypes = PFQuery(className: "Exercises")
            //queryWorkoutTypes.fromLocalDatastore()
            if type == "All Exercises"{
                queryWorkoutTypes.whereKeyExists("img_name")
                queryWorkoutTypes.whereKey("img_name", notEqualTo: "")
                queryWorkoutTypes.whereKey("Type", notEqualTo: "Prog")
                //queryWorkoutTypes.whereNotEqualTo("Type", "Prog");
            }
            else{
                queryWorkoutTypes.whereKey("Type", equalTo: type!);
            }
            queryWorkoutTypes.limit = 1000
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    var count = 0
                    for object in objects!{
                        
                        self.wko_Type.append(object["name"] as! String)
                        self.img_name.append(self.wko_Type[count].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil))
                        count += 1
                    }
                    //print(self.wko_Type)
                    self.disp_tableView.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "Exercises")
                    //queryWorkoutTypes.fromLocalDatastore()
                    if self.type == "All Exercises"{
                        queryWorkoutTypes.whereKeyExists("img_name")
                        queryWorkoutTypes.whereKey("img_name", notEqualTo: "")
                        queryWorkoutTypes.whereKey("Type", notEqualTo: "Prog")
                        //queryWorkoutTypes.whereNotEqualTo("Type", "Prog");
                    }
                    else{
                        queryWorkoutTypes.whereKey("Type", equalTo: self.type!);
                    }
                    queryWorkoutTypes.limit = 1000
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            var count = 0
                            for object in objects!{
                                
                                self.wko_Type.append(object["name"] as! String)
                                self.img_name.append(self.wko_Type[count].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil))
                                count += 1
                            }
                            //print(self.wko_Type)
                            self.disp_tableView.reloadData()
                        }
                    }//end second query
                }//end else
            }//end first query
        }
        else if thing == 2{//programs
            let queryWorkoutTypes = PFQuery(className: "Programs")
            //queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKey("type", equalTo: type!);
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    for object in objects!{
                    
                        self.wko_Type.append(object["name"] as! String)
                        
                    }
                    self.disp_tableView.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "Programs")
                    //queryWorkoutTypes.fromLocalDatastore()
                    queryWorkoutTypes.whereKey("type", equalTo: self.type!);
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            for object in objects!{
                                
                                self.wko_Type.append(object["name"] as! String)
                                
                            }
                            self.disp_tableView.reloadData()
                        }
                        else{
                            
                        }//end else
                    }//end second query
                }//end else
            }//end first query
            let queryWorkoutTypes1 = PFQuery(className: "Programs")
            //queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes1.whereKey("extra", equalTo: type!);
            queryWorkoutTypes1.orderByAscending("name")
            queryWorkoutTypes1.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    for object in objects!{
                        
                        self.wko_Type.append(object["name"] as! String)
                        
                    }
                    self.disp_tableView.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "Programs")
                    //queryWorkoutTypes.fromLocalDatastore()
                    queryWorkoutTypes.whereKey("extra", equalTo: self.type!);
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            for object in objects!{
                                
                                self.wko_Type.append(object["name"] as! String)
                                
                            }
                            self.disp_tableView.reloadData()
                        }
                        else{
                            
                        }//end else
                    }//end second query
                }//end else
            }//end first query
        }//end if
        else if thing == 3{//wkos
            let queryWorkoutTypes = PFQuery(className: "Workouts")
            //queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKey("type", equalTo: type!);
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    
                    for object in objects!{
                        self.wko_Type.append(object["name"] as! String)
                        //self.img_name.append(self.wko_Type[count].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil))
                        
                        
                    }
                    
                    
                    self.disp_tableView.reloadData()
                }
                else{
                    let queryWorkoutTypes = PFQuery(className: "Workouts")
                    //queryWorkoutTypes.fromLocalDatastore()
                    queryWorkoutTypes.whereKey("type", equalTo: self.type!);
                    queryWorkoutTypes.orderByAscending("name")
                    queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                        (objects: [PFObject]?, error: NSError?) -> Void in
                        if error == nil {
                            
                            for object in objects!{
                                self.wko_Type.append(object["name"] as! String)
                            }
                            
                            self.disp_tableView.reloadData()
                        }
                        
                    }//end second query
                }//end else
            }//end first query
        }//end if
            /*
        else if thing == 6{
            navbar.topItem!.title = "Select up to 10 Exercies"
            let queryWorkoutTypes = PFQuery(className: "Exercises")
            //queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKeyExists("img_name")
            queryWorkoutTypes.limit = 1000
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    var count = 0
                    for object in objects!{
                        //display exercises
                        self.wko_Type.append(object["name"] as! String)
                        self.img_name.append(self.wko_Type[count].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil))
                        count += 1
                    }
                    //print(self.wko_Type)
                    self.disp_tableView.reloadData()
                }
                else{
                    
                }//end else
            }//end first query
            
        }//end else if thing == 6
        */
        
    } //end view did load

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //number of rows on the table
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        if searchController.active && searchController.searchBar.text != "" {
            return filtered_stuff.count
        }
        return self.wko_Type.count
    }
    
    

    //populatin each cell
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let mycell =  self.disp_tableView.dequeueReusableCellWithIdentifier("disp_cell", forIndexPath: indexPath) as! Display_tableview_cell
        if searchController.active && searchController.searchBar.text != "" {//searching stuff
            mycell.name_lbl.text = filtered_stuff[indexPath.row]
            if thing == 1{
                mycell.circular_image.image = UIImage(named: self.filtered_stuff[indexPath.row].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + ".jpg")
            }
            else{
                mycell.circular_image.image = UIImage(named:"logo_icon.png")
            }
            
        }
        else{
            mycell.name_lbl.text = wko_Type[indexPath.row]
            if thing == 1{
                mycell.circular_image.image = UIImage(named: img_name[indexPath.row] + ".jpg")
            }
            else{
                mycell.circular_image.image = UIImage(named:"logo_icon.png")
            }
        }
        
        
        
        return mycell
    }
    
    
    //on click event
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
        //open storyboard 
        if thing == 1 {
            let viewController: Search_list_blue_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Search_List_control") as! Search_list_blue_ViewController
            viewController.type = wko_Type[indexPath.row]
            viewController.thing = 1
            self.presentViewController(viewController, animated: true, completion: nil)
        }
        else if thing == 2 {
        let viewController: ListofWKOSViewController = self.storyboard?.instantiateViewControllerWithIdentifier("list_of_wko") as! ListofWKOSViewController
            if searchController.active && searchController.searchBar.text != "" {
                viewController.program = filtered_stuff[indexPath.row]
            }
            else{
                viewController.program = wko_Type[indexPath.row]
            }
        self.presentViewController(viewController, animated: true, completion: nil)
        }
        else if thing == 3 {
            let viewController: Search_list_blue_ViewController = self.storyboard?.instantiateViewControllerWithIdentifier("Search_List_control") as! Search_list_blue_ViewController
            viewController.type = wko_Type[indexPath.row]
            viewController.thing = 1
            self.presentViewController(viewController, animated: true, completion: nil)
        }
        
        
        
    }

    //search
    func filterContentForSearchText(searchText: String, scope: String = "All") {
        filtered_stuff = wko_Type.filter { candy in
            return candy.lowercaseString.containsString(searchText.lowercaseString)
        }
        
        disp_tableView.reloadData()
    }
    //back button
    @IBAction func Back_b(sender: AnyObject) {
        self.dismissViewControllerAnimated(true, completion: {});
        
    }
    
   

}//end class

extension Search_list_blue_ViewController: UISearchResultsUpdating {
    func updateSearchResultsForSearchController(searchController: UISearchController) {
        filterContentForSearchText(searchController.searchBar.text!)
    }
}


