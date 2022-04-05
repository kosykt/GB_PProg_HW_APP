package ru.kosykt.githubusers.ui.usersfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.kosykt.githubusers.data.GithubUsersRepositoryImpl
import ru.kosykt.githubusers.databinding.FragmentUsersBinding
import ru.kosykt.githubusers.domain.GetUsersListUseCase

class UsersFragment : Fragment() {

    private val githubUsersRepository = GithubUsersRepositoryImpl()
    private val getUsersListUseCase = GetUsersListUseCase(githubUsersRepository)

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding ?: throw RuntimeException("FragmentUsersBinding? = null")

    private val adapter by lazy {
        UsersAdapter()
    }

    private val viewModel: UsersFragmentViewModel by lazy {
        ViewModelProvider(
            this,
            UsersFragmentViewModelFactory(getUsersListUseCase)
        )[UsersFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fuRv.adapter = adapter
        viewModel.users.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}