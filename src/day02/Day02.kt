package day02

import readInput
import kotlin.math.abs

fun main() {
    fun checkLevel(level: List<Int>): Boolean{
        var direction = 0

        level.forEachIndexed { index, i ->
            if (index < level.size - 1) {
                val nextLevel = level[index + 1]

                val diff = i - nextLevel
                if (index == 0){
                    direction = if (diff < 0) 1 else if (diff > 0) -1 else 0
                }
                val nowDirection = if (diff < 0) 1 else if (diff > 0) -1 else 0
                if (abs(diff) > 3 || nowDirection != direction || direction == 0) {
                    return false
                }
            }
        }
        return true
    }


    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }

            if (checkLevel(levels)) {
                sum ++
            }
        }
        return sum
    }
    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }.toMutableList()

            if (checkLevel(levels)) {
                sum ++
            } else{
                var i = 0
                while (i < levels.size) {
                    val item = levels.removeAt(i)
                    if (checkLevel(levels)) {
                        sum ++
                        break
                    }
                    levels.add(i, item)
                    i++
                }
            }
        }
        return sum
    }
    
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(testInput) == 2)
    println(part1(input))

    check(part2(testInput) == 4)
    println(part2(input))
}
