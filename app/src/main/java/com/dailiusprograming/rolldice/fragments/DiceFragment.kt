package com.dailiusprograming.rolldice.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dailiusprograming.rolldice.ANIM_TEXT_START
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.DiceFragmentBinding
import com.dailiusprograming.rolldice.util.ViewAnim

class DiceFragment : Fragment() {

    private lateinit var viewModel: DiceViewModel
    private var diceFragmentBinding: DiceFragmentBinding? = null
    private lateinit var viewTxtCount: TextView
    private lateinit var viewTxtComb: TextView
    private lateinit var ibtButton: ImageButton
    private var btnClicked: Boolean = false


    private val imageViews by lazy {
        diceFragmentBinding?.let {
            arrayOf(
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
        ibtButton = binding.ibtRedButton

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

        ibtButton.setOnClickListener {
            ViewAnim.animRedButton(ibtButton, "start", 0)
            ViewAnim.animTextViewStart(viewTxtComb)
            btnClicked = true
            rollDice()
        }
        return binding.root
    }

    private fun rollDice() {
        ObjectAnimator.ofFloat(viewTxtCount, "alpha", 1f, 0f)
            .apply {
                duration = ANIM_TEXT_START
                start()
            }.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    viewModel.rollDice()
                }
            })
    }

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
        imageViews?.get(1)?.isGone = true
        imageViews?.get(2)?.isGone = true
        imageViews?.get(3)?.isGone = true
        imageViews?.get(4)?.isGone = true
    }

    private fun updateDisplay(dice: IntArray) {
        var animDelay: Long = 0
        for (i in 0 until (imageViews?.size ?: 0)) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.gp_dice1
                2 -> R.drawable.gp_dice2
                3 -> R.drawable.gp_dice3
                4 -> R.drawable.gp_dice4
                5 -> R.drawable.gp_dice5
                6 -> R.drawable.gp_dice6
                else -> R.drawable.ic_add
            }
            animDelay += 150

            if (btnClicked) {
                imageViews?.get(i)?.let {
                    ViewAnim.animDice(it, drawableId, animDelay)
                }
            } else {
                imageViews?.get(i)?.setImageResource(drawableId)
            }
        }
        animTextAndButton(animDelay)
    }

    private fun animTextAndButton(animDelay: Long) {
        if (btnClicked) {
            ViewAnim.animTextViewEnd(viewTxtComb, animDelay)
            ViewAnim.animTextViewEnd(viewTxtCount, animDelay)
            ViewAnim.animRedButton(ibtButton, "end", animDelay)
            btnClicked = false
        }
    }

    override fun onDestroyView() {
        diceFragmentBinding = null
        super.onDestroyView()
    }

}
