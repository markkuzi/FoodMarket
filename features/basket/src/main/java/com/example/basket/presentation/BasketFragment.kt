package com.example.basket.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.basket.R
import com.example.basket.presentation.adapter.BasketListAdapter
import com.example.basket.databinding.FragmentBasketBinding
import com.example.basket.domain.entity.BasketDish
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private val viewModel by viewModel<BasketViewModel>()
    private val basketListAdapter by lazy { BasketListAdapter(requireContext()) }
    private val binding by viewBinding(FragmentBasketBinding::bind)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBasket.adapter = basketListAdapter
        viewModel.basketList.observe(viewLifecycleOwner) {
            basketListAdapter.submitList(it)

            binding.addToBasketButtonDialog.text = String.format(
                getString(R.string.pay_button),
                getAllPrice(it))
            if (it.isEmpty())
                binding.addToBasketButtonDialog.visibility = View.GONE
            else
                binding.addToBasketButtonDialog.visibility = View.VISIBLE
        }

        basketListAdapter.onUpCountClickListener = {
            viewModel.upDishBasketCount(it)
        }

        basketListAdapter.onDownCountClickListener = {
            viewModel.downDishBasketCount(it)
        }

        binding.addToBasketButtonDialog.setOnClickListener {
            viewModel.clearBasket()
        }

    }
}
private fun getAllPrice(dishList: List<BasketDish>?): String {
    var sum = 0
    dishList?.forEach {
        sum += it.price
    }
    return sum.toString()
}