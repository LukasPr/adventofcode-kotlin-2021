import java.lang.Exception

fun main() {
    println(multiplyHorizontalPositionWithFinalDepth())
    println(part2(readInputFile("src/main/resources/day2-input.txt")))
}

fun multiplyHorizontalPositionWithFinalDepth(): Int {
    val horizontalPosition = calculateHorizontalPosition(readInputFile("src/main/resources/day2-input.txt"))
    val stepsDown = calculateStepsDown(readInputFile("src/main/resources/day2-input.txt"))
    val stepsUp = calculateStepsUp(readInputFile("src/main/resources/day2-input.txt"))

    return horizontalPosition * (-stepsUp + stepsDown)
}

fun calculateHorizontalPosition(listOfInstructions: List<String>): Int {
    return prepareData(listOfInstructions)
        .filter { it.contains("forward") }
        .sumOf { it.last().toInt() }
}

fun calculateStepsDown(listOfInstructions: List<String>): Int {
    return prepareData(listOfInstructions)
        .filter { it.contains("down") }
        .sumOf { it.last().toInt() }
}

fun calculateStepsUp(listOfInstructions: List<String>): Int {
    return prepareData(listOfInstructions)
        .filter { it.contains("up") }
        .sumOf { it.last().toInt() }
}

fun part2 (listOfInstructions: List<String>): Int {

    data class Position(val horizontal: Int, val depth: Int, val aim: Int)

    val finalPositionData = prepareData(listOfInstructions)
        .fold(Position(0, 0, 0)) { acc, listOfInstructions ->
            when (listOfInstructions.first()) {
                "forward" -> acc.copy(horizontal = acc.horizontal + listOfInstructions.last().toInt(), depth = acc.depth + (acc.aim * listOfInstructions.last().toInt()))
                "down" -> acc.copy(aim = acc.aim + listOfInstructions.last().toInt())
                "up" -> acc.copy(aim = acc.aim - listOfInstructions.last().toInt())
                else -> throw Exception("error")
            }
        }
    return finalPositionData.horizontal * finalPositionData.depth
}

fun prepareData(listOfInstructions: List<String>): List<List<String>> {
    return listOfInstructions.windowed(1)
        .map { it -> it.map { it.split(" ") } }
        .flatten()
}