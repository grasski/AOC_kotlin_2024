package day08

import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val antennas = input.flatMap { it.chunked(1) }.distinct().toMutableList()
        antennas.remove(".")
        val grid = input.map { it.chunked(1).toMutableList() }.toMutableList()
        val newGrid = grid.map { it.toMutableList() }.toMutableList()

        antennas.forEach { c ->
            val indexes = grid.mapIndexed { index, strings ->
                val f = strings.indexOf(c)
                if (f != -1){
                    Pair(index, f)
                } else{
                    Pair(-1,-1)
                }
            }.toMutableList()
            indexes.removeAll { it == Pair(-1,-1) }

            while(indexes.size > 1){
                var i = 1
                val first = indexes.first()
                while (i < indexes.size){
                    val second = indexes[i]

                    val x = abs(first.first - second.first)
                    val y = abs(first.second - second.second)

                    val xFirst = first.first - x
                    val yFirst = if (first.second > second.second) first.second + y else first.second - y

                    val xSecond = second.first + x
                    val ySecond = if (first.second < second.second) second.second + y else second.second - y

                    if (xFirst >= 0 && xFirst < grid.size && yFirst >= 0 && yFirst < grid.size){
                        newGrid[xFirst][yFirst] = "#"
                    }
                    if (xSecond >= 0 && xSecond < grid.size && ySecond >= 0 && ySecond < grid.size){
                        newGrid[xSecond][ySecond] = "#"
                    }

                    i ++
                }

                indexes.removeFirst()
            }
        }

        return newGrid.flatten().count { it == "#" }
    }
    fun part2(input: List<String>): Int {
        val antennas = input.flatMap { it.chunked(1) }.distinct().toMutableList()
        antennas.remove(".")
        val grid = input.map { it.chunked(1).toMutableList() }.toMutableList()
        val newGrid = grid.map { it.toMutableList() }.toMutableList()

        antennas.forEach { c ->
            val indexes = grid.mapIndexed { index, strings ->
                val f = strings.indexOf(c)
                if (f != -1){
                    Pair(index, f)
                } else{
                    Pair(-1,-1)
                }
            }.toMutableList()
            indexes.removeAll { it == Pair(-1,-1) }

            while(indexes.size > 1){
                var i = 1
                val first = indexes.first()
                while (i < indexes.size){
                    val second = indexes[i]

                    val x = abs(first.first - second.first)
                    val y = abs(first.second - second.second)

                    var xFirst = first.first - x
                    var yFirst = if (first.second > second.second) first.second + y else first.second - y
                    while(xFirst >= 0 && xFirst < grid.size && yFirst >= 0 && yFirst < grid.size){
                        newGrid[xFirst][yFirst] = "#"

                        xFirst -= x
                        yFirst = if (first.second > second.second) yFirst + y else yFirst - y
                    }

                    var xSecond = second.first + x
                    var ySecond = if (first.second < second.second) second.second + y else second.second - y
                    while (xSecond >= 0 && xSecond < grid.size && ySecond >= 0 && ySecond < grid.size){
                        newGrid[xSecond][ySecond] = "#"

                        xSecond += x
                        ySecond = if (first.second < second.second) ySecond + y else ySecond - y
                    }

                    i ++
                }

                indexes.removeFirst()
            }
        }
        return newGrid.flatten().count { it != "." }
    }
    
    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    check(part1(testInput) == 14)
    println(part1(input))

    check(part2(testInput) == 34)
    println(part2(input))
}
