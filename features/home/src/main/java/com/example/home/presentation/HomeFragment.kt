package com.example.home.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.BaseFragment
import com.example.core.ViewState
import com.example.core.onTryAgain
import com.example.core.setSimpleViewStatusVisibility
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.presentation.adapter.CategoryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()
    private val categoryListAdapter by lazy { CategoryListAdapter() }
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCategory.adapter = categoryListAdapter
        viewModel.allCategories.observe(viewLifecycleOwner) {
            categoryListAdapter.submitList(it)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            setSimpleViewStatusVisibility(root = binding.root, state = it)
        }
        onTryAgain(root = binding.root) {
            viewModel.getCategory()
        }

        categoryListAdapter.onCategoryItemClickListener = {
            findNavController().navigate(Uri.parse("foodmarket://categories/${it.id}/${it.name}"))
        }

    }
}
