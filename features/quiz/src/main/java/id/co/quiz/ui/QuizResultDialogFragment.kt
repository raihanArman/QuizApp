package id.co.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.quiz.R
import id.co.quiz.adapter.QuizResultAdapter
import id.co.quiz.databinding.FragmentQuizResultDialogBinding
import id.co.room.entity.QuizResultEntity


class QuizResultDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentQuizResultDialogBinding

    private val adapter: QuizResultAdapter by lazy {
        QuizResultAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quiz_result_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        val list: ArrayList<QuizResultEntity> = arguments?.getParcelableArrayList("quiz")!!
        val listQuiz = mutableListOf<QuizResultEntity>()
        listQuiz.add(QuizResultEntity(0, ""))
        listQuiz.addAll(list)
        adapter.setListQuiz(listQuiz)

        binding.tvBack.setOnClickListener {
            dismiss()
        }

    }


    private fun setupAdapter() {
        binding.rvQuiz.layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuiz.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null){
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}