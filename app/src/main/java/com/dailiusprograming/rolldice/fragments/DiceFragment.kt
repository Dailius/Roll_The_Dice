package com.dailiusprograming.rolldice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.DiceFragmentBinding

class DiceFragment() : Fragment() {

//    companion object {
//        fun newInstance() = DiceFragment()
//    }

    private lateinit var viewModel: DiceViewModel
    private var diceFragmentBinding: DiceFragmentBinding? = null

    private val imageViews by lazy {
        diceFragmentBinding?.let {
            arrayOf<ImageView>(
                it.imgDice1,
                it.imgDice2,
                it.imgDice3,
                it.imgDice4,
                it.imgDice5
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DiceFragmentBinding.inflate(inflater, container, false)
        diceFragmentBinding = binding

        val viewTxtCount = binding.txtCount
        val viewTxtComb = binding.txtCombination

        viewModel = ViewModelProvider(this)
            .get(DiceViewModel::class.java)

        viewModel.diceAdd.observe(viewLifecycleOwner, {
            setDiceVisible(it, binding)
        })

        viewModel.diceCombinationName.observe(viewLifecycleOwner, {
            viewTxtComb.text = getString(it)
        })

        viewModel.randomDice.observe(viewLifecycleOwner, {
            updateDisplay(it)
        })

        viewModel.diceCalc.observe(viewLifecycleOwner, {
            viewTxtCount.text = it.toString()
        })

        binding.fab.setOnClickListener {
            viewModel.addDice()
        }

        binding.imgRedButton.setOnClickListener {
            viewModel.rollDice()
        }

        return binding.root
    }

//    private fun setPaddingToNormal(binding: DiceFragmentBinding) {
//        binding.txtCount.setPadding(0, 0, 0, 8)
//        binding.linearLayout2.setPadding(0, 0, 0, 50)
//    }

//    private fun setPaddingToHigh(binding: DiceFragmentBinding) {
//        binding.txtCount.setPadding(0, 0, 0, 0)
//        binding.linearLayout2.setPadding(0, 40, 0, 0)
//    }

    private fun setDiceVisible(count: Int?, binding: DiceFragmentBinding) {

        setDiceDefault(binding)

        if (count != null) {
            if (count >= 2) binding.imgDice2.isGone = false

            if (count >= 3) binding.imgDice3.isGone = false
            if (count >= 4) {
                binding.imgDice4.isGone = false
//                setPaddingToNormal(binding)
            }
            if (count >= 5) {
                binding.imgDice5.isGone = false
                binding.txtCombination.isGone = false
            }
        }
    }

    private fun setDiceDefault(binding: DiceFragmentBinding) {
        binding.imgDice1.isGone = false
        binding.txtCount.text = "0"
        binding.txtCombination.isInvisible = true
//        setPaddingToHigh(binding)
        binding.imgDice2.isGone = true
        binding.imgDice3.isGone = true
        binding.imgDice4.isGone = true
        binding.imgDice5.isGone = true
    }

    private fun updateDisplay(dice: IntArray) {
        for (i in 0 until (imageViews?.size ?: 0)) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.wp_dice1
                2 -> R.drawable.wp_dice2
                3 -> R.drawable.wp_dice3
                4 -> R.drawable.wp_dice4
                5 -> R.drawable.wp_dice5
                6 -> R.drawable.wp_dice6
                else -> R.drawable.ic_add
            }
            imageViews?.get(i)?.setImageResource(drawableId)
        }
    }

    override fun onDestroyView() {
        diceFragmentBinding = null
        super.onDestroyView()
    }


}