package id.co.quizapp.module

import id.co.core.domain.iterator.Iterator
import id.co.core.domain.usecase.UseCase
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module{
        factory<UseCase>{
            Iterator(get())
        }
    }
}