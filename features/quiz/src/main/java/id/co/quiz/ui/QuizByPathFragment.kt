package id.co.quiz.ui

import android.content.Intent
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
import id.co.core.data.model.Quiz
import id.co.core.data.network.ResponseState
import id.co.quiz.R
import id.co.quiz.adapter.QuizAdapter
import id.co.quiz.databinding.FragmentQuizByPathBinding
import id.co.quiz.module.QuizModule.quizModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class QuizByPathFragment : Fragment() {

    private lateinit var binding: FragmentQuizByPathBinding
    private val adapter: QuizAdapter by lazy{
        QuizAdapter{
            goToQuiz(it)
        }
    }


    private val viewModel: QuizViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_by_path, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadKoinModules(quizModule)

        setupAdapter()
        setupObserve()


        binding.ivBack.setOnClickListener {
//            val deepLink = Uri.parse("quiz://materi")
            findNavController().navigateUp()
        }
    }

    private fun setupAdapter() {
        binding.rvQuiz.layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuiz.adapter = adapter
    }

    private fun setupObserve() {
        val id = arguments?.getString("id")
        val title = arguments?.getString("title")
        binding.tvTitle.text = title
        viewModel.getQuizByPath(id!!).observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    setDataQuiz(response.data)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun setDataQuiz(data: List<Quiz>) {
        adapter.setQuizList(data)
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