package com.example.gismeteoapitestapp.di

import android.content.Context
import com.example.gismeteoapitestapp.data.GismeteoApiService
import com.example.gismeteoapitestapp.data.RetrofitClient
import com.example.gismeteoapitestapp.interactor.CachingInteractor
import com.example.gismeteoapitestapp.interactor.CachingInteractorImpl
import com.example.gismeteoapitestapp.interactor.WeatherInteractor
import com.example.gismeteoapitestapp.interactor.WeatherInteractorImpl
import com.example.gismeteoapitestapp.repository.CachingRepository
import com.example.gismeteoapitestapp.repository.CachingRepositoryImpl
import com.example.gismeteoapitestapp.repository.WeatherRepository
import com.example.gismeteoapitestapp.repository.WeatherRepositoryImpl
import com.example.gismeteoapitestapp.viewmodel.MainViewModel
import com.example.gismeteoapitestapp.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [MainModule::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): MainComponent
    }

    val weatherInteractor: WeatherInteractor
    val cachingInteractor: CachingInteractor

    fun viewModelsFactory(): ViewModelFactory
}

@Module
class MainModule {

    @Provides
    fun provideWeatherInteractor(
        weatherRepository: WeatherRepository
    ): WeatherInteractor {
        return WeatherInteractorImpl(weatherRepository)
    }

    @Provides
    fun provideWeatherRepository(
        gismeteoApiService: GismeteoApiService
    ): WeatherRepository {
        return WeatherRepositoryImpl(gismeteoApiService)
    }

    @Provides
    fun provideGisMeteoApiService(): GismeteoApiService {
        return RetrofitClient.getInstance().create(GismeteoApiService::class.java)
    }

    @Provides
    fun provideCachingInteractor(
        cachingRepository: CachingRepository
    ): CachingInteractor {
        return CachingInteractorImpl(cachingRepository)
    }

    @Provides
    fun provideCachingRepository(
        context: Context
    ): CachingRepository {
        return CachingRepositoryImpl(context)
    }

}