package com.appbin.fininfocomassignment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.appbin.fininfocomassignment.R
import com.appbin.fininfocomassignment.databinding.FragmentSortingBottomSheetBinding
import com.appbin.fininfocomassignment.ui.interfaces.OnSortClickListener
import com.appbin.fininfocomassignment.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingBottomSheet : BottomSheetDialogFragment()  {

    private lateinit var binding : FragmentSortingBottomSheetBinding

    private lateinit var onSortClickListener : OnSortClickListener

    fun initClickListener(onSortClickListener: OnSortClickListener){
        this.onSortClickListener = onSortClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sorting_bottom_sheet, container, false)

        binding.lifecycleOwner = viewLifecycleOwner


        binding.byName.setOnClickListener {
            onSortClickListener.sortData(Constants.sortByName)
            findNavController().navigateUp()
        }

        binding.byAge.setOnClickListener {
            onSortClickListener.sortData(Constants.sortByAge)
            findNavController().navigateUp()
        }

        binding.byCity.setOnClickListener {
            onSortClickListener.sortData(Constants.sortByCity)
            findNavController().navigateUp()
        }

        return binding.root
    }


}

