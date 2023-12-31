package com.example.bookshelfapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.bookshelfapp.util.SavedPrefManager
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

//Base Class For All Fragment


abstract class BaseFragment<T: ViewBinding>  : Fragment() {
    protected var binding: T? = null
    open lateinit var savedPrefManager: SavedPrefManager


    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = bindingInflater.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedPrefManager = SavedPrefManager(requireContext())
        setup()


    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    fun toast(message:String?){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}