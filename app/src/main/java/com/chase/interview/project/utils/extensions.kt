import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.appcompat.widget.AppCompatTextView
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
fun getFirstWord(input: String): String {
    for (i in input.indices) {
        if (input[i] == ' ') {
            return input.substring(0, i)
        }
    }
    return input
}
fun boldFirstWord(end: Int, sentence: String, textView: AppCompatTextView) {
    if (sentence.isNotEmpty()) {
        val fancySentence = SpannableStringBuilder(sentence)
        fancySentence.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = fancySentence
    }
}