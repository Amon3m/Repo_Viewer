package com.example.repoviewer.repodetails.view

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
import com.bumptech.glide.Glide

import com.example.repoviewer.R
import com.example.repoviewer.databinding.FragmentRepoDetailsBinding
import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.network.ApiState
import com.example.repoviewer.repodetails.viewmodel.DetailsViewModel
import com.example.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class RepoDetailsFragment : Fragment() {
    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var binding: FragmentRepoDetailsBinding
    private var owner: String = ""
    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        owner = RepoDetailsFragmentArgs.fromBundle(requireArguments()).owner
        name = RepoDetailsFragmentArgs.fromBundle(requireArguments()).name
        viewModel.getDetails(owner, name)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(requireContext())) {
                val action =
                    RepoDetailsFragmentDirections.actionRepoDetailsFragmentToIssuesFragment(
                        owner, name
                    )
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(requireContext(), "No network connection", Toast.LENGTH_SHORT).show()


            }
        }

        lifecycleScope.launch {
            viewModel.details.collect {
                when (it) {
                    is ApiState.Success<*> -> {
                        val data = it.data as? DetailsResponse
                        binding.progressBar3.visibility = View.INVISIBLE
                        bindView(data)
                    }

                    is ApiState.Failure -> {
                        Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                        binding.progressBar3.visibility = View.INVISIBLE
                    }

                    else -> {


                        binding.progressBar3.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun bindView(data: DetailsResponse?) {
        binding.repoNameTxt.text = data?.name ?: ""
        binding.ownerTxt.text = data?.owner?.login
        binding.desTxt.text = data?.description
        binding.forksTxt.text = data?.forks.toString()
        binding.starCountTxt.text = data?.stargazersCount.toString()
        binding.createdAtTxt.text = data?.createdAt?.substringAfter("T")
        binding.visibilityTxt.text = data?.visibility
        binding.openIssuesTxt.text = data?.openIssues.toString()
        Glide.with(requireContext())
            .load(data?.owner?.avatarUrl)
            .error(R.drawable.img_error)
            .placeholder(R.drawable.loading_img)
            .into(binding.imageView)
    }

}