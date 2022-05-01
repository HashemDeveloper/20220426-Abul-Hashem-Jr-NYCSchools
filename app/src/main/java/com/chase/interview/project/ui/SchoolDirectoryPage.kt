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
import com.chase.interview.project.R
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.models.SchoolDirectoryObj
import com.chase.interview.project.viewmodel.SharedViewModel
import com.chase.interview.project.ui.SchoolDirectoryPageArgs.fromBundle
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SchoolDirectoryPage : Fragment() {
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by activityViewModels {
        withFactory(this.viewModelFactory)
    }
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
        val neighbor: String = intentObj.data
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.schoolDirectories.collect {
                    val schoolDirList: MutableList<SchoolDirectoryObj>? = it?.results?.filter { v -> v.city == neighbor }?.toMutableList()
                    schoolDirList?.sortBy { it.schoolName }
                    print(schoolDirList)
                }
            }
        }
    }
}