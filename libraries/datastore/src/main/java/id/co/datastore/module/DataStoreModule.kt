package id.co.datastore.module

import id.co.datastore.UserDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataStoreModule {
    val dataStoreModule = module{
        single {
            UserDataStore(androidContext())
        }
    }
}