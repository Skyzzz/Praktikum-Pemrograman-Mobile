package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceImage1: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        diceImage1.setImageResource(R.drawable.empty_dice)
        diceImage2.setImageResource(R.drawable.empty_dice)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()

        val diceImage1: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        val drawableResource1 = when (diceRoll1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage1.setImageResource(drawableResource1)
        diceImage2.setImageResource(drawableResource2)

        diceImage1.contentDescription = diceRoll1.toString()
        diceImage2.contentDescription = diceRoll2.toString()

        if (diceRoll1 != diceRoll2) {
            Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Selamat anda dapat dadu double!", Toast.LENGTH_SHORT).show()
        }
    }
}


class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}