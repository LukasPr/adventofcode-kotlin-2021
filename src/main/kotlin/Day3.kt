fun main() {
    println(solvePartOneDay3())
    println(solvePartTwoDay3())
}

fun calculateGammaRate(input: List<String>): String {
    return input.first().indices.map { column ->
        val zero = input.count { it[column] == '0' }
        val one = input.count { it[column] == '1' }
        if (one > zero) '1' else '0'
    }.joinToString("")
}

fun calculateEpsilonRate(input: List<String>): String {
    return input.first().indices.map { column ->
        val zero = input.count { it[column] == '0' }
        val one = input.count { it[column] == '1' }
        if (one < zero) '1' else '0'
    }.joinToString("")
}

fun solvePartOneDay3(): Int {
    val input = readInputFile("src/main/resources/day3-input.txt")
    return calculateGammaRate(input).toInt(2) * calculateEpsilonRate(input).toInt(2)
}

fun findOxygenGeneratorRating(input: List<String>): String {
    return input.first().indices.fold(input) { inputList, i ->
        if (inputList.size == 1) inputList else {
            val split = inputList.partition { it[i] == '1' }
            if (split.first.size >= split.second.size) split.first else split.second
        }
    }.first()
}

fun findCO2ScrubberRating(input: List<String>): String { // 01010
    return input.first().indices.fold(input) { inputList, i ->
        if (inputList.size == 1) inputList else {
            val split = inputList.partition { it[i] == '1' }
            if (split.first.size < split.second.size) split.first else split.second
        }
    }.first()
}

fun solvePartTwoDay3(): Int {
    val input = readInputFile("src/main/resources/day3-input.txt")
    return findOxygenGeneratorRating(input).toInt(2) * findCO2ScrubberRating(input).toInt(2)
}

