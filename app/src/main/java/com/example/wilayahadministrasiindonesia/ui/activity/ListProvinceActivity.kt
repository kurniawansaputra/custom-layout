package com.example.wilayahadministrasiindonesia.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wilayahadministrasiindonesia.adapter.ProvinceAdapter
import com.example.wilayahadministrasiindonesia.databinding.ActivityListProvinceBinding
import com.example.wilayahadministrasiindonesia.model.ProvinsiItem
import com.example.wilayahadministrasiindonesia.viewmodel.ListProvinceViewModel
import java.util.*
import kotlin.collections.ArrayList

class ListProvinceActivity : AppCompatActivity() {
    private lateinit var listProvinceViewModel: ListProvinceViewModel
    private var provinceAdapter: ProvinceAdapter? = null
    private lateinit var provinceList: ArrayList<ProvinsiItem>

    private lateinit var binding: ActivityListProvinceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listProvinceViewModel = ViewModelProvider(this)[ListProvinceViewModel::class.java]

        setListener()
        setProvince()
    }

    private fun setListener() {
        binding.apply {
            labelBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setProvince() {
        listProvinceViewModel.isLoading.observe(this) {
            setLoading(it)
        }
        listProvinceViewModel.getProvince()
        listProvinceViewModel.province.observe(this) {
            provinceList = it.provinsi as ArrayList<ProvinsiItem>
            provinceAdapter = ProvinceAdapter(provinceList, this)
            binding.rvProvince.adapter = provinceAdapter
            binding.rvProvince.setHasFixedSize(true)

            binding.etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    filter(p0.toString())
                }

            })
        }
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<ProvinsiItem>()
        //looping through existing elements and adding the element to filtered list
        provinceList.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.nama?.lowercase(Locale.getDefault())?.contains(text.lowercase(Locale.getDefault())) == true
        }
        //calling a method of the adapter class and passing the filtered list
        if (filteredNames.isEmpty()) {
            binding.containerNotFound.containerNotFound.visibility = View.VISIBLE
            binding.rvProvince.visibility = View.GONE
        } else {
            binding.containerNotFound.containerNotFound.visibility = View.GONE
            binding.rvProvince.visibility = View.VISIBLE
            provinceAdapter?.filterList(filteredNames)
        }

    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}