package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chase.interview.project.R
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.recylerviews.SchoolDirAdapter
import com.chase.interview.project.viewmodel.SharedViewModel
import com.chase.interview.project.ui.SchoolDirectoryPageArgs.fromBundle
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_school_directory_page.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SchoolDirectoryPage : Fragment() {
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by activityViewModels {
        withFactory(this.viewModelFactory)
    }
    private val schoolDirListAdapter: SchoolDirAdapter = SchoolDirAdapter()
    private val intentObj by lazy {
        fromBundle(requireArguments()).welcomePage
    }
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
        getSchoolDirList()
        setupPopUpFilter()

    }
    private fun setupPopUpFilter() {
        val listPopupWindow = ListPopupWindow(requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = schoolDirectoryPage_filter_bt_id
        listPopupWindow.width = 400
        val items = listOf("BROOKLYN","QUEENS","MANHATTAN","STATEN ISLAND","BRONX")
        val adapter = ArrayAdapter(requireContext(), R.layout.pop_up_filter_item_layout, R.id.pop_up_options_id, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            listPopupWindow.dismiss()
        }
        schoolDirectoryPage_filter_bt_id?.setOnClickListener {
            listPopupWindow.show()
        }
    }

    private fun getSchoolDirList() {
        val neighbor: String = intentObj.data
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
}