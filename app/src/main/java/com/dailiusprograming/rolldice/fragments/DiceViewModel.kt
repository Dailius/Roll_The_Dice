package com.dailiusprograming.rolldice.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.util.DiceHelper

class DiceViewModel() : ViewModel() {

    val diceCombinationName = MutableLiveData<Int>()
    val randomDice = MutableLiveData<IntArray>()
    val diceCalc = MutableLiveData<Int>()
    val diceAdd = MutableLiveData<Int>()


    init {
        diceCombinationName.value = R.string.welcome
        randomDice.value = intArrayOf(1, 1, 1, 1, 1)
        diceAdd.value = 1
    }

    fun rollDice() {
        randomDice.value = DiceHelper.rollDice()
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun addDice() {
        diceAdd.value = diceAdd.value?.plus(1)
        if (diceAdd.value!! > 5) diceAdd.value = 1
    }


}