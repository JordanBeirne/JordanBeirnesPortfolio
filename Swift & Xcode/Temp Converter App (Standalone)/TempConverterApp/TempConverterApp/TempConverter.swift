//
//  TempConverter.swift
//  TempConverterApp
//
//  Created by Beirne, Jordan on 12/9/25.
//

import Foundation

class TempConverter {
    public var temp: Int
    private var isF: Bool
    public var convertedTemp: Int
    
    init(temp: Int) {
        self.temp = temp
        self.isF = true
        self.convertedTemp = 0
    }
    
    convenience init() {
        self.init(temp: 0)
    }
    
    func convert(temp: Int, unit: String) -> Int {
        if(tempBelowAbsoluteZero(temp: temp, unit: unit)) {
            return -1000
        }
        if unit == "C" {
            self.convertedTemp = 5 * (temp - 32) / 9
            return self.convertedTemp
        }
        else {
            self.convertedTemp = (9 * temp) / 5 + 32
            return self.convertedTemp
        }
    }
    
    func setUnits(isF: Bool) {
        self.isF = isF
    }
    
    func getUnits() -> String {
        if(isF) {
            return "F"
        }
        else {
            return "C"
        }
    }
    
    func tempBelowAbsoluteZero(temp: Int, unit: String) -> Bool {
        return (temp < -454 && unit == "F" ) || (temp < -270 && unit == "C")
    }
}

enum Unit {
    case F;
    case C;
}
