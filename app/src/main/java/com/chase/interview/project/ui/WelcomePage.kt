package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.chase.interview.project.R
import com.chase.interview.project.di.ui.withFactory
import com.chase.interview.project.models.IntentObj
import com.chase.interview.project.viewmodel.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_welcome_page.*
import javax.inject.Inject

class WelcomePage : Fragment() {
    @Inject
    lateinit var viewModelFactory: SharedViewModel.Factory
    private val sharedViewModel: SharedViewModel by activityViewModels {
        withFactory(this.viewModelFactory)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pickANeighborAction()
    }

    private fun getNeighborName(id: Int): String = resources.getString(id)
    private fun pickANeighborAction() {
        welcomepage_brooklyn_card_id.setOnClickListener {
            this.sharedViewModel.setFilterOption(getNeighborName(R.string.br_brooklyn))
            navigateToSchoolDirectoryPage()
        }
        welcomepage_queens_card_id.setOnClickListener {
            this.sharedViewModel.setFilterOption(getNeighborName(R.string.br_queens))
            navigateToSchoolDirectoryPage()
        }
        welcomepage_manhattan_card_id.setOnClickListener {
            this.sharedViewModel.setFilterOption(getNeighborName(R.string.br_manhattan))
            navigateToSchoolDirectoryPage()
        }
        welcomepage_si_card_id.setOnClickListener {
            this.sharedViewModel.setFilterOption(getNeighborName(R.string.br_staten_island))
            navigateToSchoolDirectoryPage()
        }
        welcomepage_bronx_card_id.setOnClickListener {
            this.sharedViewModel.setFilterOption(getNeighborName(R.string.br_bronx))
            navigateToSchoolDirectoryPage()
        }
    }
    private fun navigateToSchoolDirectoryPage() {
        val router: NavDirections = WelcomePageDirections.actionWelcomePageToSchoolDirectoryPage()
        val controller = findNavController()
        controller.navigate(router)
    }
}