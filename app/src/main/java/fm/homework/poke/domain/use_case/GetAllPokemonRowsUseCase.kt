package fm.homework.poke.domain.use_case

import fm.homework.poke.common.Resource
import fm.homework.poke.domain.repository.PokeRepository
import fm.homework.poke.presentation.selector.RowPokemonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPokemonRowsUseCase @Inject constructor(
    private val repository: PokeRepository
){
    operator fun invoke() : Flow<Resource<List<RowPokemonData>>> = flow {
        try {
            emit(Resource.Loading())
            val pokemons = repository.getAllPokemons()
            val pokemonsData = pokemons.map { pokemon ->
                RowPokemonData(
                    pokemon.name,
                    pokemon.types[0],
                    pokemon.caught
                )
            }
            emit(Resource.Success(pokemonsData))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}