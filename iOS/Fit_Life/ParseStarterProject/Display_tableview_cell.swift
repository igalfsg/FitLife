//
//  Display_tableview_cell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/27/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class Display_tableview_cell: UITableViewCell {

    @IBOutlet weak var name_lbl: UILabel!
    @IBOutlet weak var circular_image: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
