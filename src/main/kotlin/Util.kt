import java.io.File

fun readInputFile(fileName: String): List<String> = File(fileName).readLines()