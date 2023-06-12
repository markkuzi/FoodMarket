package com.example.home.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.presentation.adapter.CategoryListAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val categoryListAdapter by lazy { CategoryListAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCategories: RecyclerView = view.findViewById(R.id.rvCategory)

        rvCategories.adapter =categoryListAdapter

        rvCategories.setOnClickListener {
            findNavController().navigate(Uri.parse("foodmarket://categories"))
        }


    }
}
