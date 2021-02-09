package com.dailiusprograming.rolldice.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.DiceFragmentBinding
import com.dailiusprograming.rolldice.util.DiceHelper

class DiceFragment : Fragment() {

//    companion object {
//        fun newInstance() = DiceFragment()
//    }

    private lateinit var viewModel: DiceViewModel
    private var diceFragmentBinding: DiceFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DiceFragmentBinding.inflate(inflater, container, false)
        diceFragmentBinding = binding

        binding.btnRoll.setOnClickListener {
            val rollDice = DiceHelper.rollDice()
            val data = DiceHelper.calculateDice(3, rollDice)
            Toast.makeText(
                context,
                "rolled dice ${rollDice.size}, $data, ${rollDice[0]} ",
                Toast.LENGTH_SHORT
            ).show()

        }



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiceViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        diceFragmentBinding = null
        super.onDestroyView()
    }

}