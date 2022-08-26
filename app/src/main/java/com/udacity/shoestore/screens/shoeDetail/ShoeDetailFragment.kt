package com.udacity.shoestore.screens.shoeDetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentShoeDetailBinding

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail,container,false)
        binding.viewModel=viewModel
        binding.shoeTemp=viewModel.shoetemp

        viewModel.saveButton.observe(viewLifecycleOwner){
            if(it){
                this.findNavController().popBackStack()
                viewModel.eventSaveClickedCompleted()
            }
        }

        binding.cancelButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        return binding.root
    }

}