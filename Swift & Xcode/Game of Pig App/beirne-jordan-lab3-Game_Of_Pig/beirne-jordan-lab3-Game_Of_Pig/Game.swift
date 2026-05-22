//
//  Game.swift
//  beirne-jordan-lab3-Game_Of_Pig
//
//  Created by Beirne, Jordan on 11/28/25.
//

import Foundation

class Game {
    var Player1Score: Int
    var Player2Score: Int
    var currentPlayer: Int
    var volatileScore: Int
    var diceRoll: Int
    
    init(firstPlayer: Int) {
        Player1Score = 0
        Player2Score = 0
        currentPlayer = firstPlayer
        volatileScore = 0
        diceRoll = 0
    }
    
    func holdButtonPressed()  {
        addScore(player: currentPlayer, points: volatileScore)
        changePlayer()
    }
    
    func rollButtonPressed() {
        let score : Int = Int.random(in: 1...6)
        diceRoll = score
        if (score == 1) {
            changePlayer()
        }
        else {
            volatileScore += score
        }
    }
    
    func changePlayer() {
        self.currentPlayer = 3 - self.currentPlayer
        volatileScore = 0
    }
    
    func getScore(player: Int) -> Int {
        switch player {
        case 1:
            return Player1Score
        case 2:
            return Player2Score
        default:
            return 0
        }
    }
    
    func addScore(player: Int, points: Int) {
        if (player == 1) {
            Player1Score += points
        }
        else {
            Player2Score += points
        }
    }
    
    func CheckForWinner(player: Int) -> Int {
        if (getScore(player: player) >= 100) {
            return player
        }
        else {
            return 0
        }
    }
    
    func checkWinner() -> Int {
        var scoreToWin : Int = 10
        if(Player1Score >= scoreToWin) {
            resetScores()
            return 1
        }
        else if (Player2Score >= scoreToWin) {
            resetScores()
            return 2
        }
        else {
            return 0
        }
    }
    
    func resetScores() {
        Player1Score = 0
        Player2Score = 0
    }
}
