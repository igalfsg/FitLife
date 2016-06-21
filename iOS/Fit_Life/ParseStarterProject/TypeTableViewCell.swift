//
//  TypeTableViewCell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 6/20/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class TypeTableViewCell: UITableViewCell {

    @IBOutlet weak var imagecell: UIView!
    @IBOutlet weak var typelable: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
