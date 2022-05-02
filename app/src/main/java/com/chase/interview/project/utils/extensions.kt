import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.*
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
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

/**
 * extracts first word from @param input
 */
fun getFirstWord(input: String): String {
    for (i in input.indices) {
        if (input[i] == ' ') {
            return input.substring(0, i)
        }
    }
    return input
}
/**
 * Bolds the first word from the sentence. And, if the @param isUnderline is true, will
 * underline the sentence from the @param end of first bold word. Temporarily using end+1 to start index after the ':'
 */
fun boldFirstWord(end: Int, sentence: String, textView: AppCompatTextView, isUnderLine: Boolean) {
    if (sentence.isNotEmpty()) {
        val fancySentence = SpannableStringBuilder(sentence)
        fancySentence.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (isUnderLine) {
            //TODO: Check if firstWord.length-1 == ':' if true set pointer to end+1 else set pointer end
            fancySentence.setSpan(UnderlineSpan(), end+1, sentence.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        textView.text = fancySentence
    }
}