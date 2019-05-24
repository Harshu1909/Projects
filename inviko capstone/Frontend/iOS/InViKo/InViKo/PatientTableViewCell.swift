//
//  PatientTableViewCell.swift
//  InViKo
//
//  Created by Student on 2019-03-27.
//  Copyright © 2019 Student. All rights reserved.
//

import UIKit

class PatientTableViewCell: UITableViewCell {

    @IBOutlet weak var lbFname: UILabel!
    @IBOutlet weak var lbPhone: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
