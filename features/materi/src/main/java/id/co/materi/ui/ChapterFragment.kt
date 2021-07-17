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
import id.co.core.data.model.Chapter
import id.co.core.data.network.ResponseState
import id.co.materi.R
import id.co.materi.adapter.ChapterAdapter
import id.co.materi.adapter.ChapterAdapterClickListener
import id.co.materi.databinding.FragmentChapterBinding
import id.co.materi.module.MateriModule
import id.co.materi.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ChapterFragment : Fragment(), ChapterAdapterClickListener {

    private lateinit var dataBinding: FragmentChapterBinding
    val viewModel: MateriViewModel by viewModel()

    private val adapter : ChapterAdapter by lazy {
        ChapterAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chapter, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(MateriModule.materiModule)

        setupAdapter()
        setupObserve()

        dataBinding.ivBack.setOnClickListener {
//            val deepLink = Uri.parse("quiz://materi")
            findNavController().navigateUp()
        }

    }

    private fun setupObserve() {
        val id = arguments?.getString("id")
        val title = arguments?.getString("title")
        dataBinding.tvTitle.text = title
        viewModel.getBabByMateri(id!!).observe(viewLifecycleOwner, Observer { response ->
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

    private fun setDataMateri(data: List<Chapter>) {
        adapter.setBab(data)
        Constant.listChapter = data as ArrayList<Chapter>
    }

    private fun setupAdapter() {
        dataBinding.rvBab.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvBab.adapter = adapter
    }

    override fun onItemClicked(position: Int, moduleId: String?) {
        viewModel.setSelectedChapter(moduleId!!, position)
        val deepLink = Uri.parse("quiz://content/${moduleId}/${position}")
        findNavController().navigate(deepLink)
    }
}