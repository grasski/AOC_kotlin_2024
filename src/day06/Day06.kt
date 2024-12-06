package day06

import readInput


fun goUp(grid: MutableList<MutableList<String>>, position: Triple<Int, Int, String>): Pair<MutableList<MutableList<String>>, Triple<Int, Int, String>> {
    var i = position.first
    while(i > 0){
        val next = grid[i-1][position.second]
        if (next == "#"){
            break
        }
        grid[i-1][position.second] = "X"

        i --
    }
    var pos = Triple(i, position.second, "^")

    if (i <= 0){
        return Pair(grid, pos)
    } else{
        pos = Triple(pos.first, pos.second, ">")
        return Pair(grid, pos)
    }
}
fun goRight(grid: MutableList<MutableList<String>>, position: Triple<Int, Int, String>): Pair<MutableList<MutableList<String>>, Triple<Int, Int, String>> {
    var i = position.second
    while(i < grid.size-1){
        val next = grid[position.first][i+1]
        if (next == "#"){
            break
        }
        grid[position.first][i+1] = "X"

        i ++
    }
    var pos = Triple(position.first, i, ">")

    if (i >= grid.size-1){
        return Pair(grid, pos)
    } else{
        pos = Triple(pos.first, pos.second, "v")
        return Pair(grid, pos)
    }
}
fun goDown(grid: MutableList<MutableList<String>>, position: Triple<Int, Int, String>): Pair<MutableList<MutableList<String>>, Triple<Int, Int, String>> {
    var i = position.first
    while (i < grid.size-1){
        val next = grid[i+1][position.second]
        if (next == "#"){
            break
        }
        grid[i+1][position.second] = "X"

        i ++
    }
    var pos = Triple(i, position.second, "v")

    if (i >= grid.size-1){
        return Pair(grid, pos)
    } else{
        pos = Triple(pos.first, pos.second, "<")
        return Pair(grid, pos)
    }
}
fun goLeft(grid: MutableList<MutableList<String>>, position: Triple<Int, Int, String>): Pair<MutableList<MutableList<String>>, Triple<Int, Int, String>> {
    var i = position.second
    while (i > 0){
        val next = grid[position.first][i-1]
        if (next == "#"){
            break
        }
        grid[position.first][i-1] = "X"

        i --
    }
    var pos = Triple(position.first, i, "<")

    if (i <= 0){
        return Pair(grid, pos)
    } else{
        pos = Triple(pos.first, pos.second, "^")
        return Pair(grid, pos)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.chunked(1).toMutableList() }.toMutableList()

        var startDir = "^"
        val position = Triple(grid.indexOf(grid.find { it.contains("^") }), grid.find { it.contains("^") }!!.indexOf("^"), startDir)
        grid[position.first][position.second] = "X"

        var (newGrid, pos) = goUp(grid, position)
        while(startDir != pos.third){
            startDir = pos.third
            when (startDir) {
                ">" -> {
                    val result = goRight(newGrid, pos)
                    newGrid = result.first
                    pos = result.second
                }
                "<" -> {
                    val result = goLeft(newGrid, pos)
                    newGrid = result.first
                    pos = result.second
                }
                "v" -> {
                    val result = goDown(newGrid, pos)
                    newGrid = result.first
                    pos = result.second
                }
                "^" -> {
                    val result = goUp(newGrid, pos)
                    newGrid = result.first
                    pos = result.second
                }
            }
        }

        return newGrid.flatten().count { it == "X" }
    }
    fun part2(input: List<String>): Int {
        var grid = input.map { it.chunked(1).toMutableList() }.toMutableList()
        val tempGrid = grid.map { it.toMutableList() }.toMutableList()

        var sum = 0
        for (x in grid.indices){
            for (y in grid.indices){
                if (grid[x][y] == "#" || grid[x][y] == "^"){
                    continue
                }
                grid[x][y] = "#"

                var startDir = "^"
                val position = Triple(grid.indexOf(grid.find { it.contains("^") }), grid.find { it.contains("^") }!!.indexOf("^"), startDir)
                grid[position.first][position.second] = "X"

                var (newGrid, pos) = goUp(grid, position)
                var r = 0
                while(startDir != pos.third){
                    startDir = pos.third
                    when (startDir) {
                        ">" -> {
                            val result = goRight(newGrid, pos)
                            newGrid = result.first
                            pos = result.second
                        }
                        "<" -> {
                            val result = goLeft(newGrid, pos)
                            newGrid = result.first
                            pos = result.second
                        }
                        "v" -> {
                            val result = goDown(newGrid, pos)
                            newGrid = result.first
                            pos = result.second
                        }
                        "^" -> {
                            val result = goUp(newGrid, pos)
                            newGrid = result.first
                            pos = result.second
                        }
                    }
                    r ++

                    if (r >= 1000){ // Might need bigger number for different puzzle inputs
                        sum ++
                        break
                    }
                }

                grid = tempGrid.map { it.toMutableList() }.toMutableList()
            }
        }
        return sum
    }
    
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    check(part1(testInput) == 41)
    println(part1(input))

    check(part2(testInput) == 6)
    println(part2(input))
}
