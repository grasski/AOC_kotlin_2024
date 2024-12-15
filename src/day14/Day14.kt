package day14

import print
import println
import readInput

fun main() {
    fun part1(input: List<String>, gridSize: Pair<Int, Int>): Int {
        var sumQ1 = 0
        var sumQ2 = 0
        var sumQ3 = 0
        var sumQ4 = 0
        input.forEach { line ->
            val split = line.replace("p=", "").replace("v=", "").split(" ").map { it.split(",") }
            val p = Pair(split[0][0].toInt(), split[0][1].toInt())
            val v = Pair(split[1][0].toInt(), split[1][1].toInt())

            var position = p.copy()
            for (i in 0..99){
                var x = (position.first + v.first) % gridSize.first
                if (x < 0){
                    x += gridSize.first
                }
                var y = (position.second + v.second) % gridSize.second
                if (y < 0){
                    y += gridSize.second
                }
                position = Pair(x, y)
            }
            if (position.first < gridSize.first/2){
                if (position.second < gridSize.second/2){
                    sumQ1++
                } else if (position.second > gridSize.second/2){
                    sumQ3++
                }
            } else if (position.first > gridSize.first/2){
                if (position.second < gridSize.second/2){
                    sumQ2++
                } else if (position.second > gridSize.second/2){
                    sumQ4++
                }
            }
        }
        return sumQ1 * sumQ2 * sumQ3 * sumQ4
    }
    
    fun part2(input: List<String>, gridSize: Pair<Int, Int>): Int {
        val grid = buildList { for (i in 0..gridSize.second-1){add(buildList { for (ii in 0..gridSize.first-1) {add(".")} }.toMutableList())} }.toMutableList()

        val robots = mutableMapOf<Int, Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        input.forEachIndexed { index, line ->
            val split = line.replace("p=", "").replace("v=", "").split(" ").map { it.split(",") }
            val p = Pair(split[0][0].toInt(), split[0][1].toInt())
            val v = Pair(split[1][0].toInt(), split[1][1].toInt())
            robots[index] = Pair(Pair(p.first, p.second), Pair(v.first, v.second))
        }

        var seconds = 0
        while(!grid.joinToString("") { it.joinToString("") }.contains("########")){
            robots.forEach { robot ->
                var position = robot.value.first.copy()
                if (robots.none { it.value.first == position && it.key != robot.key }){
                    grid[position.second][position.first] = "."
                }
                val v = robot.value.second

                var x = (position.first + v.first) % gridSize.first
                if (x < 0) {
                    x += gridSize.first
                }
                var y = (position.second + v.second) % gridSize.second
                if (y < 0) {
                    y += gridSize.second
                }
                position = Pair(x, y)

                robots[robot.key] = Pair(position, v)
                grid[position.second][position.first] = "#"
            }
            seconds++
        }
        return seconds
    }
    
    val testInput = readInput("Day14_test")
    val input = readInput("Day14")

    check(part1(testInput, Pair(11, 7)) == 12)
    println(part1(input, Pair(101, 103)))

    println(part2(input, Pair(101, 103)))
}
