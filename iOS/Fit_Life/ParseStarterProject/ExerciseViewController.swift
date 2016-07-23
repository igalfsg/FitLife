//
//  ExerciseViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/17/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import Parse

class ExerciseViewController: UIViewController {
    @IBOutlet weak var image_play: UIImageView!
    @IBOutlet weak var explanation: UITextView!
    @IBOutlet weak var top_bar: UIView!
    @IBOutlet weak var top_title: UILabel!
    
    var exercise: String?
    var video_link = ""
    var details = ""
    var steps = 0
    var imagesListArray = [UIImage]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        top_title.text = exercise
        let weightquery = PFQuery(className: "Exercises")
        //weightquery.fromLocalDatastore()
        weightquery.whereKey("name", equalTo: self.exercise!);
        weightquery.getFirstObjectInBackgroundWithBlock({
            (object:PFObject?, error:NSError?)  in
            if error == nil {
                self.details += object!["description"] as! String
                self.video_link = object!["Video"] as! String
                self.steps = object!["steps"] as! Int
                var i = 0
                print(self.steps)
                while i <= self.steps{
                    let image  = UIImage(named: self.exercise!.lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + String(i) + ".jpg")
                    print(self.exercise!.lowercaseString.stringByReplacingOccurrencesOfString(" ", withString: "_", options: NSStringCompareOptions.LiteralSearch, range: nil) + String(i) + ".jpg")
                    if(image != nil){
                        self.imagesListArray.append(image!)
                    }
                    
                    print(image)
                    i += 1
                }// while
                self.image_play.animationImages = self.imagesListArray;
                self.image_play.animationDuration = 2.0
                self.image_play.startAnimating()
                self.explanation.text = self.details
            }//end if object
            else{
                print(error)
            }//end else
        })//end query
        
        self.top_bar.backgroundColor = UIColor(patternImage: UIImage(named: "header.png")!)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //video
    @IBAction func play_btn(sender: AnyObject) {
        
    }
    
    
    //back
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
