package com.purlewave.di.modules

import com.purlewave.data.network.NetworkApi
import com.purlewave.data.local.room.LocalDao
import com.purlewave.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesSampleRepository(networkApi: NetworkApi, localDao: LocalDao): NetworkRepository {
        return NetworkRepository(networkApi, localDao)
    }
}