fun main() {
    val (numbersToDraw, bingoBoards) = extractData(readInputFile("src/main/resources/day4-input.txt"))

    for (n in numbersToDraw.indices) {
        val drawnNumbers = numbersToDraw.take(n)
        val winner = bingoBoards.firstOrNull {
            it.hasBingo(drawnNumbers)
        }
        if (winner != null) {
            println("We have a Winner!!!")
            println(winner.toString(drawnNumbers))
            println("Score is: ${winner.score(drawnNumbers)}")
            break
        }
    }
}

fun extractData(input: List<String>): Pair<List<Int>, List<BingoBoard>> {
    val numbersToDraw = input[0].split(",")
        .map { it.toInt() }

    val bingoBoards = input.drop(1)
        .chunked(6)
        .map { it.filterNot(String::isBlank) }
        .map {
            BingoBoard(it.map {
                it.trim().split("""\s+""".toRegex()).map(String::toInt)
            })
        }
    return numbersToDraw to bingoBoards
}

data class BingoBoard(val fields: List<List<Int>>) {
    fun hasBingo(drawnNumbers: Collection<Int>): Boolean {

        return (rows() + cols()).any { rowOrColumn ->
            rowOrColumn.all { number -> number in drawnNumbers }
        }
    }

    fun rows(): List<List<Int>> = fields
    fun cols(): List<List<Int>> = fields.first().indices.map { col -> fields.map { it[col] } }

    fun score(drawnNumbers: Collection<Int>): Int {
        val sumOfAllUnmarkedNumbers = (fields.flatten() - drawnNumbers).sum()
        return sumOfAllUnmarkedNumbers * drawnNumbers.last()
    }

    override fun toString(): String = toString(emptyList())

    fun toString(drawnNumbers: Collection<Int>): String {
        return fields.joinToString(separator = System.lineSeparator(), postfix = System.lineSeparator()) { it ->
            it.joinToString(separator = " ") { number ->
                val n = number.toString().padStart(2)
                if (number in drawnNumbers) "$ANSI_RED$n$ANSI_RESET" else n
            }
        }
    }
}


const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"