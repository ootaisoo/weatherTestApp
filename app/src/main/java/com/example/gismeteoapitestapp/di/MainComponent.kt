package com.example.gismeteoapitestapp.di

import android.content.Context
import com.example.gismeteoapitestapp.data.CustomLogger
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
import com.example.gismeteoapitestapp.router.MainRouter
import com.example.gismeteoapitestapp.router.MainRouterImpl
import com.example.gismeteoapitestapp.viewmodel.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

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
    fun provideGisMeteoApiService(
        client: OkHttpClient
    ): GismeteoApiService {
        return RetrofitClient.getInstance(client).create(GismeteoApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor.Logger
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(logger)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideCustomLogger(
        cachingRepository: CachingRepository
    ): HttpLoggingInterceptor.Logger {
        return CustomLogger(cachingRepository)
    }

    @Provides
    fun provideCachingInteractor(
        cachingRepository: CachingRepository
    ): CachingInteractor {
        return CachingInteractorImpl(cachingRepository)
    }

    @Provides
    fun provideCachingRepository(
        context: Context,
        gson: Gson,
    ): CachingRepository {
        return CachingRepositoryImpl(context, gson)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideMainRouter(): MainRouter {
        return MainRouterImpl()
    }

}