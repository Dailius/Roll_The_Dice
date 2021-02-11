package com.dailiusprograming.rolldice.util

import com.dailiusprograming.rolldice.R
import kotlin.random.Random

class DiceHelper {

    companion object {

        private fun getDice(): Int {
            return Random.nextInt(1, 7)
        }

        fun rollDice(): IntArray {
            return intArrayOf(
                getDice(),
                getDice(),
                getDice(),
                getDice(),
                getDice()
            )
        }

        fun calculateDice(count: Int?, dice: IntArray?): Int {
            var calculate: Int = 0
            if (count != null) {
                for (i in 0 until count) {
                    calculate += dice?.get(i) ?: 1
                }
            }
            return calculate
        }

        fun evaluateDice(dice: IntArray?): Int {

            // Initialize a map of die counts
            val result = mutableMapOf(
                Pair(1, 0),
                Pair(2, 0),
                Pair(3, 0),
                Pair(4, 0),
                Pair(5, 0),
                Pair(6, 0)
            )

            // Update the die counts for each of 5 dice
            for (i in 0 until dice!!.size) {
                val currentCount = result.getOrElse(dice[i]) { 0 }
                result[dice[i]] = currentCount + 1
            }

            // Check possible outcomes in order of best roll
            return when {
                result.containsValue(5) ->
                    R.string.five_of_a_kind
                result.containsValue(4) ->
                    R.string.four_of_a_kind
                isFullHouse(result) ->
                    R.string.full_house
                isStraight(dice) ->
                    R.string.straight
                result.containsValue(3) ->
                    R.string.three_of_a_kind
                is2Pairs(result.values) ->
                    R.string.two_pairs
                result.containsValue(2) ->
                    R.string.pair
                else ->
                    R.string.nothing_special
            }

        }

        private fun isFullHouse(result: MutableMap<Int, Int>): Boolean {
            return result.containsValue(3) && result.containsValue(2)
        }

        private fun is2Pairs(values: MutableCollection<Int>): Boolean {
            var foundPair = false
            for (value in values) {
                if (value == 2) {
                    if (foundPair) return true else foundPair = true
                }
            }
            return false
        }

        private fun isStraight(dice: IntArray): Boolean {
            return (dice.contains(1) || dice.contains(6)) &&
                    dice.contains(2) &&
                    dice.contains(3) &&
                    dice.contains(4) &&
                    dice.contains(5)
        }
    }
}