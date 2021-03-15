package com.dailiusprograming.rolldice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dailiusprograming.rolldice.R

class SettingsFragment : Fragment() {

    private val viewModel: DiceViewModel by activityViewModels()
    private lateinit var imgDiceColor: ImageView
    private lateinit var txtDiceNo: TextView
    private lateinit var btnLeftDiceColor: ImageButton
    private lateinit var btnRightDiceColor: ImageButton
    private lateinit var btnLeftDiceNo: ImageButton
    private lateinit var btnRightDiceNo: ImageButton
    private var drawableId = R.drawable.pp_dice1
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_settings, container, false)

        imgDiceColor = rootView.findViewById(R.id.imgDiceColor)
        txtDiceNo = rootView.findViewById(R.id.txtDiceNo)
        btnLeftDiceColor = rootView.findViewById(R.id.ibtLeftDiceColor)
        btnRightDiceColor = rootView.findViewById(R.id.ibtRightDiceColor)
        btnLeftDiceNo = rootView.findViewById(R.id.ibtLeftDiceNo)
        btnRightDiceNo = rootView.findViewById(R.id.ibtRightDiceNo)

        imgDiceColor.setImageResource(R.drawable.bp_dice1)
        txtDiceNo.text = "5"

        btnRightDiceColor.setOnClickListener { nextDiceColor() }
        btnLeftDiceColor.setOnClickListener { previousDiceColor() }
        btnRightDiceNo.setOnClickListener { nextDiceNo() }
        btnLeftDiceNo.setOnClickListener { previousDiceNo() }

        return rootView
    }

    private fun imageResource() {
        imgDiceColor.setImageResource(drawableId)
    }

    private fun nextDiceColor() {
        val arrayIndex = diceColorList().indexOfFirst { it == drawableId }
        drawableId = if (arrayIndex == diceColorList().size - 1)
            diceColorList()[0] else diceColorList()[arrayIndex + 1]
        imageResource()
    }

    private fun previousDiceColor() {
        val arrayIndex = diceColorList().indexOfFirst { it == drawableId }
        drawableId = if (arrayIndex == 0)
            diceColorList()[3] else diceColorList()[arrayIndex - 1]
        imageResource()
    }

    private fun nextDiceNo() {
        val diceNo = when (diceNo()) {
            5 -> 1
            else -> diceNo() + 1
        }
        txtDiceNo.text = diceNo.toString()
    }

    private fun diceNo(): Int {
        val txtNo: String = txtDiceNo.text.toString()
        return Integer.valueOf(txtNo)
    }

    private fun previousDiceNo() {
        val diceNo = when (diceNo()) {
            1 -> 5
            else -> diceNo() - 1
        }
        txtDiceNo.text = diceNo.toString()
    }

    private fun diceColorList(): IntArray {
        return intArrayOf(
            R.drawable.bp_dice1,
            R.drawable.gp_dice1,
            R.drawable.pp_dice1,
            R.drawable.rp_dice1
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)

        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { navController.navigateUp() }

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {
            viewModel.addDice(diceNo())
            navController.navigateUp()
        }
    }
}