package com.example.newsproject.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsproject.databinding.FragmentSaveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment() {

    private lateinit var binding : FragmentSaveBinding
    private lateinit var saveViewModel : SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveViewModel = ViewModelProvider(requireActivity())[SaveViewModel::class.java]
        saveViewModel.getData()

        saveViewModel.saveLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvSaveNews.adapter = SaveAdapter(requireContext(),it)
            if (it.isEmpty())
            {
                binding.savePlaceHolderContainer.tvNoSave.visibility = View.VISIBLE
            }
        })
    }
}