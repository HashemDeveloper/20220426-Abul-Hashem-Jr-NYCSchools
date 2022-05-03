package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import boldFirstWord
import com.chase.interview.project.R
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.models.SatScoreDataObj
import com.chase.interview.project.models.SchoolDirectoryObj
import com.chase.interview.project.recylerviews.SchoolDetailsAdapter
import com.chase.interview.project.ui.SchoolDirectoryDetailsPageArgs.fromBundle
import com.chase.interview.project.utils.RequestState
import com.chase.interview.project.viewmodel.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import getFirstWord
import kotlinx.android.synthetic.main.fragment_school_directory_details_page.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SchoolDirectoryDetailsPage : Fragment() {
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by activityViewModels {
        withFactory(this.viewModelFactory)
    }
    private val schoolDetailsAdapter: SchoolDetailsAdapter = SchoolDetailsAdapter()
    private val schoolDirectoryObj: SchoolDirectoryObj by lazy {
        fromBundle(requireArguments()).schoolDirectoryPage
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_directory_details_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_school_details_recyclerView_id?.layoutManager = LinearLayoutManager(requireContext())
        fragment_school_details_recyclerView_id?.adapter = this.schoolDetailsAdapter
        DETAILS_LIST.add(schoolDirectoryObj)
        this.schoolDetailsAdapter.setData(DETAILS_LIST)
        fetchSATScores()
    }
    private fun fetchSATScores() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                sharedViewModel.satScoreResult.collect { state ->
                    when (state) {
                        is RequestState.Loading -> {
                            this@SchoolDirectoryDetailsPage.schoolDetailsAdapter.setIsLoading(true)
                        }
                        is RequestState.Success -> {
                            val satScoreDataObj = state.data[0]
                            DETAILS_LIST.add(satScoreDataObj)
                            this@SchoolDirectoryDetailsPage.schoolDetailsAdapter.setData(
                                DETAILS_LIST)
                        }
                        is RequestState.Error -> {
                            print(state.error)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        DETAILS_LIST.clear()
    }
//    private fun setupInitialData() {
//        fragment_school_dir_details_titleview_id?.let {
//            boldFirstWord(getFirstWord(schoolDirectoryObj.schoolName!!).length,schoolDirectoryObj.schoolName!!, it, false)
//        }
//        fragment_school_dir_overview_id.text = schoolDirectoryObj.overviewParagraph
//    }
    companion object {
        private val DETAILS_LIST: MutableList<Any> = mutableListOf()
    }
}