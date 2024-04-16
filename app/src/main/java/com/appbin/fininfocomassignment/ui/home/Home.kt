package com.appbin.fininfocomassignment.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.appbin.fininfocomassignment.R
import com.appbin.fininfocomassignment.database.UserInfo
import com.appbin.fininfocomassignment.databinding.FragmentHomeBinding
import com.appbin.fininfocomassignment.repository.Repository
import com.appbin.fininfocomassignment.ui.interfaces.OnSortClickListener
import com.appbin.fininfocomassignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject


class Home : Fragment() ,OnSortClickListener{

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : HomeAdapter
    private lateinit var modal : SortingBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getAllData()

        val list = viewModel.userInfo
        adapter = HomeAdapter()
        adapter.data = list as ArrayList<UserInfo>

        binding.recyclerView.adapter = adapter
        modal = SortingBottomSheet()
        modal.initClickListener(this)

        viewModel.isSortClickedLiveData.observe(viewLifecycleOwner){
            if(it){
                requireActivity().supportFragmentManager.let { modal.show(it, "SortingBottomSheet") }
                viewModel.sortClickedDone()
            }
        }
        return binding.root
    }

    override fun sortData(type: String) {
        when(type){

            Constants.sortByName -> {
                val list = viewModel.userInfo
                val list2 : List<UserInfo> = list.sortedBy { it.name }
                adapter.data = ArrayList(list2)
                modal.dismiss()
            }

            Constants.sortByAge -> {

                val list = viewModel.userInfo
                val list2 : List<UserInfo> = list.sortedBy { it.age?.toInt() }
                adapter.data = ArrayList(list2)
                modal.dismiss()
            }

            Constants.sortByCity -> {

                val list = viewModel.userInfo
                val list2 : List<UserInfo> = list.sortedBy { it.city }
                adapter.data = ArrayList(list2)
                modal.dismiss()
            }
        }
    }

}