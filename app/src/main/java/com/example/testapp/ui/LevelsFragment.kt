package com.example.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.LevelsUiState
import com.example.testapp.R
import com.example.testapp.ViewModelProviderFactory
import com.example.testapp.adapters.LevelsRecyclerAdapter
import com.example.testapp.models.Level
import com.example.testapp.viewmodels.LevelsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LevelsFragment: DaggerFragment(), LevelsRecyclerAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var mAdapter: LevelsRecyclerAdapter

    private lateinit var viewModel: LevelsViewModel
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_levels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        init()
    }

    private fun init(){

        viewModel = ViewModelProviders.of(requireActivity(), providerFactory).get(LevelsViewModel::class.java)
        setupRecyclerView()
        subscribeObservers()
    }

    private fun setupRecyclerView(){
        mAdapter = LevelsRecyclerAdapter(requireContext(), this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter
    }

    private fun subscribeObservers(){

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect {
                    when(it){
                        is LevelsUiState.Success -> {
                            progressBar.visibility = View.GONE
                            mAdapter.setData(it.tasksList)

                        }
                        is LevelsUiState.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                        is LevelsUiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(levelId: Int) {
        val bundle = Bundle()
        bundle.putInt("levelId", levelId)
        Navigation.findNavController(requireView()).navigate(R.id.load_tasks_fragment, bundle)
    }
}