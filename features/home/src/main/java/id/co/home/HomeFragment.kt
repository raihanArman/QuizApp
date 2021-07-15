package id.co.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.home.databinding.FragmentHomeBinding
import id.co.home.module.HomeModule.homeModule
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private val popularAdapter: PopularAdapter by lazy {
        PopularAdapter()
    }

    private val categoryAdapter: CategoryAdapter by lazy{
        CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(homeModule)

        setupAdapter()

        loadUsersData()
        loadMateri()
        loadCategory()

    }

    private fun loadCategory() {
        viewModel.getCategory().observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setDataCategory(response.data)
                }
                is ResponseState.Error ->{

                }
            }
        })
    }

    private fun setDataCategory(data: List<Category>) {
        categoryAdapter.setListCategory(data)
    }

    private fun loadMateri() {
        viewModel.getMateri().observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setDataMateri(response.data)
                }
                is ResponseState.Error ->{

                }
            }
        })
    }

    private fun setDataMateri(data: List<Materi>) {
        popularAdapter.setListMateri(data)
    }

    private fun loadUsersData() {
        viewModel.getUsersById().observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setDataUsers(response.data)
                }
                is ResponseState.Error -> {
//                    dataBinding.progressCircular.visibility = View.GONE
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading -> {
//                    dataBinding.progressCircular.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setDataUsers(data: Users) {
        with(dataBinding){
            tvName.text = data.name
            tvStatus.text = data.status
        }
    }

    private fun setupAdapter() {
        dataBinding.rvPopular.apply {
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = horiz
            adapter = popularAdapter
        }


        dataBinding.rvCategory.apply {
            val horiz = GridLayoutManager(requireContext(), 2)
            layoutManager = horiz
            adapter = categoryAdapter
        }
    }
}