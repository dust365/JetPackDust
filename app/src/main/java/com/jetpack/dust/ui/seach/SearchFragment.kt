package com.jetpack.dust.ui.seach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jetpack.dust.R
import com.jetpack.dust.databinding.FragmentSearchBinding

class SearchFragment :Fragment() {
     private var _binding: FragmentSearchBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = _binding?.root!!
        initClick(root)
        return root
    }

    private fun initClick(root: View) {

        _binding?.goToPersonBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_to_navigation_person)
        }

        val string = arguments?.getString("hotKey")
        _binding?.textView?.text=string

    }


}