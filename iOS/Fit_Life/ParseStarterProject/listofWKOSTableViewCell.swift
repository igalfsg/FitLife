//
//  listofWKOSTableViewCell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class listofWKOSTableViewCell: UITableViewCell {
    @IBOutlet weak var pic: UIImageView!
    @IBOutlet weak var name_lbl: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
