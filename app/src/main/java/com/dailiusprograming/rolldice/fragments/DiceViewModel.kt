package com.dailiusprograming.rolldice.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dailiusprograming.rolldice.util.AppPreferences
import com.dailiusprograming.rolldice.util.DiceHelper

class DiceViewModel() : ViewModel() {

    val diceCombinationName = MutableLiveData<Int>()
    val randomDice = MutableLiveData<IntArray>()
    val diceCalc = MutableLiveData<Int>()
    val diceAdd = MutableLiveData<Int>()


    init {
        diceAdd.value = AppPreferences.diceNo
        randomDice.value = AppPreferences.diceRoll
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun rollDice() {
        randomDice.value = DiceHelper.rollDice()
        AppPreferences.diceRoll = randomDice.value!!
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun addDice() {
        diceAdd.value = diceAdd.value?.let { DiceHelper.addDice(it) }
        AppPreferences.diceNo = diceAdd.value!!
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

}