package fm.homework.poke.domain.use_case

import fm.homework.poke.common.Resource
import fm.homework.poke.domain.model.PokemonDetails
import fm.homework.poke.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokeRepository
){
    operator fun invoke(pokemonName: String) : Flow<Resource<PokemonDetails>> = flow {
        try {
            emit(Resource.Loading())
            val pokemon = repository.getPokemon(pokemonName)
            emit(Resource.Success(pokemon))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}