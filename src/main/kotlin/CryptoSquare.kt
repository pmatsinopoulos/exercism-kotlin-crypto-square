private fun normalize(originalText: String): String {
    val regex = Regex("\\s|\\W")
    return originalText.replace(regex, "").lowercase()
}

private fun calculateColumnsAndRows(length: Int): Array<Int> {
    var c = 1
    var r = length / c
    while (c - r > 1 || c < r) {
        c++
        r = length / c
        if (length % c >= 1) r++
    }
    return arrayOf(c, r)
}

private fun breakTextByColumns(text: String, columns: Int): Array<String> {
    var element = ""
    var result = arrayOf<String>()
    text.forEach { char ->
        if (element.length < columns) {
            element = element + char.toString()
        } else {
            result = result.plus(element)
            element = char.toString()
        }
    }
    if (element.isNotBlank()) {
        result = result.plus(element.padEnd(columns, ' '))
    }
    return result
}

private fun buildEncodedText(input: Array<String>): String {
    var result = ""
    for (i in 0 until input[0].length) {
        for (j in 0 until input.size) {
            result = "${result}${input[j][i]}"
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

        val textByColumns: Array<String> = breakTextByColumns(result, columnsAndRows[0])
        result = buildEncodedText(textByColumns)

        return result
    }
}
