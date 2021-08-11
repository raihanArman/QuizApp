package id.co.profil

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.History
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

        dataBinding.tvLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setMessage("Apakah anda yakin ingin logout ?")
            alertDialog.setPositiveButton("Ya", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    logoutProses()
                }
            })
            alertDialog.setNegativeButton("Tidak", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }
            })
            alertDialog.show()
        }


    }

    private fun logoutProses() {
        viewModel.logoutUser().observe(viewLifecycleOwner, Observer {response->
            when(response){
                is ResponseState.Success ->{
                    gotoLogin(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun gotoLogin(data: String) {
        Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("quiz://login"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setupObserve() {
        viewModel.getUserById().observe(viewLifecycleOwner, Observer { response ->
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

        viewModel.getHistoryByUser().observe(viewLifecycleOwner, Observer {response->
            when(response){
                is ResponseState.Success ->{
                    setDataHistory(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error->{

                }
            }
        })
    }

    private fun setDataHistory(data: List<History>) {
        adapter.setListHistory(data)
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