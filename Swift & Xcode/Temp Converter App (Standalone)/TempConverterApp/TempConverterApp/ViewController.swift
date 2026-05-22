//
//  ViewController.swift
//  TempConverterApp
//
//  Created by Beirne, Jordan on 12/9/25.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate, UITextViewDelegate {
    
    @IBOutlet weak var tempLabel: UITextField!
    
    @IBOutlet weak var unitLabel: UITextField!
    
    @IBOutlet weak var tempInput: UITextView!
    
    @IBOutlet weak var isFSwitch: UISwitch!
    
    var tempInstance: TempConverter!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tempInstance = TempConverter()
        tempInput.keyboardType = .numberPad
        self.tempInput.delegate = self
    }
    
    @IBAction func convertButton(_ sender: Any) {
        let userData: String = tempInput.text
        if userData == "" {
            tempInstance.temp = -5000
        }
        if let temp = Int(userData) {
            tempLabel.text = String(tempInstance.convert(temp: temp, unit: tempInstance.getUnits()))
        }
    }
    
    @IBAction func unitsToggle(_ sender: Any) {
        tempInstance.setUnits(isF: isFSwitch.isOn)
        unitLabel.text = tempInstance.getUnits()
        convertButton((Any).self)
        updateLabels()
    }
    
    func updateLabels() {
        tempLabel.text = String(tempInstance.convertedTemp)
        unitLabel.text = "\(tempInstance.getUnits())"
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
     tempInput.resignFirstResponder()
     return true
    }
}

