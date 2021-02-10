package com.dailiusprograming.rolldice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dailiusprograming.rolldice.databinding.DiceFragmentBinding

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

        var count = 1
        setDiceVisible(count, binding)

        binding.fab.setOnClickListener{
            count += 1
            if (count > 5) count = 1
            setDiceVisible(count, binding)
        }

        binding.imgRedButton.setOnClickListener{

        }

//        binding.btnRoll.setOnClickListener {
////            val rollDice = DiceHelper.rollDice()
////            val data = DiceHelper.calculateDice(count, rollDice)
////            Toast.makeText(
////                context,
////                "rolled dice ${rollDice.size}, $data, ${rollDice[0]} ",
////                Toast.LENGTH_SHORT
////            ).show()
//
//
//        }





        return binding.root
    }

    private fun setPaddingToNormal(binding: DiceFragmentBinding) {
        binding.txtCount.setPadding(0, 0, 0, 8)
        binding.linearLayout2.setPadding(0, 0, 0, 50)
    }

    private fun setPaddingToHigh(binding: DiceFragmentBinding) {
        binding.txtCount.setPadding(0, 0, 0, 60)
        binding.linearLayout2.setPadding(0, 0, 0, 80)
    }

    private fun setDiceVisible(count: Int?, binding: DiceFragmentBinding) {

        setDiceDefault(binding)

        if (count != null) {
            if (count >= 2) binding.imgDice2.isGone = false

            if (count >= 3) binding.imgDice3.isGone = false
            if (count >= 4) {
                binding.imgDice4.isGone = false
                setPaddingToNormal(binding)
            }
            if (count >= 5) {
                binding.imgDice5.isGone = false
                binding.txtCombination.isGone = false
            }
        }
    }

    private fun setDiceDefault(binding: DiceFragmentBinding) {
        binding.imgDice1.isGone = false
        binding.txtCombination.isGone = true
        setPaddingToHigh(binding)
        binding.imgDice2.isGone = true
        binding.imgDice3.isGone = true
        binding.imgDice4.isGone = true
        binding.imgDice5.isGone = true
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