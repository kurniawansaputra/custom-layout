package com.example.wilayahadministrasiindonesia.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wilayahadministrasiindonesia.adapter.ProvinceAdapter
import com.example.wilayahadministrasiindonesia.databinding.ActivityListProvinceBinding
import com.example.wilayahadministrasiindonesia.model.ProvinsiItem
import com.example.wilayahadministrasiindonesia.viewmodel.ListProvinceViewModel
import java.util.*

class ListProvinceActivity : AppCompatActivity() {
    private lateinit var listProvinceViewModel: ListProvinceViewModel
    private var provinceAdapter: ProvinceAdapter? = null

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
            toolbar.setNavigationOnClickListener {
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
            provinceAdapter = ProvinceAdapter(it.provinsi as ArrayList<ProvinsiItem>, this)
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

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun filter(text: String) {
        val provinceList = ArrayList<ProvinsiItem>()
        for (item in provinceList) {
            if (item.nama?.lowercase(Locale.getDefault())
                    ?.contains(text.lowercase(Locale.getDefault())) == true) {
                provinceList.add(item)
            }
        }
        provinceAdapter!!.filterList(provinceList)
    }
}