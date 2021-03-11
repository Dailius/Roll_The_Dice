package com.dailiusprograming.rolldice.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.dailiusprograming.rolldice.R
import kotlinx.android.synthetic.main.fragment_settings_dialog.*


class SettingsDialog : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_settings_dialog, container, false);

        val btnCancel  = rootView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener{ dismiss()}

        return rootView
    }

    companion object {

    }
}

