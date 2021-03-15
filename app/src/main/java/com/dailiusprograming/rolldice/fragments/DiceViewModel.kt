package com.dailiusprograming.rolldice.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dailiusprograming.rolldice.data.DiceData
import com.dailiusprograming.rolldice.util.AppPreferences
import com.dailiusprograming.rolldice.util.DiceHelper

class DiceViewModel : ViewModel() {

    val diceCombinationName = MutableLiveData<Int>()
    val randomDice = MutableLiveData<IntArray>()
    val diceCalc = MutableLiveData<Int>()
    val diceAdd = MutableLiveData<Int>()
    val rDrawableDice = MutableLiveData<IntArray>()

    init {
        diceAdd.value = AppPreferences.diceNo
        randomDice.value = AppPreferences.diceRoll
        rDrawableDice.value = DiceData.drawableDiceData(AppPreferences.diceColor)
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun rollDice() {
        randomDice.value = DiceHelper.rollDice()
        AppPreferences.diceRoll = randomDice.value!!
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun addDice(diceNo: Int = 0) {
        diceAdd.value = if (diceNo != 0) diceNo else diceAdd.value?.let { DiceHelper.addDice(it) }
        AppPreferences.diceNo = diceAdd.value!!
        diceCombinationName.value = DiceHelper.evaluateDice(randomDice.value)
        diceCalc.value = DiceHelper.calculateDice(diceAdd.value, randomDice.value)
    }

    fun getDrawableDice(imgDrawableID: Int) {
        rDrawableDice.value = DiceData.drawableDiceData(imgDrawableID)
        AppPreferences.diceColor = imgDrawableID
    }
}