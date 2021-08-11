package id.co.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Quiz
import id.co.core.data.network.ResponseState
import id.co.search.databinding.FragmentSearchBinding
import id.co.search.module.SearchModule.searchModule
import org.koin.core.context.loadKoinModules


class SearchFragment : Fragment() {

    private lateinit var dataBinding: FragmentSearchBinding
    private val adapter: SearchAdapter by lazy{
        SearchAdapter{
            goToQuiz(it)
        }
    }

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentSearchBinding.inflate(inflater)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadKoinModules(searchModule)

        setupAdapter()
        setupObserve("")

        dataBinding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                setupObserve(s.toString())
            }

        })

    }

    private fun setupObserve(text: String) {
        viewModel.getQuizSearch(text).observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setData(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setData(data: List<Quiz>) {
        adapter.setQuizList(data)
    }

    private fun setupAdapter() {
        dataBinding.rvQuiz.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvQuiz.adapter = adapter
    }

    private fun goToQuiz(quiz: Quiz){
        viewModel.getQuizCheck(quiz.id!!).observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    if(response.data.startQuiz){
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("quiz://quiz/${quiz.id}"))
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(), "Anda sudah mengambil quiz ini", Toast.LENGTH_SHORT).show()
                    }
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}