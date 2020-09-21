import com.mittas.moviebrowser.data.network.Endpoints
import com.mittas.moviebrowser.data.network.RemoteApiFactories
import com.mittas.moviebrowser.data.network.movies.MoviesRemoteApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteApi(): MoviesRemoteApi = RemoteApiFactories.createApi(Endpoints.MOVIES, MoviesRemoteApi::class.java)
}