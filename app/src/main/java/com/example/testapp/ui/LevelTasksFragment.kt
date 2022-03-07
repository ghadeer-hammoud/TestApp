package com.example.testapp.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.LevelUiState
import com.example.testapp.LevelsUiState
import com.example.testapp.R
import com.example.testapp.ViewModelProviderFactory
import com.example.testapp.adapters.TasksRecyclerAdapter
import com.example.testapp.models.Level
import com.example.testapp.models.Task
import com.example.testapp.viewmodels.LevelTasksViewModel
import com.example.testapp.viewmodels.LevelsViewModel
import com.google.android.material.button.MaterialButton
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LevelTasksFragment: DaggerFragment(), View.OnClickListener, TasksRecyclerAdapter.OnItemCheckListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btn_confirm: MaterialButton
    private lateinit var mAdapter: TasksRecyclerAdapter

    private lateinit var viewModel: LevelTasksViewModel
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private var currentLevelId = 0
    private lateinit var currentLevel: Level

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        btn_confirm = view.findViewById(R.id.btn_confirm)
        btn_confirm.setOnClickListener(this)

        if (arguments != null) {
            currentLevelId = LevelTasksFragmentArgs.fromBundle(requireArguments()).levelId
        }

        init()
    }

    private fun init(){

        viewModel = ViewModelProviders.of(requireActivity(), providerFactory).get(LevelTasksViewModel::class.java)
        setupRecyclerView()
        subscribeObservers()
        viewModel.getLevelTasks(currentLevelId)
    }

    private fun setupRecyclerView(){
        mAdapter = TasksRecyclerAdapter(requireContext(), this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter
    }

    private fun subscribeObservers(){

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.state.collect {
                    when(it){
                        is LevelUiState.Success -> {
                            activity?.title = it.level.title.plus(" Tasks")
                            currentLevel = it.level
                            mAdapter.setData(currentLevel.tasks)

                            if(currentLevel.isConfirmed){
                                btn_confirm.isEnabled = false
                                btn_confirm.text = "Confirmed"
                                btn_confirm.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                            }

                            if(currentLevel.tasks.count { x -> x.completed }
                                == currentLevel.tasks.count()){
                                btn_confirm.isEnabled = true
                                btn_confirm.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.green))
                            }
                        }
                        is LevelUiState.Error -> {
                            Toast.makeText(requireContext(), it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                        is LevelUiState.Loading -> {
                        }
                    }
                }
            }
        }
    }

    override fun onItemCheck(task: Task) {
        currentLevel.tasks[currentLevel.tasks.indexOf(task)].completed = task.completed
        viewModel.updateLevel(currentLevel)
        if(currentLevel.tasks.count { x -> x.completed }
            == currentLevel.tasks.count()){
            btn_confirm.isEnabled = true
            btn_confirm.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onClick(p0: View?) {
        currentLevel.isConfirmed = true
        viewModel.confirmLevel(currentLevel)
        //Navigation.findNavController(requireView()).navigate()
    }
}