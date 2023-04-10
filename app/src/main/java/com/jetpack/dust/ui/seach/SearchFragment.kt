package com.jetpack.dust.ui.seach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jetpack.dust.R
import com.jetpack.dust.databinding.FragmentSearchBinding
import com.jetpack.dust.ui.seach.engine.BindQuickEngin
import com.jetpack.dust.ui.seach.engine.BindSlowEngin
import com.jetpack.dust.ui.seach.engine.SearchEngine
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment :Fragment() {

    private val viewModel: SearchViewMode by viewModels()


    @Inject
    lateinit var searchMode: SearchMode




     private lateinit var _binding: FragmentSearchBinding

    @Inject
//    @BindQuickEngin
    lateinit var searchEngine: SearchEngine

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

        val hotKey = arguments?.getString("hotKey")?:"默认"
        _binding?.textView?.text=hotKey


        //跳转
        _binding.goToPersonBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_to_navigation_person)
        }


        //搜索引擎(测试引擎提供者的指定是否生效)
        _binding.engin?.setOnClickListener {

            searchEngine.start()
            searchEngine.end()
        }

        //viewMode test
        _binding.viewMode.setOnClickListener {
            viewModel.goToSearch(hotKey)
        }

    }


}