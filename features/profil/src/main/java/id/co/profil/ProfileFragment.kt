package id.co.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.profil.databinding.FragmentProfileBinding
import id.co.profil.module.ProfileModule.profileModule
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var dataBinding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModel()

    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(profileModule)

        setupAdapter()
        setupObserve()


    }

    private fun setupObserve() {
        viewModel.getUserById("1").observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setData(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error->{

                }
            }
        })
    }

    private fun setData(data: Users) {
        with(dataBinding){
            tvName.text = data.name
            tvStatus.text = data.status
            tvQuiz.text = data.sumQuiz
            tvScore.text = data.lastScore
        }
    }

    private fun setupAdapter() {
        dataBinding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvHistory.adapter = adapter
    }
}