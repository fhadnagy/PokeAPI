package fm.homework.poke.domain.use_case

import fm.homework.poke.common.Resource
import fm.homework.poke.domain.model.Type
import fm.homework.poke.domain.repository.PokeRepository
import fm.homework.poke.presentation.selector.RowPokemonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllTypesUseCase @Inject constructor(
    private val repository: PokeRepository
){
    operator fun invoke() : Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val types = repository.getAllTypes()
            emit(Resource.Success(types.map { it.name }))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}