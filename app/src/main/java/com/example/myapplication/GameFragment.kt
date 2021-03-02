package com.example.myapplication


import android.R.attr.data
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentCongratsBinding
import com.example.myapplication.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 */

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var listener : GameFragmentListener

    var moveCount = 0
    var clickableTiles: Array<Array<TextView>> = arrayOf(arrayOf());

    interface GameFragmentListener {
        abstract fun onUpdateMoves(moveCount: Int)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )

        setListeners(binding)
        return binding.root
    }

    override fun onAttach (context: Context){
        super.onAttach(context);
        if (context is GameFragmentListener)
            listener = context
        else {
            throw RuntimeException(
                "$context must implement GameFragmentListener")

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setListeners(binding: FragmentGameBinding) {
        //show stats
        binding.apply {
            clickableTiles = arrayOf(
                arrayOf(tile1, tile2, tile3, tile4, tile5),
                arrayOf(tile6, tile7, tile8, tile9, tile10),
                arrayOf(tile11, tile12, tile13, tile14, tile15),
                arrayOf(tile16, tile17, tile18, tile19, tile20),
                arrayOf(tile21, tile22, tile23, tile24, tile25)
            )

        }

        for (row in clickableTiles) {
            for (tile in row) {
                tile.setOnClickListener {view : View->
                    moveCount++;
                    activateTiles(tile);
                    checkWin(view)
                 }

            }
        }

        binding.restartButton.setOnClickListener{
            restartGrid()
        }

    }

    private fun checkWin(view: View){
        for (i in 0..4) {
            for (j in 0..4) {
                if (clickableTiles[i][j].text == "X") //return;
                    return
            }
        }
        updateMoveCount()
        view.findNavController().navigate(R.id.action_gameFragment_to_congratsFragment)
    }

    private fun updateMoveCount() {
        listener.onUpdateMoves(moveCount)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun restartGrid(){
        val vibe: Vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val effect: VibrationEffect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibe.vibrate(effect)

        for (row in clickableTiles) {
            for (tile in row) {
                tile.setBackgroundResource(R.drawable.back)
                tile.setTextColor(
                    ContextCompat.getColor(
                        tile.context,
                        android.R.color.black
                    )
                )
                tile.text = "X"
            }
        }
        moveCount = 0
    }
    private fun activateTiles(view: View) {
        for (i in 0..4) {
            var found = false;
            for (j in 0..4) {
                if (view.id == clickableTiles[i][j].id) {

                    found = true;

                    val offSets: Array<Array<Int>> = arrayOf(
                        arrayOf(i, j),
                        arrayOf(i - 1, j),
                        arrayOf(i + 1, j),
                        arrayOf(i, j - 1),
                        arrayOf(i, j + 1)
                    )
                    for (pair: Array<Int> in offSets) {
                        if (pair[0] < 0 || pair[0] > 4 || pair[1] < 0 || pair[1] > 4) {
                            continue
                        } else {
                            if (clickableTiles[pair[0]][pair[1]].text.equals("O")) {
                                clickableTiles[pair[0]][pair[1]].setBackgroundResource(R.drawable.back)
                                clickableTiles[pair[0]][pair[1]].setTextColor(
                                    ContextCompat.getColor(
                                        clickableTiles[pair[0]][pair[1]].context,
                                        android.R.color.black
                                    )
                                )
                                clickableTiles[pair[0]][pair[1]].text = "X"
                            } else {
                                clickableTiles[pair[0]][pair[1]].setBackgroundResource(android.R.color.white)
                                clickableTiles[pair[0]][pair[1]].setTextColor(
                                    ContextCompat.getColor(
                                        clickableTiles[pair[0]][pair[1]].context,
                                        android.R.color.white
                                    )
                                )
                                clickableTiles[pair[0]][pair[1]].text = "O"
                            }
                        }
                    }
                }
            }
            if (found) {
                break;
            }
        }

    }

}

