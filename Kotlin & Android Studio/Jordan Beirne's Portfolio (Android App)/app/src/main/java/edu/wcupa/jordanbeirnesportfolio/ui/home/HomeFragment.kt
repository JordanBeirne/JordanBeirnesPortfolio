package edu.wcupa.jordanbeirnesportfolio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = androidx.compose.ui.platform.ComposeView(requireContext())
        composeView.setContent {
            HomeCompose()
        }
        return composeView
    }

}