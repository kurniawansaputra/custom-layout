package com.example.wilayahadministrasiindonesia.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wilayahadministrasiindonesia.R
import com.example.wilayahadministrasiindonesia.databinding.FragmentTableBinding
import com.example.wilayahadministrasiindonesia.model.ProvinsiItem
import com.example.wilayahadministrasiindonesia.viewmodel.ListProvinceViewModel
import com.levitnudi.legacytableview.LegacyTableView

class TableFragment : Fragment() {
    private lateinit var listProvinceViewModel: ListProvinceViewModel
    private lateinit var provinceList: ArrayList<ProvinsiItem>
    private val idProvince: MutableList<String> = ArrayList()
    private val nameProvince: MutableList<String> = ArrayList()

    private var _binding: FragmentTableBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        listProvinceViewModel = ViewModelProvider(this)[ListProvinceViewModel::class.java]
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTable()
    }

    private fun setTable() {
//        listProvinceViewModel.isLoading.observe(this) {
//            setLoading(it)
//        }
        listProvinceViewModel.getProvince()
        listProvinceViewModel.province.observe(viewLifecycleOwner) {
            provinceList = it.provinsi as ArrayList<ProvinsiItem>
            for (item in provinceList.indices) {
                idProvince.add(
                    provinceList[item].id.toString()
                )
                nameProvince.add(
                    provinceList[item].nama.toString()
                )
            }


            for (i in nameProvince) {
                LegacyTableView.insertLegacyContent(i, i)
            }
            //set table title labels
            LegacyTableView.insertLegacyTitle("idProvinsi", "namaProvinsi")


            //set table contents as string arrays
//            LegacyTableView.insertLegacyContent(
//                provinceList[0].id.toString(), provinceList[0].nama.toString(),
//                provinceList[1].id.toString(), provinceList[1].nama.toString()
//            )

            binding.legacyTableView.setTitle(LegacyTableView.readLegacyTitle())
            binding.legacyTableView.setContent(LegacyTableView.readLegacyContent())

            //depending on the phone screen size default table scale is 100
            //you can change it using this method
            //legacyTableView.setInitialScale(100);//default initialScale is zero (0)

            //if you want a smaller table, change the padding setting

            //depending on the phone screen size default table scale is 100
            //you can change it using this method
            //legacyTableView.setInitialScale(100);//default initialScale is zero (0)

            //if you want a smaller table, change the padding setting
            binding.legacyTableView.setTablePadding(7)

            //to enable users to zoom in and out:
            binding.legacyTableView.setZoomEnabled(true)
            binding.legacyTableView.setShowZoomControls(true)

            //remember to build your table as the last step
            binding.legacyTableView.build()

        }
    }

//    private fun setLoading(isLoading: Boolean) {
//        if (isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu_table, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuCamera -> {
                Toast.makeText(requireContext(), "Camera", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menuProfile -> {
                Toast.makeText(requireContext(), "Profile", Toast.LENGTH_SHORT).show()
                true
            }
            else -> true
        }
    }

}