package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity for the Tic Tac Toe game.
 * 
 * This activity manages a two-player Tic Tac Toe game where players take turns
 * placing X and O marks on a 3x3 grid. The game detects wins and draws, and
 * provides a play again functionality.
 */
class MainActivity : AppCompatActivity() {
    
    companion object {
        private const val BOARD_SIZE = 3
        private const val PLAYER_X = 'X'
        private const val PLAYER_O = 'O'
        private const val EMPTY_CELL = ' '
    }
    
    private var activePlayer = PLAYER_X // X goes first
    private var gameBoard = Array(BOARD_SIZE) { CharArray(BOARD_SIZE) { EMPTY_CELL } }
    private var isGameActive = true
    
    private lateinit var gameButtons: Array<Array<Button>>
    private lateinit var statusTextView: TextView
    private lateinit var playAgainBtn: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initializeViews()
        setupButtonListeners()
        updateStatusDisplay()
    }
    
    private fun initializeViews() {
        statusTextView = findViewById(R.id.statusText)
        playAgainBtn = findViewById(R.id.playAgainButton)
        
        // Initialize button grid
        gameButtons = arrayOf(
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
    }
    
    private fun setupButtonListeners() {
        // Set click listeners for all game buttons
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                gameButtons[row][col].setOnClickListener {
                    handleCellClick(row, col)
                }
            }
        }
        
        // Play again button listener
        playAgainBtn.setOnClickListener {
            resetGame()
        }
    }
    
    /**
     * Handles a cell click on the game board.
     * 
     * @param row The row index of the clicked cell (0-2)
     * @param col The column index of the clicked cell (0-2)
     */
    private fun handleCellClick(row: Int, col: Int) {
        if (!isGameActive || gameBoard[row][col] != EMPTY_CELL) {
            return
        }
        
        // Update game board and UI
        gameBoard[row][col] = activePlayer
        updateButtonDisplay(row, col)
        
        // Check game state
        when {
            hasWinner() -> {
                endGameWithWinner()
            }
            isBoardFull() -> {
                endGameWithDraw()
            }
            else -> {
                switchPlayer()
                updateStatusDisplay()
            }
        }
    }
    
    private fun updateButtonDisplay(row: Int, col: Int) {
        val button = gameButtons[row][col]
        button.text = activePlayer.toString()
        button.setTextColor(
            resources.getColor(
                if (activePlayer == PLAYER_X) R.color.x_color else R.color.o_color,
                theme
            )
        )
        button.isEnabled = false
    }
    
    /**
     * Checks if there is a winner on the current board.
     * 
     * @return true if a player has three marks in a row (horizontal, vertical, or diagonal)
     */
    private fun hasWinner(): Boolean {
        // Check rows
        for (row in 0 until BOARD_SIZE) {
            if (isWinningLine(
                    gameBoard[row][0],
                    gameBoard[row][1],
                    gameBoard[row][2]
                )
            ) {
                return true
            }
        }
        
        // Check columns
        for (col in 0 until BOARD_SIZE) {
            if (isWinningLine(
                    gameBoard[0][col],
                    gameBoard[1][col],
                    gameBoard[2][col]
                )
            ) {
                return true
            }
        }
        
        // Check main diagonal (top-left to bottom-right)
        if (isWinningLine(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2])) {
            return true
        }
        
        // Check anti-diagonal (top-right to bottom-left)
        if (isWinningLine(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0])) {
            return true
        }
        
        return false
    }
    
    /**
     * Checks if three cells form a winning line (all same player and not empty).
     * 
     * @param cell1 First cell in the line
     * @param cell2 Second cell in the line
     * @param cell3 Third cell in the line
     * @return true if all three cells have the same non-empty value
     */
    private fun isWinningLine(cell1: Char, cell2: Char, cell3: Char): Boolean {
        return cell1 != EMPTY_CELL && cell1 == cell2 && cell2 == cell3
    }
    
    private fun isBoardFull(): Boolean {
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                if (gameBoard[row][col] == EMPTY_CELL) {
                    return false
                }
            }
        }
        return true
    }
    
    private fun endGameWithWinner() {
        isGameActive = false
        val winnerMessage = if (activePlayer == PLAYER_X) {
            getString(R.string.player_x_wins)
        } else {
            getString(R.string.player_o_wins)
        }
        showGameEndDialog(winnerMessage)
        playAgainBtn.visibility = View.VISIBLE
    }
    
    private fun endGameWithDraw() {
        isGameActive = false
        showGameEndDialog(getString(R.string.game_draw))
        playAgainBtn.visibility = View.VISIBLE
    }
    
    private fun switchPlayer() {
        activePlayer = if (activePlayer == PLAYER_X) PLAYER_O else PLAYER_X
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
    
    private fun updateStatusDisplay() {
        if (isGameActive) {
            statusTextView.text = if (activePlayer == PLAYER_X) {
                getString(R.string.player_x_turn)
            } else {
                getString(R.string.player_o_turn)
            }
        }
    }
    
    private fun resetGame() {
        // Reset game state
        activePlayer = PLAYER_X
        isGameActive = true
        gameBoard = Array(BOARD_SIZE) { CharArray(BOARD_SIZE) { EMPTY_CELL } }
        
        // Reset all buttons
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                val button = gameButtons[row][col]
                button.text = ""
                button.isEnabled = true
                button.setTextColor(resources.getColor(R.color.x_color, theme))
            }
        }
        
        // Hide play again button
        playAgainBtn.visibility = View.GONE
        
        // Update status display
        updateStatusDisplay()
    }
}

