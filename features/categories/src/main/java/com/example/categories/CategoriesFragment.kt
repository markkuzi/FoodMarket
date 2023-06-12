package com.example.categories

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(CATEGORY_ID)
        val name = arguments?.getString(CATEGORY_NAME)
        val text:TextView = view.findViewById(R.id.text)
        text.text = name

    }

    companion object {
        private const val CATEGORY_ID = "categoryId"
        private const val CATEGORY_NAME = "categoryName"
    }
}