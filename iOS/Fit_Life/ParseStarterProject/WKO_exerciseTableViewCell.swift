//
//  WKO_exerciseTableViewCell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit
import DLRadioButton

class WKO_exerciseTableViewCell: UITableViewCell {

    @IBOutlet weak var pic: UIImageView!
    @IBOutlet weak var name_lbl: UILabel!
    @IBOutlet weak var reps_lbl: UILabel!
    @IBOutlet weak var sets_lbl: UILabel!
    @IBOutlet weak var weight_lbl: UILabel!
    
    @IBOutlet weak var changeweight_btn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
