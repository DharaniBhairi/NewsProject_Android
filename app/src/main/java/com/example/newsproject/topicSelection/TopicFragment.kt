package com.example.newsproject.topicSelection

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.R
import com.example.newsproject.TopicSelectionActivity
import com.example.newsproject.databinding.EnterNameLayoutBinding
import com.example.newsproject.databinding.FragmentTopicBinding
import com.example.newsproject.databinding.TopicSingleItemBinding
import com.example.newsproject.home.HomeFragment
import com.example.newsproject.main.MainActivity
import com.example.newsproject.topicSelection.model.MainResponse
import com.example.newsproject.utils.Constants
import com.example.newsproject.utils.DataPreferences

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopicFragment : Fragment(), TopicAdapter.TopicClickListener {

    private lateinit var binding: FragmentTopicBinding
    private lateinit var mViewModel: TopicViewModel
    private lateinit var dialogLayout: EnterNameLayoutBinding
    private lateinit var userData: DataPreferences
    private var checklist = setOf<String>()
    private var arrayList: ArrayList<MainResponse> = arrayListOf()
    private var checklistData = ArrayList<String>()
    private var checkItemTrue = ArrayList<String>()
    private var checkItemFalse = ArrayList<String>()
    var checkedState = false
    var clickedItemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userData = DataPreferences(requireContext())

        mViewModel = ViewModelProvider(this)[TopicViewModel::class.java]
        mViewModel.getCategory()
        mViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer {
            arrayList = it
            println("array list data $arrayList")
            binding.rvCategory.adapter = TopicAdapter(it, this)
        })

        binding.btnSkip.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnContinue.setOnClickListener {
            nameAlert()
        }
    }

    private fun nameAlert() {
        val builder = android.app.AlertDialog.Builder(requireContext())
        dialogLayout = EnterNameLayoutBinding.inflate(layoutInflater)
        builder.setTitle("Enter your name")
        builder.setPositiveButton("OK") { dialog, which ->
            lifecycleScope.launch {
                userData.storeUser(dialogLayout.etName.text.toString(), Constants.USER_KEY)
                val enteredName = userData.readUser(Constants.USER_KEY)
                if (enteredName != null) {
                    if (enteredName.isNotEmpty()) {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        this@TopicFragment.activity?.supportFragmentManager?.beginTransaction()
                            ?.remove(this@TopicFragment)?.commit()
                    }
                } else {
                    nameAlert()
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            Toast.makeText(requireContext(), R.string.enter_name, Toast.LENGTH_SHORT)
                .show();
            dialog.dismiss()
        }
        builder.setView(dialogLayout.root).show()
    }

    override fun topicItemClick(cardView: CardView, position: Int) {
        arrayList[position].isChecked = !arrayList[position].isChecked
        if (arrayList[position].isChecked) {
            checklistData.add(arrayList[position].title)
            clickedItemCount++
            cardView.setCardBackgroundColor(Color.parseColor("#ADD8E6"))
        } else {
            checklistData.remove(arrayList[position].title)
            clickedItemCount--
            cardView.setCardBackgroundColor(Color.WHITE)
        }
        checklist = checklistData.toSet()
        lifecycleScope.launch {
            userData.storeCategories(checklist, Constants.CATEGORIES_KEY)
        }
        binding.btnContinue.isEnabled = clickedItemCount >= 3
    }

    @SuppressLint("SuspiciousIndentation")
    override fun itemClick(cardView: CardView, position: Int, list: ArrayList<MainResponse>) {
        lifecycleScope.launch {
            val  value = userData.readState(Constants.STATE_KEY)
            println("state fragment $value")
            if (value == false) {
                binding.tvTitle.visibility = View.INVISIBLE
                binding.btnSkip.visibility = View.INVISIBLE
                binding.btnContinue.text = "Update"

                lifecycleScope.launch {
                    val checkedData = userData.readCategories(Constants.CATEGORIES_KEY)
                    val checkedValue = userData.readValue(Constants.VALUE_KEY)
                        if (checkedData?.contains(arrayList[position].title) == true) {
                            checkedState = true
                            checkItemTrue.add(arrayList[position].title)
                            cardView.setCardBackgroundColor(Color.parseColor("#ADD8E6"))
                        }
                        else {
                            checkedState = false
                            checkItemFalse.add(arrayList[position].title)
                            cardView.setCardBackgroundColor(Color.WHITE)
                        }
                    list[position].isChecked = checkedState
                    if (list[position].isChecked) {
                        checklistData.add(list[position].title)
                        cardView.setCardBackgroundColor(Color.parseColor("#ADD8E6"))
                    }

                    binding.btnContinue.isEnabled = checkItemTrue.size>=3
                    binding.btnContinue.setOnClickListener {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

/*    @SuppressLint("SetTextI18n")
    override fun itemClick(cardView: CardView, position: Int, list: ArrayList<MainResponse>) {
        var count = 0
        lifecycleScope.launch {
            val value = userData.readState(Constants.STATE_KEY)
            println("state fragment $value")
            if (value == false) {
                binding.tvTitle.visibility = View.INVISIBLE
                binding.btnSkip.visibility = View.INVISIBLE
                binding.btnContinue.text = "Update"
                lifecycleScope.launch {
                    checkedData = userData.readCategories(Constants.CATEGORIES_KEY)


                    if (checkedData?.contains(list[position].title) == true) {
                        count++
                        println("huh $count")
                        checkedState = true
                        checkItemTrue.add(list[position].title)
                        println("dataaaa trueee $checkItemTrue")
                        cardView.setCardBackgroundColor(Color.parseColor("#ADD8E6"))
                    } else {
                        count--
                        checkedState = false
                        checkItemFalse.add(list[position].title)

                        cardView.setCardBackgroundColor(Color.WHITE)
                    }
                    list[position].isChecked = checkedState

                        if (list[position].isChecked) {
                            checklistData.add(list[position].title)
                            println("checked item true title $checklistData")
                            cardView.setCardBackgroundColor(Color.parseColor("#ADD8E6"))
                        } else {
                            checklistData.remove(list[position].title)
                            cardView.setCardBackgroundColor(Color.WHITE)
                            println("checked item false title $checklistData")
                        }
//                    val clickedValue = userData.readValue(Constants.VALUE_KEY)
//                    println("hfjdkljhd $clickedValue")
                        //                  if (clickedValue != null) {
                        //                     binding.btnContinue.isEnabled = clickedValue>=3
                    binding.btnContinue.isEnabled = checkItemTrue.size >=3
                    binding.btnContinue.setOnClickListener {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }*/
}