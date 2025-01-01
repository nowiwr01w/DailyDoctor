package extensions

/**
 * You should use the following {1}, {2}, {3}... format for args
 * Sample: Resend after {1} seconds
 */
fun String.format(vararg args: Any): String {
    var formattedString = this
    args.forEachIndexed { index, arg ->
        val placeholder = "\\{${index + 1}\\}".toRegex()
        formattedString = formattedString.replace(placeholder, arg.toString())
    }
    return formattedString
}
