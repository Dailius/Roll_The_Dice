package com.dailiusprograming.rolldice.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.dailiusprograming.rolldice.R


class SettingsDialog : DialogFragment() {

    private lateinit var imgDiceColor: ImageView
    private lateinit var txtDiceNo: TextView
    private lateinit var btnLeftDiceColor: ImageButton
    private lateinit var btnRightDiceColor: ImageButton
    private lateinit var btnLeftDiceNo: ImageButton
    private lateinit var btnRightDiceNo: ImageButton
    private var drawableId = R.drawable.pp_dice1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_settings_dialog, container, false);

        imgDiceColor = rootView.findViewById<ImageView>(R.id.imgDiceColor)
        txtDiceNo = rootView.findViewById<TextView>(R.id.txtDiceNo)
        btnLeftDiceColor = rootView.findViewById<ImageButton>(R.id.ibtLeftDiceColor)
        btnRightDiceColor = rootView.findViewById<ImageButton>(R.id.ibtRightDiceColor)
        btnLeftDiceNo = rootView.findViewById<ImageButton>(R.id.ibtLeftDiceNo)
        btnRightDiceNo = rootView.findViewById<ImageButton>(R.id.ibtRightDiceNo)

        imgDiceColor.setImageResource(R.drawable.bp_dice1)
        txtDiceNo.text = "5"

        val btnCancel  = rootView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener{ dismiss()}

        val btnOk  = rootView.findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener{ SaveData()}

        btnRightDiceColor.setOnClickListener{nextDiceColor()}
        btnLeftDiceColor.setOnClickListener{previousDiceColor()}
        btnRightDiceNo.setOnClickListener{nextDiceNo()}
        btnLeftDiceNo.setOnClickListener{previousDiceNo()}

        return rootView
    }
    private fun imageResource(){
        imgDiceColor.setImageResource(drawableId)
    }

    private fun nextDiceColor(){
        val arrayIndex = diceColorList().indexOfFirst { it == drawableId }
        drawableId = if (arrayIndex == diceColorList().size-1 )
            diceColorList()[0] else diceColorList()[arrayIndex+1]
         imageResource()
    }

    private fun previousDiceColor(){
        val arrayIndex = diceColorList().indexOfFirst { it == drawableId }
        drawableId = if (arrayIndex == 0 )
            diceColorList()[3] else diceColorList()[arrayIndex-1]
        imageResource()
    }

    private fun nextDiceNo(){
        val diceNo = when(diceNo()){
            5 -> 1
            else -> diceNo() + 1
        }
        txtDiceNo.text = diceNo.toString()
    }

    private fun diceNo():Int{
        val txtNo: String = txtDiceNo.text.toString()
        return Integer.valueOf(txtNo)
    }

    private fun previousDiceNo(){
        val diceNo = when(diceNo()){
            1 -> 5
            else -> diceNo() - 1
        }
        txtDiceNo.text = diceNo.toString()
    }

    private fun diceColorList(): IntArray{
        return intArrayOf(
            R.drawable.bp_dice1,
            R.drawable.gp_dice1,
            R.drawable.pp_dice1,
            R.drawable.rp_dice1
        )
    }

    fun SaveData(){

    }



}

