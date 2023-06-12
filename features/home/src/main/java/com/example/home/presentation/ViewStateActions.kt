package com.example.core

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import com.example.home.databinding.PartResultBinding

fun BaseFragment.setSimpleViewStatusVisibility(root: ViewGroup, state: ViewState) {
    val binding = PartResultBinding.bind(root)
    this.setViewStatusVisibility(
        root = root,
        state = state,
        onSuccess = {
            root.children
                .filter { it.id != com.example.home.R.id.progressBar && it.id != com.example.home.R.id.errorContainer }
                .forEach { it.visibility = View.VISIBLE }
        },
        onError = {
            binding.errorContainer.visibility = View.VISIBLE
            binding.errorText.text = getString(com.example.home.R.string.error_result)
        },
        onLoading = {
            binding.progressBar.visibility = View.VISIBLE
        }
    )
}

fun BaseFragment.onTryAgain(root: View, onTryAgainPressed: () -> Unit) {
    root.findViewById<Button>(com.example.home.R.id.tryAgainButton).setOnClickListener {
        onTryAgainPressed()
    }
}