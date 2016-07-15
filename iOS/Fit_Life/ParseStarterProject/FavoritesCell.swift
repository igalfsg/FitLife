//
//  FavoritesCell.swift
//  ParseStarterProject-Swift
//
//  Created by Igal on 7/14/16.
//  Copyright © 2016 Parse. All rights reserved.
//

import UIKit

class FavoritesCell: UITableViewCell {

    
 
    @IBOutlet weak var img_fav: UIImageView!
    @IBOutlet weak var name_fav: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
