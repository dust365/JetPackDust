package com.jetpack.dust.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jetpack.dust.R
import com.jetpack.dust.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var retrofit1: Retrofit

    @Inject
    lateinit var retrofit2: Retrofit


    @Inject
    lateinit var okHttpClient1: OkHttpClient

    @Inject
    lateinit var okHttpClient2: OkHttpClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        setHasOptionsMenu(true)
        inintClick()
        return root
    }


    private fun inintClick() {
//        if (okHttpClient1==null)
//        if (okHttpClient2==null)
//        if (retrofit1==null)
//        if (retrofit2==null)
        binding.buttonNetwork.setOnClickListener {
            Log.d("Hilt", "Home Page Click:okHttpClient是否是同一个实例=${okHttpClient1 === okHttpClient2}")
            Log.d("Hilt", "Home Page Click: retrofit是否是同一个实例=${retrofit1 === retrofit2}")

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //去搜索页面
            R.id.search_bar -> {
                val bundle=Bundle()
                bundle.putString("hotKey","啦啦啦德玛西亚")
                findNavController().navigate(R.id.action_navigation_home_to_navigation_search,bundle)
            }
            else -> {}
        }
        return true
    }


}