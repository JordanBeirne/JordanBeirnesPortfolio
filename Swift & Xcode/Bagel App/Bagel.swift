//
//  Bagel.swift
//  beirne_jordan_Lab1
//
//  Created by Beirne, Jordan on 10/21/25.
//

/*
 Jordan Beirne
 22 October 2025
 CSC 496
 Lab 1
 Pico Fermi Bagel
 */

var secretNumber: String = ""
var stillPlaying: Bool = true
var currentGuess: String = ""

//Purpose: Create secret number
private func generateNumber() -> String {
    let number = Int.random(in: 1...1000)
    return String(number)
}

//Purpose: Validate that the input guess is valid, and retry input if rejected. Call the method to check the answer when a valid input is received
private func processGuess() {
    print("Guess a thee-digit number: ")
    if let input = readLine() {
        if(input != nil && input.count == 3) {
            currentGuess = input
            
        }
        else {
            print("Invalid input.\n")
            processGuess()
        }
    }
}

//Purpose: Compare the input to the secret number and produce hint
private func checkAnswer() {
    var splitGuess = currentGuess.map { String($0) }
    var splitSecret = secretNumber.map { String($0) }
    var correctCounter = [0, 0, 0] //reset counter
    for (index, String) in currentGuess.enumerated() {
        //check for misplaced correct member / Pico
        for i in 0...index {
            if(splitGuess[index] == splitSecret[i]) {
                correctCounter[index] = 1
            }
        }
        //check for exact matches / Fermi
        if(splitGuess[index] == splitSecret[index]) {
            correctCounter[index] = 2
        }
    }
    if (correctCounter == [2, 2, 2]) {
        stillPlaying = false
    }
    else if (correctCounter == [0, 0, 0]) {
        print("Bagels")
    }
    else {
        var result: String = ""
        for i in correctCounter {
            if (i == 1) {
                result += "Pico "
            }
            else if (i == 2) {
                result += "Fermi "
            }
        }
        print("\(result)")
    }
}
    
    //Purpose: Explain the rules and handle repitition of other methods
public func playGame() {
    secretNumber = generateNumber()
    print("Your goal is to guess the three-digit number. You will receive hints after each guess. \n Fermi -> Correct number, correct placement \n Pico  -> Correct number, wrong placement \n Bagel -> All numbers incorrect\n")
    repeat {
        processGuess()
        checkAnswer()
    } while(stillPlaying)
    print("The correct number was \(secretNumber). You got it!")
}
    

