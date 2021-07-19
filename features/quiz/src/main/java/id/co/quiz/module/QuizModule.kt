package id.co.quiz.module

import id.co.quiz.QuizViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object QuizModule {
    val quizModule = module {
        viewModel {
            QuizViewModel(get())
        }
    }
}