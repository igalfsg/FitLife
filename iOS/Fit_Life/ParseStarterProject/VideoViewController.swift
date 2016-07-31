//
//  VideoViewController.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/31/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class VideoViewController: UIViewController {
    var video_link: String?
    var youtube_link = "https://www.youtube.com/embed/"
    @IBOutlet weak var videoView: UIWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //hide dumbbar
        self.navigationController?.navigationBarHidden = false
        youtube_link += video_link!
        videoView.loadHTMLString("<iframe width=\"\(videoView.frame.width)\" height=\"\(videoView.frame.height)\" src=\"\(youtube_link)\" frameborder=\"0\" allowfullscreen></iframe>", baseURL: nil)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
