package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentSplashBinding

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    var name = ""
    private lateinit var binding : FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSplashBinding>(inflater,
            R.layout.fragment_splash,container,false)
        binding.onButton.setOnClickListener{view: View->
            if (binding.nameEdit.text.toString() == ""){
                val nudge = Toast.makeText(view.context, "Tell me who you are first.", Toast.LENGTH_SHORT)
                nudge.show()
            } else {
                addNickname(view)
                view.findNavController().navigate(R.id.action_splashFragment_to_gameFragment)
            }
        }
        return binding.root
    }

    private fun addNickname(view: View){
        name = binding.nameEdit.text.toString()
    }

}
