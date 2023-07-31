import kotlin.math.ceil
import kotlin.math.sqrt

private fun normalize(originalText: String): String {
    val regex = Regex("\\s|\\W")
    return originalText.replace(regex, "").lowercase()
}

private fun calculateColumnsAndRows(length: Int): List<Int> {
    val columns = ceil(sqrt(length.toDouble())).toInt()
    val rows = ceil(length.toDouble() / columns).toInt()
    return listOf(columns, rows)
}

private fun breakTextByColumns(text: String, columns: Int): List<String> {
    return text.chunked(columns) { c -> c.padEnd(columns).toString() }
}

private fun buildEncodedText(input: List<String>): String {
    var result = ""
    for (i in 0 until input[0].length) {
        for (inputPhrase in input) {
            result = "${result}${inputPhrase[i]}"
        }
        if (i < input[0].length - 1) result = "$result "
    }
    return result
}

object CryptoSquare {
    fun ciphertext(plaintext: String): String {
        if (plaintext.isBlank()) {
            return ""
        }
        var result = normalize(plaintext)
        val length = result.length

        val columnsAndRows = calculateColumnsAndRows(length)

        val textByColumns: List<String> = breakTextByColumns(result, columnsAndRows[0])
        result = buildEncodedText(textByColumns)

        return result
    }
}
