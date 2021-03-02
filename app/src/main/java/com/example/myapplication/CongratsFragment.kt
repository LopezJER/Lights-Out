package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentCongratsBinding
import com.example.myapplication.databinding.FragmentSplashBinding

/**
 * A simple [Fragment] subclass.
 */
class CongratsFragment : Fragment() {
    private lateinit var binding: FragmentCongratsBinding
    companion object{
        private var finalMoves = 0
        fun updateMoves(moveCount : Int){
            finalMoves = moveCount
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentCongratsBinding>(inflater,
            R.layout.fragment_congrats,container,false)
        binding.scoreText.text = finalMoves.toString();
        binding.button.setOnClickListener{view: View->
            view.findNavController().navigate(R.id.action_congratsFragment_to_splashFragment)
        }
        return binding.root
    }



}
