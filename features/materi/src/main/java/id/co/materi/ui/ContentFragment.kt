package id.co.materi.ui

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.co.core.data.model.Chapter
import id.co.core.data.network.ResponseState
import id.co.materi.R
import id.co.materi.databinding.FragmentContentBinding
import id.co.materi.module.MateriModule
import id.co.materi.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ContentFragment : Fragment() {

    private lateinit var dataBinding: FragmentContentBinding
    private val viewModel: MateriViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(MateriModule.materiModule)

        setupObservable()

        dataBinding.btnNext.setOnClickListener {
            viewModel.setNextPage()
            getData()
        }
        dataBinding.btnPrev.setOnClickListener {
            viewModel.setPrevPage()
            getData()
        }

        dataBinding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setupObservable() {
        val id = arguments?.getString("id")
        val position = arguments?.getString("position")
        viewModel.setSelectedChapter(id!!, position!!.toInt())
        getData()
    }

    private fun getData() {
        viewModel.selectedChapter.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is ResponseState.Success ->{
                    if (response.data != null) {
                        val content = response.data
//                        dataBinding?.progressBar?.visibility = View.GONE
                        setDataContent(content)
                        setButtonNextPrevState()
                    }
                }
                is ResponseState.Loading ->{
//                    dataBinding?.progressBar?.visibility = View.VISIBLE
                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setButtonNextPrevState() {
        viewModel.position.observe(viewLifecycleOwner, Observer { position->
            if(position > 0){
                if(Constant.listChapter.size-1 == position){
                    dataBinding.btnPrev.visibility = View.VISIBLE
                    dataBinding.btnNext.visibility = View.INVISIBLE
                }else{
                    dataBinding.btnPrev.visibility = View.VISIBLE
                    dataBinding.btnNext.visibility = View.VISIBLE
                }
            }else{
                dataBinding.btnPrev.visibility = View.INVISIBLE
                dataBinding.btnNext.visibility = View.VISIBLE
            }
        })
    }

    private fun setDataContent(content: Chapter) {
        with(dataBinding){
            tvTitle.text = content.name
            tvContent.text = HtmlCompat.fromHtml(content.content!!, FROM_HTML_MODE_COMPACT)
            if(content.image != null){
                ivContent.visibility = View.VISIBLE
                Glide.with(this@ContentFragment)
                    .load(content.image)
                    .into(ivContent)
            }else{
                ivContent.visibility = View.GONE
            }
        }
    }
}