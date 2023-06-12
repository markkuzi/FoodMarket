package com.example.categories.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.categories.R
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.presentation.adapter.DishListAdapter
import com.example.core.BaseFragment
import com.example.core.onTryAgain
import com.example.core.setSimpleViewStatusVisibility
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesFragment : BaseFragment(R.layout.fragment_categories) {

    private val viewModel by viewModel<CategoriesViewModel>()
    private val dishListAdapter by lazy { DishListAdapter() }
    private val binding by viewBinding(FragmentCategoriesBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(CATEGORY_ID)
        val name = arguments?.getString(CATEGORY_NAME)

        binding.rvDishes.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvDishes.adapter = dishListAdapter
        viewModel.allDishes.observe(viewLifecycleOwner) {
            dishListAdapter.submitList(it)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            setSimpleViewStatusVisibility(root = binding.root, state = it)
        }
        onTryAgain(root = binding.root) {
            viewModel.getDishes()
        }

    }

    companion object {
        private const val CATEGORY_ID = "categoryId"
        private const val CATEGORY_NAME = "categoryName"
    }
}