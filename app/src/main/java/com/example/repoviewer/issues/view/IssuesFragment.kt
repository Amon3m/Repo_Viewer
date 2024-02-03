package com.example.repoviewer.issues.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repoviewer.databinding.FragmentIssuesBinding
import com.example.repoviewer.issues.viewmodel.IssuesViewModel
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.network.ApiState
import com.example.repoviewer.repodetails.view.RepoDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class IssuesFragment : Fragment() {
    private val viewModel by viewModels<IssuesViewModel>()
    private lateinit var binding: FragmentIssuesBinding
    lateinit var issuesAdapter: IssuesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val owner = RepoDetailsFragmentArgs.fromBundle(requireArguments()).owner
       val name = RepoDetailsFragmentArgs.fromBundle(requireArguments()).name
        viewModel.getIssues(owner,name)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIssuesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        issuesAdapter = IssuesAdapter(requireContext())

        binding.recyclerView.apply {
            adapter = issuesAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        lifecycleScope.launch {
            viewModel.issues.collect {
                when (it) {
                    is ApiState.Success<*> -> {
                        val data = it.data as? List<IssuesResponse>
                        binding.progressBar4.visibility = View.INVISIBLE
                        issuesAdapter.submitList(data)
                    }

                    is ApiState.Failure -> {
                        Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                        binding.progressBar4.visibility = View.INVISIBLE
                    }

                    else -> {


                        binding.progressBar4.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

}

