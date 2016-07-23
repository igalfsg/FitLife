//
//  changeWeightTableViewCell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/17/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class changeWeightTableViewCell: UITableViewCell {

    
    @IBOutlet weak var img: UIImageView!
    @IBOutlet weak var name_lbl: UILabel!
    @IBOutlet weak var weight_edittxt: UITextField!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
