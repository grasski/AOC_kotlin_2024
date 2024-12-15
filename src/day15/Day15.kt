package day15

import readInput

fun main() {
    fun part1(input: List<String>, movements: List<String>): Int {
        val moves = movements.joinToString("").map { it }
        val grid = input.map { it.chunked(1).toMutableList() }.toMutableList()
        val startPosition = Pair(grid.indexOf(grid.find { it.contains("@") }), grid.find { it.contains("@") }!!.indexOf("@"))

        var position = startPosition.copy()
        moves.forEach { move ->
            when (move) {
                '<' -> {
                    var canMove = 1
                    for (i in position.second-1 downTo 0){
                        if (grid[position.first][i] == "."){
                            break
                        } else if (grid[position.first][i] == "#"){
                            canMove = 0
                            break
                        }
                        canMove ++
                    }
                    if (canMove == 1){
                        grid[position.first][position.second] = "."
                        position = position.copy(second = position.second-1)
                        grid[position.first][position.second] = "@"
                    } else if (canMove > 1){
                        for (i in canMove downTo 2){
                            grid[position.first][position.second-i] = "O"
                        }
                        grid[position.first][position.second] = "."
                        grid[position.first][position.second-1] = "@"
                        position = position.copy(second = position.second-1)
                    }
                }
                '>' -> {
                    var canMove = 1
                    for (i in position.second+1..<grid[0].size){
                        if (grid[position.first][i] == "."){
                            break
                        } else if (grid[position.first][i] == "#"){
                            canMove = 0
                            break
                        }
                        canMove ++
                    }
                    if (canMove == 1){
                        grid[position.first][position.second] = "."
                        position = position.copy(second = position.second+1)
                        grid[position.first][position.second] = "@"
                    } else if (canMove > 1){
                        for (i in canMove downTo 2){
                            grid[position.first][position.second+i] = "O"
                        }
                        grid[position.first][position.second] = "."
                        grid[position.first][position.second+1] = "@"
                        position = position.copy(second = position.second+1)
                    }
                }
                '^' -> {
                    var canMove = 1
                    for (i in position.first-1 downTo 0){
                        if (grid[i][position.second] == "."){
                            break
                        } else if (grid[i][position.second] == "#"){
                            canMove = 0
                            break
                        }
                        canMove ++
                    }
                    if (canMove == 1){
                        grid[position.first][position.second] = "."
                        position = position.copy(first = position.first-1)
                        grid[position.first][position.second] = "@"
                    } else if (canMove > 1){
                        for (i in canMove downTo 2){
                            grid[position.first-i][position.second] = "O"
                        }
                        grid[position.first][position.second] = "."
                        grid[position.first-1][position.second] = "@"
                        position = position.copy(first = position.first-1)
                    }
                }
                'v' -> {
                    var canMove = 1
                    for (i in position.first+1..<grid.size){
                        if (grid[i][position.second] == "."){
                            break
                        } else if (grid[i][position.second] == "#"){
                            canMove = 0
                            break
                        }
                        canMove ++
                    }
                    if (canMove == 1){
                        grid[position.first][position.second] = "."
                        position = position.copy(first = position.first+1)
                        grid[position.first][position.second] = "@"
                    } else if (canMove > 1){
                        for (i in canMove downTo 2){
                            grid[position.first+i][position.second] = "O"
                        }
                        grid[position.first][position.second] = "."
                        grid[position.first+1][position.second] = "@"
                        position = position.copy(first = position.first+1)
                    }
                }
            }
        }

        var sum = 0
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, item ->
                if (item == "O"){
                    sum += 100*y + x
                }
            }
        }
        return sum
    }
    
    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day15_test")
    val testInput2 = readInput("Day15_test2")
    val input = readInput("Day15")
    val input2 = readInput("Day15_2")

    check(part1(testInput, testInput2) == 10092)
    println(part1(input, input2))

//    check(part2(testInput) == 0)
//    println(part2(input))
}
