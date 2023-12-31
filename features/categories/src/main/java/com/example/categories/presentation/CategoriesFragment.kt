package com.example.categories.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.categories.R
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.domain.entity.BasketDish
import com.example.categories.presentation.adapter.chips.ChipListAdapter
import com.example.categories.presentation.adapter.dishes.DishListAdapter
import com.example.core.BaseFragment
import com.example.core.onTryAgain
import com.example.core.setSimpleViewStatusVisibility
import com.example.toolbar.domain.entity.ToolbarSettings
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesFragment : BaseFragment(R.layout.fragment_categories) {

    private val viewModel by viewModel<CategoriesViewModel>()
    private val dishListAdapter by lazy { DishListAdapter() }
    private val chipListAdapter by lazy { ChipListAdapter() }
    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getString(CATEGORY_ID)
        val name = arguments?.getString(CATEGORY_NAME)
        viewModel.setupCategoriesToolbar(ToolbarSettings(name.toString(), true))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.rvDishes.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvDishes.adapter = dishListAdapter
        viewModel.allDishes.observe(viewLifecycleOwner) {
            dishListAdapter.submitList(it)
        }
        dishListAdapter.onDishItemClickListener = {
            showDishDialog(it)
        }

        binding.rvChips.adapter = chipListAdapter
        viewModel.tags.observe(viewLifecycleOwner) {
            chipListAdapter.submitList(it)
        }
        chipListAdapter.onChipItemClickListener = {
            viewModel.changeTagsSelected(it)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            setSimpleViewStatusVisibility(root = binding.root, state = it)
        }
        onTryAgain(root = binding.root) {
            viewModel.getDishes()
        }
    }

    private fun showDishDialog(dish: DishesUi) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(layoutInflater.inflate(R.layout.dish_dialog, null))
        dialog.window?.setBackgroundDrawableResource(R.drawable.dish_item_background)
        val image: ImageView = dialog.findViewById(R.id.dishImageDialog)
        val name = dialog.findViewById<TextView>(R.id.dishNameDialog)
        val price: TextView = dialog.findViewById(R.id.dishPriceDialog)
        val weight: TextView = dialog.findViewById(R.id.dishWeighDialog)
        val description: TextView = dialog.findViewById(R.id.dishDescriptionDialog)
        val addToBasket: Button = dialog.findViewById(R.id.addToBasketButtonDialog)
        val closeDialog: ImageButton = dialog.findViewById(R.id.closeButton)

        if (dish.imageUrl.isNotBlank()) {
            Picasso.get().load(dish.imageUrl).into(image)
        } else
            Picasso.get().load(dish.description).into(image)
        name.text = dish.name
        price.text = String.format(
            getString(R.string.price),
            dish.price.toString()
        )

        weight.text = String.format(
            getString(R.string.weight),
            dish.weight.toString()
        )

        description.text = dish.description
        dialog.show()

        addToBasket.setOnClickListener {
            viewModel.insertDishToBasket(
                BasketDish(
                    id = dish.id,
                    name = dish.name,
                    price = dish.price,
                    weight = dish.weight,
                    image = dish.imageUrl,
                    count = 1
                )
            )
            dialog.hide()
        }

        closeDialog.setOnClickListener {
            dialog.hide()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setupCategoriesToolbar(ToolbarSettings("", false))
    }

    companion object {
        private const val CATEGORY_ID = "categoryId"
        private const val CATEGORY_NAME = "categoryName"
    }
}