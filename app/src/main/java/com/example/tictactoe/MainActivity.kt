package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private var currentPlayer = 'X' // X goes first
    private var gameBoard = Array(3) { CharArray(3) { ' ' } }
    private var gameActive = true
    
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize UI components
        statusText = findViewById(R.id.statusText)
        playAgainButton = findViewById(R.id.playAgainButton)
        
        // Initialize button grid
        buttons = arrayOf(
            arrayOf(
                findViewById(R.id.button00),
                findViewById(R.id.button01),
                findViewById(R.id.button02)
            ),
            arrayOf(
                findViewById(R.id.button10),
                findViewById(R.id.button11),
                findViewById(R.id.button12)
            ),
            arrayOf(
                findViewById(R.id.button20),
                findViewById(R.id.button21),
                findViewById(R.id.button22)
            )
        )
        
        // Set click listeners for all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener {
                    onCellClick(i, j)
                }
            }
        }
        
        // Play again button
        playAgainButton.setOnClickListener {
            resetGame()
        }
        
        updateStatusText()
    }
    
    private fun onCellClick(row: Int, col: Int) {
        if (!gameActive || gameBoard[row][col] != ' ') {
            return
        }
        
        // Update game board
        gameBoard[row][col] = currentPlayer
        buttons[row][col].text = currentPlayer.toString()
        buttons[row][col].setTextColor(
            if (currentPlayer == 'X') 
                resources.getColor(R.color.x_color, theme)
            else 
                resources.getColor(R.color.o_color, theme)
        )
        buttons[row][col].isEnabled = false
        
        // Check for win or draw
        if (checkWinner()) {
            gameActive = false
            showGameEndDialog(getWinnerMessage())
            playAgainButton.visibility = View.VISIBLE
        } else if (isBoardFull()) {
            gameActive = false
            showGameEndDialog(getString(R.string.game_draw))
            playAgainButton.visibility = View.VISIBLE
        } else {
            // Switch player
            currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
            updateStatusText()
        }
    }
    
    private fun checkWinner(): Boolean {
        // Check rows
        for (i in 0..2) {
            if (gameBoard[i][0] != ' ' &&
                gameBoard[i][0] == gameBoard[i][1] &&
                gameBoard[i][1] == gameBoard[i][2]) {
                return true
            }
        }
        
        // Check columns
        for (j in 0..2) {
            if (gameBoard[0][j] != ' ' &&
                gameBoard[0][j] == gameBoard[1][j] &&
                gameBoard[1][j] == gameBoard[2][j]) {
                return true
            }
        }
        
        // Check diagonals
        if (gameBoard[0][0] != ' ' &&
            gameBoard[0][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[2][2]) {
            return true
        }
        
        if (gameBoard[0][2] != ' ' &&
            gameBoard[0][2] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[2][0]) {
            return true
        }
        
        return false
    }
    
    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (gameBoard[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }
    
    private fun getWinnerMessage(): String {
        return if (currentPlayer == 'X') {
            getString(R.string.player_x_wins)
        } else {
            getString(R.string.player_o_wins)
        }
    }
    
    private fun showGameEndDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
    
    private fun updateStatusText() {
        if (gameActive) {
            statusText.text = if (currentPlayer == 'X') {
                getString(R.string.player_x_turn)
            } else {
                getString(R.string.player_o_turn)
            }
        }
    }
    
    private fun resetGame() {
        // Reset game state
        currentPlayer = 'X'
        gameActive = true
        gameBoard = Array(3) { CharArray(3) { ' ' } }
        
        // Reset all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
                buttons[i][j].isEnabled = true
                buttons[i][j].setTextColor(resources.getColor(R.color.x_color, theme))
            }
        }
        
        // Hide play again button
        playAgainButton.visibility = View.GONE
        
        // Update status text
        updateStatusText()
    }
}

