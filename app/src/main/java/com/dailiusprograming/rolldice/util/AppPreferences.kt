package com.dailiusprograming.rolldice.util

import android.content.Context
import android.content.SharedPreferences
import com.dailiusprograming.rolldice.DICE_TYPE_WHITE
import com.dailiusprograming.rolldice.PREF_NAME

object AppPreferences {
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables

    private val DICE_NO = Pair("dice_no", 1)
    private val DICE_TYPE = Pair("dice_type", DICE_TYPE_WHITE)
    private val DICE_ROLL = Pair("dice_roll", intArrayOf(1, 1, 1, 1, 1).contentToString())


    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    //SharedPreferences variables getters/setters
    var diceNo: Int
        get() = preferences.getInt(DICE_NO.first, DICE_NO.second)
        set(value) = preferences.edit {
            it.putInt(DICE_NO.first, value)
        }

    var diceType: String
        get() = preferences.getString(DICE_TYPE.first, DICE_TYPE.second) ?: ""
        set(value) = preferences.edit {
            it.putString(DICE_TYPE.first, value)
        }

    var diceRoll: IntArray
        get() = preferences.getString(DICE_ROLL.first, DICE_ROLL.second)
            ?.removeSurrounding("[","]")?.replace(" ","")
            ?.split(",")?.map{it.toInt()}?.toIntArray()
            ?: intArrayOf(2, 3, 4, 5, 6)
        set(value) = preferences.edit {
            it.putString(DICE_ROLL.first, value.contentToString())
        }

}