package com.dailiusprograming.rolldice.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.dailiusprograming.rolldice.ANIM_DICE_END
import com.dailiusprograming.rolldice.ANIM_DICE_START


class ViewAnim {

    companion object {
        fun animDice(
            imageView: ImageView,
            drawableId: Int,
            delayAnim: Long
        ) {

            val startAnimDiceX: ObjectAnimator = animView(
                imageView,
                "scaleX", 1f, 0f
            )
            val startAnimDiceY: ObjectAnimator = animView(
                imageView,
                "scaleY", 1f, 0f
            )
            val startRotateDice: ObjectAnimator = animView(
                imageView,
                "rotation", 0f, 180f
            )

            startAnimDiceX.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    imageView.setImageResource(drawableId)
                    super.onAnimationEnd(animation)
                }
            })

            val endAnimDiceX: ObjectAnimator = animView(
                imageView,
                "scaleX", 0f, 1f, ANIM_DICE_END, true
            )
            val endAnimDiceY: ObjectAnimator = animView(
                imageView,
                "scaleY", 0f, 1f, ANIM_DICE_END, true
            )
            val endRotateDice: ObjectAnimator = animView(
                imageView,
                "rotation", 0f, 180f, ANIM_DICE_END, true
            )

            AnimatorSet().apply {
                playTogether(startAnimDiceX, startAnimDiceY, startRotateDice)
                play(endAnimDiceX).after(startAnimDiceY)
                play(endAnimDiceX).with(endAnimDiceY)
                play(endAnimDiceX).with(endRotateDice)
                startDelay = delayAnim
                start()
            }

        }

        fun animTextViewEnd(textView: TextView, delayAnim: Long) {
            val animate: ObjectAnimator = animView(
                textView, "alpha", 0f, 1f, 1000
            )
            AnimatorSet().apply {
                play(animate)
                startDelay = delayAnim
                start()
            }
        }

        fun animTextViewStart(textView: TextView) {
            val animate: ObjectAnimator = animView(
                textView, "alpha", 1f, 0f, 0
            )
            AnimatorSet().apply {
                play(animate)
                start()
            }
        }

        private fun animView(
            imageView: View,
            ptyName: String,
            valueStart: Float,
            valueEnd: Float,
            durationTime: Long = ANIM_DICE_START,
            interPolator: Boolean = false
        ): ObjectAnimator {

            return ObjectAnimator.ofFloat(
                imageView,
                ptyName,
                valueStart, valueEnd
            ).apply {
                duration = durationTime
                if (interPolator) interpolator = BounceInterpolator()
            }
        }

    }


}