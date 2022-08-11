package com.example.wilayahadministrasiindonesia.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wilayahadministrasiindonesia.databinding.ItemRowListBinding
import com.example.wilayahadministrasiindonesia.model.ProvinsiItem
import com.example.wilayahadministrasiindonesia.ui.activity.MainActivity


class ProvinceAdapter(private var provinceList: ArrayList<ProvinsiItem>, private val context: Context): RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {
    class ViewHolder (val binding: ItemRowListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(provinceList[position]) {
                val id = id
                val name = nama

                binding.apply {
                    textName.text = name

                    containerList.setOnClickListener {
                        val moveWithData = Intent(context, MainActivity::class.java)
                        moveWithData.putExtra("idProvince",  id)
                        moveWithData.putExtra("nameProvince", name)
                        (context as Activity).setResult(RESULT_OK, moveWithData)
                        context.finish()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = provinceList.size

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredNames: ArrayList<ProvinsiItem>) {
        provinceList = filteredNames
        notifyDataSetChanged()
    }
}