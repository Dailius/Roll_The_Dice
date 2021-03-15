package com.dailiusprograming.rolldice.data
import com.dailiusprograming.rolldice.R

class DiceData ()  {

    companion object{
        fun drawableDiceData (imgDrawableID: Int): IntArray{
            return when (imgDrawableID){
                R.drawable.bp_dice6 -> blue()
                R.drawable.gp_dice6 -> green()
                R.drawable.pp_dice6 -> pink()
                R.drawable.rp_dice6 -> red()
                else -> blue()
            }
        }

        private fun blue(): IntArray{
            return intArrayOf(
                R.drawable.bp_dice1,
                R.drawable.bp_dice2,
                R.drawable.bp_dice3,
                R.drawable.bp_dice4,
                R.drawable.bp_dice5,
                R.drawable.bp_dice6
            )
        }
        private fun green(): IntArray{
            return intArrayOf(
                R.drawable.gp_dice1,
                R.drawable.gp_dice2,
                R.drawable.gp_dice3,
                R.drawable.gp_dice4,
                R.drawable.gp_dice5,
                R.drawable.gp_dice6
            )
        }
        private fun pink(): IntArray{
            return intArrayOf(
                R.drawable.pp_dice1,
                R.drawable.pp_dice2,
                R.drawable.pp_dice3,
                R.drawable.pp_dice4,
                R.drawable.pp_dice5,
                R.drawable.pp_dice6
            )
        }
        private fun red(): IntArray{
            return intArrayOf(
                R.drawable.rp_dice1,
                R.drawable.rp_dice2,
                R.drawable.rp_dice3,
                R.drawable.rp_dice4,
                R.drawable.rp_dice5,
                R.drawable.rp_dice6
            )
        }
    }
}


