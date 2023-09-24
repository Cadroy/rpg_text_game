package com.example.rpgchuchu

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yunusulucay.myapplication.R
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerHealthTextView: TextView
    private lateinit var enemyHealthTextView: TextView
    private lateinit var battleLogTextView: TextView
    private lateinit var attackButton: Button
    private lateinit var defendButton: Button
    private lateinit var healButton: Button

    private var playerHealth = 100
    private var enemyHealth = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerHealthTextView = findViewById(R.id.playerHealthTextView)
        enemyHealthTextView = findViewById(R.id.enemyHealthTextView)
        battleLogTextView = findViewById(R.id.battleLogTextView)
        attackButton = findViewById(R.id.attackButton)
        defendButton = findViewById(R.id.defendButton)
        healButton = findViewById(R.id.healButton)

        updateUI()

        attackButton.setOnClickListener {
            playerAttack()
            enemyAction()
        }

        defendButton.setOnClickListener {
            playerDefend()
            enemyAction()
        }

        healButton.setOnClickListener {
            playerHeal()
            enemyAction()
        }
    }

    private fun playerAttack() {
        val damage = Random().nextInt(10) + 10 // Random damage between 10 and 19
        enemyHealth -= damage
        appendToBattleLog("Player attacks for $damage damage.")
        updateUI()
    }

    private fun playerDefend() {
        // Implement player defense logic here
        // You can reduce incoming damage or have some other defensive effect
        appendToBattleLog("Player defends.")
        updateUI()
    }

    private fun playerHeal() {
        val healing = Random().nextInt(10) + 10 // Random healing between 10 and 19
        playerHealth += healing
        appendToBattleLog("Player heals for $healing HP.")
        updateUI()
    }

    private fun enemyAction() {
        when (Random().nextInt(3)) { // 0: Attack, 1: Defend, 2: Heal
            0 -> enemyAttack()
            1 -> enemyDefend()
            2 -> enemyHeal()
        }
    }

    private fun enemyAttack() {
        val damage = Random().nextInt(10) + 10 // Random damage between 10 and 19
        playerHealth -= damage
        appendToBattleLog("Enemy attacks for $damage damage.")
        updateUI()
    }

    private fun enemyDefend() {
        // Implement enemy defense logic here
        // You can reduce incoming damage or have some other defensive effect
        appendToBattleLog("Enemy defends.")
        updateUI()
    }

    private fun enemyHeal() {
        val healing = Random().nextInt(10) + 10 // Random healing between 10 and 19
        enemyHealth += healing
        appendToBattleLog("Enemy heals for $healing HP.")
        updateUI()
    }

    private fun appendToBattleLog(message: String) {
        battleLogTextView.append("\n$message")
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        playerHealthTextView.text = "Player HP: $playerHealth"
        enemyHealthTextView.text = "Enemy HP: $enemyHealth"

        if (playerHealth <= 0) {
            appendToBattleLog("Player has been defeated!")
            disableButtons()
        } else if (enemyHealth <= 0) {
            appendToBattleLog("Enemy has been defeated!")
            disableButtons()
        }
    }

    private fun disableButtons() {
        attackButton.isEnabled = false
        defendButton.isEnabled = false
        healButton.isEnabled = false
    }
}

