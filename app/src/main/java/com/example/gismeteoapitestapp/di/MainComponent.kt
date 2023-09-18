package com.example.gismeteoapitestapp.di

import com.example.gismeteoapitestapp.data.GismeteoApiService
import com.example.gismeteoapitestapp.data.RetrofitClient
import com.example.gismeteoapitestapp.interactor.WeatherInteractor
import com.example.gismeteoapitestapp.interactor.WeatherInteractorImpl
import com.example.gismeteoapitestapp.repository.WeatherRepository
import com.example.gismeteoapitestapp.repository.WeatherRepositoryImpl
import com.example.gismeteoapitestapp.viewmodel.MainViewModel
import com.example.gismeteoapitestapp.viewmodel.ViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [MainModule::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(target: MainViewModel)

    val weatherInteractor: WeatherInteractor

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

}

fun MainComponent.constructViewModel() = MainViewModel(
    weatherInteractor = weatherInteractor
)