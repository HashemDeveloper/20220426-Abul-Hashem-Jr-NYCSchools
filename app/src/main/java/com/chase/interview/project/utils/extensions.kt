import android.content.Context
import java.io.IOException
import java.io.InputStream

fun readAssetFile(context: Context, fileName: String): String? {
    val json: String? = try {
        val `is`: InputStream = context.assets.open(fileName)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, charset("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
    return json
}