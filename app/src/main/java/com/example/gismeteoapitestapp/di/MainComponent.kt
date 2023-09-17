package com.example.gismeteoapitestapp.di

import com.example.gismeteoapitestapp.interactor.WeatherInteractor
import com.example.gismeteoapitestapp.interactor.WeatherInteractorImpl
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
    fun provideWeatherInteractor(): WeatherInteractor {
        return WeatherInteractorImpl()
    }
}

fun MainComponent.constructViewModel() = MainViewModel(
    weatherInteractor = weatherInteractor
)