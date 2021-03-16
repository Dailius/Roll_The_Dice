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
import com.dailiusprograming.rolldice.DiceViewModel
import com.dailiusprograming.rolldice.R
import com.dailiusprograming.rolldice.databinding.FragmentSettingsBinding
import com.dailiusprograming.rolldice.util.AppPreferences

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DiceViewModel by activityViewModels()
    private lateinit var imgDiceColor: ImageView
    private lateinit var txtDiceNo: TextView
    private lateinit var btnLeftDiceColor: ImageButton
    private lateinit var btnRightDiceColor: ImageButton
    private lateinit var btnLeftDiceNo: ImageButton
    private lateinit var btnRightDiceNo: ImageButton
    private var drawableId = R.drawable.bp_dice1
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        imgDiceColor = binding.imgDiceColor
        txtDiceNo = binding.txtDiceNo
        btnLeftDiceColor = binding.ibtLeftDiceColor
        btnRightDiceColor = binding.ibtRightDiceColor
        btnLeftDiceNo = binding.ibtLeftDiceNo
        btnRightDiceNo = binding.ibtRightDiceNo

        btnRightDiceColor.setOnClickListener { nextDiceColor() }
        btnLeftDiceColor.setOnClickListener { previousDiceColor() }
        btnRightDiceNo.setOnClickListener { nextDiceNo() }
        btnLeftDiceNo.setOnClickListener { previousDiceNo() }

        drawableId = AppPreferences.diceColor
        if (drawableId == 0){drawableId = R.drawable.bp_dice6}
        imageResource()

        txtDiceNo.text = AppPreferences.diceNo.toString()

        return binding.root
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
            R.drawable.bp_dice6,
            R.drawable.gp_dice6,
            R.drawable.pp_dice6,
            R.drawable.rp_dice6
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)

        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { navController.navigateUp() }

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {
            viewModel.getDrawableDice(drawableId)
            viewModel.addDice(diceNo())
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}