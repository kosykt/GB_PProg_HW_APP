package ru.kosykt.githubusers.ui.userdetailsfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kosykt.githubusers.databinding.FragmentUserDetailsBinding
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.utils.NetworkStatus
import ru.kosykt.utils.myNetworkStatus
import javax.inject.Inject

class UserDetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val args by navArgs<UserDetailsFragmentArgs>()

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding? = null")

    private val viewModel: UserDetailsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[UserDetailsViewModel::class.java]
    }

    private val adapter by lazy {
        UserDetailsAdapter()
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
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.udRv.adapter = adapter
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
        viewModel.getDetails(args.domainUserModel.reposUrl)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}