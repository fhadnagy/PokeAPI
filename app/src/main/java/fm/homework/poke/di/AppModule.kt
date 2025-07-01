package fm.homework.poke.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fm.homework.poke.common.Constants
import fm.homework.poke.data.remote.PokeAPI
import fm.homework.poke.data.repository.PokeRepositoryImpl
import fm.homework.poke.data.repository.PokeRepositoryMockImpl
import fm.homework.poke.domain.repository.PokeRepository
import fm.homework.poke.domain.use_case.GetAllPokemonRowsUseCase
import fm.homework.poke.domain.use_case.GetPokemonUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokeApi(): PokeAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePokeRepository(api: PokeAPI): PokeRepository {
        return PokeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetPokemonUseCase(repository: PokeRepository): GetPokemonUseCase {
        return GetPokemonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllPokemonRowsUseCase(repository: PokeRepository): GetAllPokemonRowsUseCase {
        return GetAllPokemonRowsUseCase(repository)
    }


}