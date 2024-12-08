package day07

import println
import readInput

fun main() {
    fun generateCombinations(
        items: List<String>,
        depth: Int,
        current: List<String> = emptyList()
    ): List<List<String>> {
        if (depth == 0) {
            return listOf(current)
        }
        val combinations = mutableListOf<List<String>>()
        for (item in items) {
            combinations += generateCombinations(items, depth - 1, current + item)
        }
        return combinations
    }

    fun part1(input: List<String>): Long {
        var sum: Long = 0

        input.forEach { line ->
            val split = line.split(": ")
            val value = split[0].toLong()
            val numbers = split[1].split(" ").map { it.toLong() }

            val combinations = generateCombinations(listOf("*", "+"), numbers.size-1)
            run {
                combinations.forEach { combination ->
                    var eq = numbers[0]
                    numbers.subList(1, numbers.size).forEachIndexed{ index, number ->
                        if (combination[index] == "+"){
                            eq = eq + number
                        } else{
                            eq = eq * number
                        }
                    }
                    if (eq == value){
                        sum += value
                        return@run
                    }
                }
            }
        }
        return sum
    }
    fun part2(input: List<String>): Long {
        var sum: Long = 0

        input.forEach { line ->
            val split = line.split(": ")
            val value = split[0].toLong()
            val numbers = split[1].split(" ").map { it.toLong() }

            val combinations = generateCombinations(listOf("*", "+", "||"), numbers.size-1)
            run {
                combinations.forEach { combination ->
                    var eq = numbers[0]
                    numbers.subList(1, numbers.size).forEachIndexed{ index, number ->
                        if (combination[index] == "+"){
                            eq = eq + number
                        } else if (combination[index] == "*"){
                            eq = eq * number
                        } else{
                            eq = (eq.toString() + number.toString()).toLong()
                        }
                    }
                    if (eq == value){
                        sum += value
                        return@run
                    }
                }
            }
        }
        return sum
    }
    
    val testInput = readInput("Day07_test")
    val input = readInput("Day07")

    check(part1(testInput) == 3749L)
    println(part1(input))

    check(part2(testInput) == 11387L)
    println(part2(input))
}
