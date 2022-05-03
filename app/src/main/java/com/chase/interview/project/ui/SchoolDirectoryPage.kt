package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chase.interview.project.R
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.models.SchoolDirectoryObj
import com.chase.interview.project.recylerviews.SchoolDirAdapter
import com.chase.interview.project.viewmodel.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_school_directory_page.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SchoolDirectoryPage : Fragment(), SchoolDirAdapter.SchoolDirItemActionListener {
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by activityViewModels {
        withFactory(this.viewModelFactory)
    }
    private val schoolDirListAdapter: SchoolDirAdapter = SchoolDirAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_directory_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schoolDirectoryPage_recyclerView_id.layoutManager = LinearLayoutManager(requireContext())
        schoolDirectoryPage_recyclerView_id.adapter = this.schoolDirListAdapter
        getSchoolDirList(this.sharedViewModel.getFilterOption())
        setupPopUpFilter()
    }
    private fun setupPopUpFilter() {
        val popupWindow = PopupMenu(requireContext(),schoolDirectoryPage_filter_bt_id)
        popupWindow.menuInflater.inflate(R.menu.school_dir_filter_options,popupWindow.menu)
        val filterOption: String = this.sharedViewModel.getFilterOption()
        popupWindow.menu.getItem(filterOptionMap[filterOption]!!).isChecked = true
        schoolDirectoryPage_filter_bt_id?.setOnClickListener {
            popupWindow.show()
        }
        popupWindow.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.filter_brooklyn -> {
                    print(item.title)
                    item.isChecked = true
                }
                R.id.filter_queens -> {
                    item.isChecked = true
                }
                R.id.filter_manhattan -> {
                    item.isChecked = true
                }
                R.id.filter_si -> {
                    item.isChecked = true
                }
                R.id.filter_bronx -> {
                    item.isChecked = true
                }
            }
            print(item.title)
            getSchoolDirList(item.title.toString())
            this.sharedViewModel.setFilterOption(item.title.toString())
            true
        }
    }

    private fun getSchoolDirList(filterOption: String) {
        val neighbor: String = filterOption
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.schoolDirectories.map { m->
                    m?.results?.filter { v -> v.city == neighbor }?.toMutableList()
                }.collect { l ->
                    l?.sortBy { it.schoolName }
                    this@SchoolDirectoryPage.schoolDirListAdapter.setData(l)
                }
            }
        }
    }
    companion object {
        private val filterOptionMap: MutableMap<String,Int> = mutableMapOf()
        init {
            filterOptionMap["BROOKLYN"] = 0
            filterOptionMap["FLUSHING"] = 1
            filterOptionMap["MANHATTAN"] = 2
            filterOptionMap["STATEN ISLAND"] = 3
            filterOptionMap["BRONX"] = 4
        }
    }

    override fun onLearnMoreClicked(schoolDirectoryObj: SchoolDirectoryObj) {
        schoolDirectoryObj.dbn?.let { this.sharedViewModel.fetchSATScores(it) }
        val router: SchoolDirectoryPageDirections.ActionSchoolDirectoryPageToSchoolDirectoryDetailsPage = SchoolDirectoryPageDirections.actionSchoolDirectoryPageToSchoolDirectoryDetailsPage(schoolDirectoryObj)
        val controller: NavController = findNavController()
        controller.navigate(router)
    }
}