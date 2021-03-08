package com.dailiusprograming.rolldice.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import com.dailiusprograming.rolldice.ANIM_DICE_END
import com.dailiusprograming.rolldice.ANIM_DICE_START


class ViewAnim {

    companion object {
        fun animDice(imageView: ImageView, drawableId: Int, delayAnim: Long) {

            val startAnimDiceX: ObjectAnimator = animDice(imageView,
                "scaleX", 1f, 0f)
            val startAnimDiceY: ObjectAnimator = animDice(imageView,
                "scaleY", 1f, 0f)
            val startRotateDice: ObjectAnimator = animDice(imageView,
                "rotation", 0f, 180f)

            startAnimDiceX.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    imageView.setImageResource(drawableId)
                    super.onAnimationEnd(animation)
                }
            })

            val endAnimDiceX: ObjectAnimator = animDice(imageView,
            "scaleX", 0f, 1f, ANIM_DICE_END, true)
            val endAnimDiceY: ObjectAnimator = animDice(imageView,
                "scaleY", 0f, 1f, ANIM_DICE_END, true)
            val endRotateDice: ObjectAnimator = animDice(imageView,
                "rotation", 0f, 180f, ANIM_DICE_END, true)

            AnimatorSet().apply {
                playTogether(startAnimDiceX, startAnimDiceY, startRotateDice)
                play(endAnimDiceX).after(startAnimDiceY)
                play(endAnimDiceX).with(endAnimDiceY)
                play(endAnimDiceX).with(endRotateDice)
                startDelay = delayAnim
                start()
            }

        }

        private fun animDice(
            imageView: ImageView,
            ptyName: String,
            valueStart: Float,
            valueEnd: Float,
            milSeconds: Long = ANIM_DICE_START,
            interPolator: Boolean = false): ObjectAnimator {

            return ObjectAnimator.ofFloat(
                imageView,
                ptyName,
                valueStart, valueEnd
            ).apply {
                duration = milSeconds
                if (interPolator) interpolator = BounceInterpolator()
            }
        }

    }




}