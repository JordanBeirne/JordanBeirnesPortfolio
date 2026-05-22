//
//  ViewController.swift
//  beirne-jordan-lab3-Game_Of_Pig
//
//  Created by Beirne, Jordan on 11/28/25.
//

import UIKit

class ViewController: UIViewController {
    
    var game: Game!
    @IBOutlet weak var DiceImage: UIImageView!
    
    @IBOutlet weak var P1ScoreBar: UIProgressView!
    @IBOutlet weak var P1Score: UITextField!
    
    @IBOutlet weak var P2ScoreBar: UIProgressView!
    @IBOutlet weak var P2Score: UITextField!

    @IBOutlet weak var CurrentPlayer: UILabel!

    @IBOutlet weak var RoundScore: UILabel!
    
    @IBOutlet weak var NewGameButton: UIButton!
    
    @IBOutlet weak var RollButton: UIButton!
    
    @IBOutlet weak var HoldButton: UIButton!
    
    var startingPlayer : Int = 2
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        RollButton.isEnabled = false
        HoldButton.isEnabled = false
    }

    
    @IBAction func NewGame(_ sender: Any) {
        if(game != nil) {
            NewGameButton.isEnabled = false
            RollButton.isEnabled = true
            HoldButton.isEnabled = false
        }
        else {
            game = Game(firstPlayer: 3 - startingPlayer)
            game.resetScores()
            NewGameButton.isEnabled = false
            RollButton.isEnabled = true
            
        }
        updateLabels()
    }
    
    
    @IBAction func RollButtonPressed(_ sender: Any) {
        game.rollButtonPressed()
        HoldButton.isEnabled = true
        updateLabels()
        if(game.diceRoll == 1) {
            HoldButton.isEnabled = false
            RollButton.isEnabled = false
            CurrentPlayer.text = "Player \(3 - game.currentPlayer) loses turn!"
            RoundScore.text = "Switching to Player \(game.currentPlayer)"
            NewGameButton.setTitle( "Continue", for: .normal)
            NewGameButton.isEnabled = true
        }
        
    }
    
    
    @IBAction func HoldButtonPressed(_ sender: Any) {
        game.holdButtonPressed()
        HoldButton.isEnabled = false
        updateLabels()
    }
    
    func updateLabels() {
        
        P1Score.text = "\(game.Player1Score)"
        P1ScoreBar.progress = Float(game.Player1Score) / Float(100)
        
        P2Score.text = "\(game.Player2Score)"
        P2ScoreBar.progress = Float(game.Player2Score) / Float(100)
        
        DiceImage.image = UIImage(named: "dice\(game.diceRoll)")
        
        CurrentPlayer.text = "Current Player: \(game.currentPlayer)"
        RoundScore.text = "Round Score:  \(game.volatileScore)"
        var winner : Int = game.checkWinner()
        if(winner != 0) {
            CurrentPlayer.text = "Winner is Player \(winner)!"
            RoundScore.text = "Congratulations"
            NewGameButton.isEnabled = true
            NewGameButton.setTitle( "New Game", for: .normal)
            RollButton.isEnabled = false
            HoldButton.isEnabled = false
        }
    }

}

