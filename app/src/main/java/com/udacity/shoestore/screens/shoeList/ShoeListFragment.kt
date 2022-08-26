package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list,container,false)
        binding.shoeDetailButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        viewModel.shoeList.observe(viewLifecycleOwner){
            showShoeList(it)
        }

        this.setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)

    }

    private fun showShoeList(list: MutableList<Shoe>){
        val listLinearLayout = binding.shoeDetailLayout

        for (shoe in list){
            val shoeLayoutBinding: LayoutShoeBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.layout_shoe,
                listLinearLayout,
                false
            )
            shoeLayoutBinding.apply {
                nameTextView.text=shoe.name
                companyTextView.text=shoe.company
                sizeTextView.text=shoe.size.toString()
                descriptionTextView.text=shoe.description}

            listLinearLayout.addView(shoeLayoutBinding.root)
        }
    }
 }
