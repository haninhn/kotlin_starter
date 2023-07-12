
import OfflineException
import Resource
import com.example.authstarterkotlin.core.utils.UseCase
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.json.JSONObject

class DefaultUseCase<T>(private inline val onRequest: suspend () -> T) : UseCase<T> {
    override suspend fun execute(): Flow<Resource<T>> = channelFlow {
        try {
            send(Resource.Loading<T>())
            val response = onRequest()
            send(Resource.Success<T>(response))
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is ServerResponseException -> {
                    try {
                        send(Resource.Error<T>(message = JSONObject(throwable.response.bodyAsText())["message"] as String))
                    } catch (e: Exception) {
                        send(
                            Resource.Error<T>(
                                message = "Something went wrong , try again later!"
                            )
                        )
                    }
                }
                is OfflineException -> {
                    send(
                        Resource.Error<T>(
                            message = "Please check your internet connection"
                        )
                    )
                }
                else -> {
                    send(
                        Resource.Error<T>(
                            message = throwable.localizedMessage ?: "Unknown Error"
                        )
                    )
                }
            }
        }
    }
}