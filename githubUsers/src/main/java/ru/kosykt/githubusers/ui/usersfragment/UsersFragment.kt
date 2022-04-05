package ru.kosykt.githubusers.ui.usersfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.kosykt.githubusers.databinding.FragmentUsersBinding
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.utils.imageloader.ImageLoader
import javax.inject.Inject

class UsersFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding ?: throw RuntimeException("FragmentUsersBinding? = null")

    private val adapter by lazy {
        UsersAdapter()
    }

    private val viewModel: UsersFragmentViewModel by lazy {
        ViewModelProvider(this, vmFactory)[UsersFragmentViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as GithubSubcomponentProvider).initSubcomponent()
            .inject(this)
        super.onAttach(context)
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
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}