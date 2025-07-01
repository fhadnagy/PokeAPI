package fm.homework.poke.data.remote

import fm.homework.poke.data.remote.dto.PokemonDetailDTO
import fm.homework.poke.data.remote.dto.PokemonListDTO
import fm.homework.poke.data.remote.dto.TypeDetailDTO
import fm.homework.poke.data.remote.dto.TypeListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPI {

    // Get all types
    @GET("type")
    suspend fun getAllTypes(): TypeListDTO

    //Get all Pokemons
    @GET("type")
    suspend fun getAllPokemons(): PokemonListDTO

    // Get Pokémon list by type
    @GET("type/{type}")
    suspend fun getPokemonByType(@Path("type") type: String): TypeDetailDTO

    // Get detailed info of a Pokémon
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailDTO
}