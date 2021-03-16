package com.dailiusprograming.rolldice.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dailiusprograming.rolldice.ANIM_TEXT_START
import com.dailiusprograming.rolldice.DiceViewModel
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.FragmentDiceBinding
import com.dailiusprograming.rolldice.util.ViewAnim

class DiceFragment : Fragment() {

    private val viewModel: DiceViewModel by activityViewModels()

    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewTxtCount: TextView
    private lateinit var viewTxtComb: TextView
    private lateinit var ibtButton: ImageButton
    private lateinit var lnrLayout3: LinearLayout
    private var btnClicked: Boolean = false

    private lateinit var rDrawableDice: IntArray
    private lateinit var imageViews: Array<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)

        imageViews = binding.let {
            arrayOf(
                it.imgDice1,
                it.imgDice2,
                it.imgDice3,
                it.imgDice4,
                it.imgDice5
            )
        }

        viewTxtCount = binding.txtCount
        viewTxtComb = binding.txtCombination
        ibtButton = binding.ibtRedButton
        lnrLayout3 = binding.linearLayout3

        viewModel.diceAdd.observe(viewLifecycleOwner, {
            setDiceVisible(it)
        })

        viewModel.rDrawableDice.observe(viewLifecycleOwner, {
            rDrawableDice = it
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

    private fun setDiceVisible(diceNo: Int? = 1) {

        setDiceDefault()

        if (diceNo != null) {
            if (diceNo >= 2) imageViews[1].isGone = false

            if (diceNo >= 3) {
                lnrLayout3.isGone = false
                imageViews[2].isGone = false
            }
            if (diceNo >= 4) {
                imageViews[3].isGone = false
            }
            if (diceNo >= 5) {
                imageViews[4].isGone = false
                viewTxtComb.isGone = false
            }
        }
    }

    private fun setDiceDefault() {
        imageViews[0].isGone = false
        viewTxtCount.text = "0"
        viewTxtComb.isGone = true
        imageViews[1].isGone = true
        imageViews[2].isGone = true
        lnrLayout3.isGone = true
        imageViews[3].isGone = true
        imageViews[4].isGone = true
    }

    private fun updateDisplay(dice: IntArray) {
        var animDelay: Long = 0
        for (i in imageViews.indices) {
            val drawableId = when (dice[i]) {
                1 -> rDrawableDice[0]
                2 -> rDrawableDice[1]
                3 -> rDrawableDice[2]
                4 -> rDrawableDice[3]
                5 -> rDrawableDice[4]
                6 -> rDrawableDice[5]
                else -> R.drawable.ic_add
            }
            animDelay += 150

            if (btnClicked) {
                imageViews[i].let {
                    ViewAnim.animDice(it, drawableId, animDelay)
                }
            } else {
                imageViews[i].setImageResource(drawableId)
            }
        }
        animDelay = 1200
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
        _binding = null
        super.onDestroyView()
    }
}
