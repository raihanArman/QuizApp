package id.co.materi.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Materi
import id.co.core.data.network.ResponseState
import id.co.materi.R
import id.co.materi.adapter.MateriByCategoryAdapter
import id.co.materi.databinding.FragmentMateriByCategoryBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MateriByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentMateriByCategoryBinding
    private val viewModel: MateriViewModel by viewModel()
    private val adapter: MateriByCategoryAdapter by lazy {
        MateriByCategoryAdapter{
            showQuiz(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_materi_by_category, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObserve()


        binding.ivBack.setOnClickListener {
//            val deepLink = Uri.parse("quiz://materi")
            findNavController().navigateUp()
        }
    }

    private fun setupAdapter() {
        binding.rvMateri.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMateri.adapter = adapter
    }

    private fun setupObserve() {
        val id = arguments?.getString("id")
        val title = arguments?.getString("title")
        binding.tvTitle.text = title
        viewModel.getPathByCategory(id!!).observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setDataMateri(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun setDataMateri(data: List<Materi>) {
        adapter.setListMateri(data)
    }


    private fun showQuiz(materi: Materi){
        val deepLink = Uri.parse("quiz://quizbypath/${materi.id}/${materi.materi}")
        findNavController().navigate(deepLink)
    }

}