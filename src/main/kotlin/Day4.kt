fun main() {
    println(test(readInputFile("src/main/resources/day4-input.txt")))
}

fun test(input: List<String>): List<List<List<String>>> {
    val numbersToDraw = input[0].split(",")
        .map { it.toInt() }

    val bingoBoards = input.drop(1)
        .chunked(6)
        .map { it -> it.filterNot { it.isBlank() } }
        .map { it ->
            it.map { it ->
                it.split(" ").filterNot { it.isBlank() }
            }
        }
    println(numbersToDraw)


    return bingoBoards
}

data class SingleField(val number: Int, val matching: Boolean = false)

data class BingoBoard(val fields: List<MutableList<SingleField>>){
    val row = fields[0].indices
    val column = fields.indices

    companion object {
        fun fromCollection(coll: List<List<Int>>): BingoBoard {
            return BingoBoard(coll.map { row -> row.map { field -> SingleField(field) }.toMutableList() })
        }
    }
}