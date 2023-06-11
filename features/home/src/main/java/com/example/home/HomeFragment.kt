package com.example.home

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view.findViewById<TextView>(R.id.textview)
        text.setOnClickListener {
            findNavController().navigate(Uri.parse("foodmarket://categories"))
        }

    }
}
