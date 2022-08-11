package com.example.wilayahadministrasiindonesia.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wilayahadministrasiindonesia.R
import com.example.wilayahadministrasiindonesia.databinding.ActivityMainBinding
import com.example.wilayahadministrasiindonesia.model.KotaKabupatenItem
import com.example.wilayahadministrasiindonesia.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private var idProvince: String = ""
    private var nameProvince: String = ""
    private var idRegency: String = ""
    private var nameRegency: String = ""
    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setListener()
    }

    private fun setListener() {
        binding.apply {
            textProvince.setOnClickListener {
                val moveIntent = Intent(this@MainActivity, ListProvinceActivity::class.java)
                resultProvince.launch(moveIntent)
            }
            buttonTable.setOnClickListener {
                val moveIntent = Intent(this@MainActivity, TableActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetail() {
        binding.apply {
            textProvince.text = nameProvince
        }
        setRegency()
    }

    private fun setRegency() {
        mainViewModel.getRegency(idProvince)
        mainViewModel.regency.observe(this) {
            val regencyData: List<KotaKabupatenItem> = it.kotaKabupaten as List<KotaKabupatenItem>
            val listRegency: MutableList<String> = ArrayList()

            for (i in regencyData.indices) {
                listRegency.add(
                    regencyData[i].nama.toString()
                )
            }

            val regencyAdapter = ArrayAdapter(this, R.layout.item_row_dropdown, listRegency)
            binding.autoRegency.setAdapter(regencyAdapter)

            binding.autoRegency.setOnItemClickListener { _, _, i, _ ->
                idRegency = regencyData[i].id.toString()
                nameRegency = regencyData[i].nama.toString()
            }

//            binding.autoProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
//                    idProvince = provinceData[position].id.toString()
//                    nameProvince = provinceData[position].nama.toString()
//                }
//
//                override fun onNothingSelected(adapterView: AdapterView<*>) {}
//            }
        }


    }

    private val resultProvince =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                idProvince = data?.getIntExtra("idProvince", 0).toString()
                nameProvince = data?.getStringExtra("nameProvince").toString()

                setDetail()
            }
        }
}
