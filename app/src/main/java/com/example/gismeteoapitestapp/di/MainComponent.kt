package com.example.gismeteoapitestapp.di

import android.content.Context
import com.example.gismeteoapitestapp.data.CustomLogger
import com.example.gismeteoapitestapp.data.GismeteoApiService
import com.example.gismeteoapitestapp.data.RetrofitClient
import com.example.gismeteoapitestapp.data.repository.CachingRepository
import com.example.gismeteoapitestapp.data.repository.CachingRepositoryImpl
import com.example.gismeteoapitestapp.data.repository.WeatherRepository
import com.example.gismeteoapitestapp.data.repository.WeatherRepositoryImpl
import com.example.gismeteoapitestapp.interactor.ForecastInteractor
import com.example.gismeteoapitestapp.interactor.ForecastInteractorImpl
import com.example.gismeteoapitestapp.interactor.HistoryInteractor
import com.example.gismeteoapitestapp.interactor.HistoryInteractorImpl
import com.example.gismeteoapitestapp.ui.router.MainRouter
import com.example.gismeteoapitestapp.ui.router.MainRouterImpl
import com.example.gismeteoapitestapp.ui.viewmodel.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Scope

@Scope
annotation class MainComponentScope

@MainComponentScope
@Component(modules = [MainModule::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): MainComponent
    }

    val forecastInteractor: ForecastInteractor
    val historyInteractor: HistoryInteractor

    fun viewModelsFactory(): ViewModelFactory
}

@Module
class MainModule {

    @MainComponentScope
    @Provides
    fun provideWeatherInteractor(
        weatherRepository: WeatherRepository,
        cachingRepository: CachingRepository
    ): ForecastInteractor {
        return ForecastInteractorImpl(weatherRepository, cachingRepository)
    }

    @MainComponentScope
    @Provides
    fun provideWeatherRepository(
        gismeteoApiService: GismeteoApiService
    ): WeatherRepository {
        return WeatherRepositoryImpl(gismeteoApiService)
    }

    @MainComponentScope
    @Provides
    fun provideGisMeteoApiService(
        client: OkHttpClient
    ): GismeteoApiService {
        return RetrofitClient.getInstance(client).create(GismeteoApiService::class.java)
    }

    @MainComponentScope
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

    @MainComponentScope
    @Provides
    fun provideCustomLogger(
        cachingRepository: CachingRepository
    ): HttpLoggingInterceptor.Logger {
        return CustomLogger(cachingRepository)
    }

    @MainComponentScope
    @Provides
    fun provideCachingInteractor(
        cachingRepository: CachingRepository
    ): HistoryInteractor {
        return HistoryInteractorImpl(cachingRepository)
    }

    @MainComponentScope
    @Provides
    fun provideCachingRepository(
        context: Context,
        gson: Gson,
    ): CachingRepository {
        return CachingRepositoryImpl(context, gson)
    }

    @MainComponentScope
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @MainComponentScope
    @Provides
    fun provideMainRouter(): MainRouter {
        return MainRouterImpl()
    }

}