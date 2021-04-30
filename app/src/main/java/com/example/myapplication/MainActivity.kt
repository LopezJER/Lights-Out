/**
 * This version of Lights Out is encapsulated into three fragments: the splash screen, the game screen, and the end prompt.
 * A navigation host fragment is situated within the main activity, allowing users to swap between the three fragments depending
 * on their input. The number of moves in the congratsFragment is updated from the gameFragment via an interface implemented in
 * the main activity.
 *
 *Initial Test successful: buttons and score working and updating as expected 
 *
 * @author Jose Enrique R. Lopez
 * @version 1.0
*/



package com.example.myapplication

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), GameFragment.GameFragmentListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onUpdateMoves(moveCount: Int){
        CongratsFragment.updateMoves(moveCount)
    }

}


