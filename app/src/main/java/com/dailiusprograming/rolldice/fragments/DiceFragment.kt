package com.dailiusprograming.rolldice.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.DiceFragmentBinding

class DiceFragment() : Fragment() {

    private lateinit var viewModel: DiceViewModel
    private var diceFragmentBinding: DiceFragmentBinding? = null
    private lateinit var viewTxtCount: TextView
    private lateinit var viewTxtComb: TextView

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

        viewTxtCount = binding.txtCount
        viewTxtComb = binding.txtCombination

        viewModel = ViewModelProvider(this)
            .get(DiceViewModel::class.java)

        viewModel.diceAdd.observe(viewLifecycleOwner, {
            setDiceVisible(it)
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

    private fun setDiceVisible(count: Int?) {

        setDiceDefault()

        if (count != null) {
            if (count >= 2) imageViews?.get(1)?.isGone = false

            if (count >= 3) imageViews?.get(2)?.isGone = false
            if (count >= 4) {
                imageViews?.get(3)?.isGone = false
//                setPaddingToNormal(binding)
            }
            if (count >= 5) {
                imageViews?.get(4)?.isGone = false
                viewTxtComb.isGone = false
            }
        }
    }

    private fun setDiceDefault() {
        imageViews?.get(0)?.isGone = false
        viewTxtCount.text = "0"
        viewTxtComb.isInvisible = true
//        setPaddingToHigh(binding)
        imageViews?.get(1)?.isGone = true
        imageViews?.get(2)?.isGone = true
        imageViews?.get(3)?.isGone = true
        imageViews?.get(4)?.isGone = true


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

            val startAnimDiceX = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "scaleX", 1f, 0f
            ).apply { duration = 500 }

            val startAnimDiceY = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "scaleY", 1f, 0f
            ).apply { duration = 500 }

            val startRotateDice = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "rotation", 0f, 360f
            ).apply { duration = 500 }

            startAnimDiceX.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    imageViews?.get(i)?.setImageResource(drawableId)
                    super.onAnimationEnd(animation)
                }
            })
            val endRotateDice = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "rotation", 0f, 360f
            ).apply { duration = 500 }

            val endAnimDiceX = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "scaleX", 0f, 1f
            ).apply { duration = 500 }

            val endAnimDiceY = ObjectAnimator.ofFloat(
                imageViews?.get(i),
                "scaleY", 0f, 1f
            ).apply { duration = 500 }


            val setAnimDice = AnimatorSet().apply {
                playTogether(startAnimDiceX, startAnimDiceY, startRotateDice)
                play(endAnimDiceX).after(startAnimDiceY)
                play(endAnimDiceX).with(endAnimDiceY)
                play(endAnimDiceX).with(endRotateDice)
                startDelay=100
                start()
            }


        }
    }

    override fun onDestroyView() {
        diceFragmentBinding = null
        super.onDestroyView()
    }
}
