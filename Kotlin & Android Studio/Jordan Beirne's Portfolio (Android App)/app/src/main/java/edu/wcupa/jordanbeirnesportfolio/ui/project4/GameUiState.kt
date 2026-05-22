package edu.wcupa.jordanbeirnesportfolio.ui.project4

data class GameUiState (
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val isGameOver: Boolean = false,
    val hint: String = ""
)