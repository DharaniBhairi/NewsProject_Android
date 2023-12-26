package com.example.newsproject.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.dataBase.DataBaseResponse
import com.example.newsproject.dataBase.DataBaseViewModel
import com.example.newsproject.databinding.ChipsBinding
import com.example.newsproject.databinding.FragmentHomeBinding
import com.example.newsproject.utils.*
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.SaveItemClickListener {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dataBaseViewModel : DataBaseViewModel
    lateinit var userData: DataPreferences
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel= ViewModelProvider(this)[HomeViewModel::class.java]
        dataBaseViewModel= ViewModelProvider(requireActivity())[DataBaseViewModel::class.java]

        userData = DataPreferences(requireContext())
        lifecycleScope.launch {
            val value = userData.readUser(Constants.USER_KEY).toString()
            binding.tvName.text = value
        }

        lifecycleScope.launch {
            val data = userData.readCategories(Constants.CATEGORIES_KEY)
            binding.chipGroup.addView(createChip("All"))
            data?.forEach {
                 count ++
                userData.storeValue(count,Constants.VALUE_KEY)
                println("count size $count")
                binding.chipGroup.addView(createChip(it))
            }
        }
        ProgressBar.showProgressBar(requireContext())
        homeViewModel.getData("top")
        homeViewModel.newsResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvNews.adapter = HomeAdapter(requireContext(), it, this)
            ProgressBar.hideProgressBar()
        })
    }

    @SuppressLint("SuspiciousIndentation")
    private fun createChip(label: String?): Chip {
        val chip = ChipsBinding.inflate(layoutInflater).root
        chip.text = label.toString()
        chip.setOnClickListener {
            if (NetworkHelper.Companion.isInternetAvailable(requireContext())) {
                if (chip.text == "All"){
                    homeViewModel.getData("top")
                }
                else{
                    homeViewModel.getData(label.toString())
                }

                homeViewModel.newsResponseLiveData.observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is MyApiResponse.Success -> {
                            binding.rvNews.adapter = HomeAdapter(requireContext(), it, this)
                        }
                        is MyApiResponse.Error -> {
                            binding.placeHolderContainer.tvError.visibility = View.VISIBLE
                            binding.placeHolderContainer.tvError.text = it.errorMessage
                        }
                        else -> {}
                    }
                })
            }
            else {
                noInternetAlert(label.toString())
            }
        }
        return chip
    }

    private fun noInternetAlert(category : String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("No internet")
        builder.setPositiveButton("Retry") { dialogInterface, which ->
            if (NetworkHelper.isInternetAvailable(requireContext()))
            {
                ProgressBar.showProgressBar(requireContext())
                homeViewModel.getData(category)
            }
            else{
                noInternetAlert(category)
            }
        }
        builder.setNeutralButton("Ok") { dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun saveItemClick(position: Int, saveDataList: DataBaseResponse) {
        lifecycleScope.launch{
            dataBaseViewModel.insertData(saveDataList)
        }
    }
}