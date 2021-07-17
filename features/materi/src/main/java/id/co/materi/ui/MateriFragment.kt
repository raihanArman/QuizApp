package id.co.materi.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.core.data.model.Materi
import id.co.core.data.network.ResponseState
import id.co.materi.R
import id.co.materi.adapter.MateriAdapter
import id.co.materi.databinding.FragmentMateriBinding
import id.co.materi.module.MateriModule.materiModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MateriFragment : Fragment() {

    private lateinit var dataBinding: FragmentMateriBinding
    private val viewModel: MateriViewModel by viewModel()

    private val adapter : MateriAdapter by lazy {
        MateriAdapter{
            showBabByMateri(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_materi, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(materiModule)

        setupAdapter()
        setupObserve()

    }

    private fun setupObserve() {
        viewModel.getMateri.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setDataMateri(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{

                }

            }
        })
    }

    private fun setDataMateri(data: List<Materi>) {
        adapter.setListMateri(data)
    }

    private fun setupAdapter() {
        dataBinding.rvMateri.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataBinding.rvMateri.adapter = adapter
    }


    private fun showBabByMateri(materi: Materi){
        val navController = findNavController()
//        val arg = BabFragmentArgs(materi).toBundle()
        viewModel.setSelectedMateri(materi.id!!)
        val deepLink = Uri.parse("quiz://bab/${materi.id}/${materi.materi}")
        findNavController().navigate(deepLink)

    }

}