package com.chase.interview.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.chase.interview.project.R
import com.chase.interview.project.models.IntentObj
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_welcome_page.*

class WelcomePage : Fragment() {
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
            navigateToSchoolDirectoryPage(getNeighborName(R.string.br_brooklyn))
        }
        welcomepage_queens_card_id.setOnClickListener {
            navigateToSchoolDirectoryPage(getNeighborName(R.string.br_queens))
        }
        welcomepage_manhattan_card_id.setOnClickListener {
            navigateToSchoolDirectoryPage(getNeighborName(R.string.br_manhattan))
        }
        welcomepage_si_card_id.setOnClickListener {
            navigateToSchoolDirectoryPage(getNeighborName(R.string.br_staten_island))
        }
        welcomepage_bronx_card_id.setOnClickListener {
            navigateToSchoolDirectoryPage(getNeighborName(R.string.br_bronx))
        }
    }
    private fun navigateToSchoolDirectoryPage(neighbor: String) {
        val router: WelcomePageDirections.ActionWelcomePageToSchoolDirectoryPage = WelcomePageDirections.actionWelcomePageToSchoolDirectoryPage(
            IntentObj(neighbor))
        val controller: NavController = findNavController()
        controller.navigate(router)
    }
}