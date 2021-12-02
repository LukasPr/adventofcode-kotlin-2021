fun main() {
    println(countNumberOfIncreasingDepth(readInputFile("src/main/resources/day1-input.txt")))
    println(threeMeasurementWindow(readInputFile("src/main/resources/day1-input.txt")))
}

fun countNumberOfIncreasingDepth(listOfDepth: List<String>): Int {
    return listOfDepth
        .map { it.toInt() }
        .zipWithNext()
        .count { it.second>it.first }
}

fun threeMeasurementWindow(listOfDepth: List<String>): Int {
    return listOfDepth
        .asSequence()
        .map { it.toInt() }
        .windowed(3).map { it.sum() }
        .zipWithNext()
        .count { it.second > it.first }
}