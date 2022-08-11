package com.example.wilayahadministrasiindonesia.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wilayahadministrasiindonesia.databinding.ActivityTableBinding
import com.example.wilayahadministrasiindonesia.model.ProvinsiItem
import com.example.wilayahadministrasiindonesia.viewmodel.ListProvinceViewModel
import com.levitnudi.legacytableview.LegacyTableView


class TableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}