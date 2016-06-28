//
//  Search_list_blue_ViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/26/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

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
        searchController.searchResultsUpdater = self
        searchController.dimsBackgroundDuringPresentation = false
        definesPresentationContext = true
        disp_tableView.tableHeaderView = searchController.searchBar
        
        
        
        if thing == 1{//exercises
            
        }
        else if thing == 2{//programs
            let queryWorkoutTypes = PFQuery(className: "Workouts")
            //queryWorkoutTypes.fromLocalDatastore()
            queryWorkoutTypes.whereKey("type", equalTo: type!);
            queryWorkoutTypes.orderByAscending("name")
            queryWorkoutTypes.findObjectsInBackgroundWithBlock{
                (objects: [PFObject]?, error: NSError?) -> Void in
                if error == nil {
                    for object in objects!{
                        var count = 0
                        self.wko_Type.append(object["name"] as! String)
                        self.img_name.append(self.wko_Type[count].lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil))
                        count += 1
                        
                    }
                    
                    self.disp_tableView.reloadData()
                }
                else{
                    
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
        else if thing == 6{
            navbar.topItem!.title = "Select up to 10 Exercies"
        }
        else {
            
        }
        // Do any additional setup after loading the view.
    }

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
