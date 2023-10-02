package com.example.bookshelfapp.ui.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.bookshelfapp.R
import com.example.bookshelfapp.data.local.UserCredentials
import com.example.bookshelfapp.databinding.BottomSheetFragmentSignupBinding
import com.example.bookshelfapp.ui.viewModel.SignUpViewModel
import com.example.bookshelfapp.util.findNavControllerSafely
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer


@AndroidEntryPoint
class SignUpBottomSheetFragment : BaseBottomSheetFragment<BottomSheetFragmentSignupBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetFragmentSignupBinding =
        BottomSheetFragmentSignupBinding::inflate

    private val signUpViewModel by viewModels<SignUpViewModel>()
    private var countryList : Array<String> = arrayOf()
    var country = "Algeria"

    override fun setup() {
        val jsonString = readJsonFromAssets()

        if (jsonString.isNotEmpty()) {
            // Parse the JSON response
            val jsonObject = JSONObject(jsonString)

            // Access data object
            val dataObject = jsonObject.getJSONObject("data")

            // Get a list of country names
            val countryNamesList = mutableListOf<String>()

            // Iterate through the keys (country codes) in the data object
            val keys = dataObject.keys()
            while (keys.hasNext()) {
                val countryCode = keys.next()
                val countryData = dataObject.getJSONObject(countryCode)
                val countryName = countryData.getString("country")
                countryNamesList.add(countryName)
            }
            countryList = countryNamesList.toTypedArray()
        }


        binding?.btnRegister?.setOnClickListener {
            if (validation()) {
                signUpViewModel.insertUser(
                    UserCredentials(
                        userName = binding?.edtEmail?.text?.trim().toString(),
                        password = binding?.edtPassword?.text.toString(),
                        country = country
                    )
                )
            }
        }
        signUpViewModel.signUpResult.observe(viewLifecycleOwner){
            if (it){
                registrationSuccessful()
            }else{
                toast("Some Error Occurred. Please Try Again")
            }
        }


        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, countryList)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding?.spinner?.adapter = adapter

        // Set a listener to handle item selection
        binding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                country = countryList[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }

    }

    private fun registrationSuccessful() {
        savedPrefManager.putLogin(true)
        findNavControllerSafely()?.navigate(SignUpBottomSheetFragmentDirections.actionSignUpBottomSheetFragmentToLoginBottomSheetFragment())
    }

    private fun validation(): Boolean {
        if (binding?.edtEmail?.text?.trim().toString().isEmpty()) {
            toast("Entered Name is Empty")
            return false
        }
        if (binding?.edtPassword?.text?.toString()?.isEmpty() == true) {
            toast("Entered Password is Empty")
            return false
        }
        val passwordPattern = "^(?=.*[0-9])(?=.*[!@#\$%&()])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
        if (!binding?.edtPassword?.text?.toString()?.let { passwordPattern.matches(it) }!!) {
            toast("Password must have at least 8 characters with one number, special characters, one lowercase letter, and one uppercase letter.")
            return false
        }
        if (country.isEmpty()){
            toast("Select Country")
            return false
        }
        return true
    }

        private fun readJsonFromAssets(): String {
            val `is` = resources.openRawResource(R.raw.countries)
            val writer: Writer = StringWriter()
            val buffer = CharArray(1024)
            try {
                val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
                var n: Int
                while (reader.read(buffer).also { n = it } != -1) {
                    writer.write(buffer, 0, n)
                }
            } finally {
                `is`.close()
            }

            val jsonString: String = writer.toString()
            return jsonString
        }


}
