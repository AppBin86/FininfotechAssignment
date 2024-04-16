package com.appbin.fininfocomassignment.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.appbin.fininfocomassignment.R
import com.appbin.fininfocomassignment.databinding.FragmentLoginBinding
import com.appbin.fininfocomassignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


class Login : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding : FragmentLoginBinding

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        sharedPreferences = requireContext().getSharedPreferences(Constants.spFile,0)
        editor = sharedPreferences.edit()

        viewModel.apply {
            isLoginLiveData.observe(viewLifecycleOwner) {
                if (it){
                    editor.putBoolean(Constants.isLogin,true)
                    editor.commit()
                    Toast.makeText(requireContext(), "Login successfully...", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_login_to_home2)
                    loginSuccessfully()
                }
            }

            showToastLiveData.observe(viewLifecycleOwner){
                if (it){
                    Toast.makeText(
                        requireContext(),
                        "${toastMessage.value}",
                        Toast.LENGTH_SHORT
                    ).show()
                    toastDone()
                }
            }
        }


        return binding.root
    }


}