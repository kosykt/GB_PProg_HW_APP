package ru.kosykt.githubusers.ui.usersfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kosykt.githubusers.databinding.FragmentUsersBinding
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.githubusers.domain.models.DomainUserModel
import ru.kosykt.utils.NetworkStatus
import ru.kosykt.utils.imageloader.ImageLoader
import ru.kosykt.utils.myNetworkStatus
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
        UsersAdapter(imageLoader, this::navigateToDetailsFragment)
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
        myNetworkStatus(requireContext().applicationContext).observe(viewLifecycleOwner) {
            when (it) {
                NetworkStatus.AVAILABLE -> {
                    subscribeRx()
                }
                NetworkStatus.LOST -> {
                    Toast.makeText(context, "connection lost", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun subscribeRx() {
        viewModel.users
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    adapter.submitList(list)
                },
                {
                    Log.e(this.javaClass.simpleName, "throwable: $it", it)
                }
            )
    }

    private fun navigateToDetailsFragment(model: DomainUserModel) {
        findNavController().navigate(
            UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(model)
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}