package com.example.repoviewer.repo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repoviewer.databinding.FragmentRepoBinding
import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.model.RepositoryEntity
import com.example.repoviewer.network.ApiState
import com.example.repoviewer.repo.viewmodel.RepoViewModel
import com.example.utils.NetworkUtils
import com.example.utils.NetworkUtils.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RepoFragment : Fragment(), OnRepoClickListener {
    private val viewModel by viewModels<RepoViewModel>()
    private lateinit var binding: FragmentRepoBinding
    lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRepositories(requireContext())

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoAdapter = RepoAdapter(requireContext(), this)

        binding.recyclerview.apply {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        lifecycleScope.launch {
            viewModel.repositories.collect {
                when (it) {
                    is ApiState.Success<*> -> {
                        val data = it.data as? List<RepositoryEntity>
                        Log.e("heeeloss in frag", "${data}")
                        binding.progressBar.visibility = View.INVISIBLE
                        repoAdapter.submitList(data)
                    }

                    is ApiState.Failure -> {
                        Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                    }

                    else -> {


                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }


    }

    override fun onRepoClick(repositoryEntity: RepositoryEntity?) {
        if (isNetworkAvailable(requireContext())) {
            val owner = repositoryEntity?.owner ?: ""
            val name = repositoryEntity?.name ?: ""
            val action = RepoFragmentDirections.actionRepoFragmentToRepoDetailsFragment(
                owner, name
            )
            Navigation.findNavController(requireView()).navigate(action)
        }
        else{
            Toast.makeText(requireContext(), "No network connection", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onShowStars(repositoryEntity: RepositoryEntity?) {
        if (isNetworkAvailable(requireContext())) {

            val owner = repositoryEntity?.owner ?: ""
            val name = repositoryEntity?.name ?: ""
            viewModel.getStars(owner, name)}
        else{
                Toast.makeText(requireContext(), "No network connection", Toast.LENGTH_SHORT).show()
            }
    }


}