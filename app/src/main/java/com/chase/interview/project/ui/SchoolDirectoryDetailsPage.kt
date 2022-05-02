package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chase.interview.project.R
import com.chase.interview.project.models.SchoolDirectoryObj
import com.chase.interview.project.ui.SchoolDirectoryDetailsPageArgs.fromBundle
import dagger.android.support.AndroidSupportInjection

class SchoolDirectoryDetailsPage : Fragment() {
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
        print(schoolDirectoryObj.dbn)
    }
}