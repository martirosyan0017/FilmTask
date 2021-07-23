import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity
import com.example.telcelltask.movieapp.AppApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(context: FragmentActivity): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = AppApplication.appContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return !(networkInfo == null || !networkInfo.isConnected)
    }

    class NoConnectivityException: IOException()
}