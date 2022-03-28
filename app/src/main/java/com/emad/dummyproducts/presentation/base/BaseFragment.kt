package com.emad.dummyproducts.presentation.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.emad.dummyproducts.R
import com.emad.dummyproducts.domain.extentions.showLongSnackBar
import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import com.emad.dummyproducts.utils.NetworkListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment: Fragment() {
    lateinit var networkListener: NetworkListener
    val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkListener= NetworkListener(requireContext())
        listenNetworkStatus()
    }

    private fun listenNetworkStatus(){
        lifecycleScope.launchWhenResumed {
            networkListener.checkNetworkAvailability()
            networkListener.isNetworkAvailable.collectLatest{
                if(!it){
                    showLongSnackBar(getString(R.string.connectionLost))
                }else{
                    if (!mainViewModel.networkStatus.value){
                        showLongSnackBar(getString(R.string.connectionRestored))
                    }
                }
                mainViewModel.setNetworkStatus(it)
            }
        }
    }
}