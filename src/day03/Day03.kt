package day03

import readInput
import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val regex = Regex("mul[(]\\d{1,3},\\d{1,3}[)]")

        var sum = 0
        input.forEach { line ->
            val results = regex.findAll(line).map { it.value }.toList()

            for (result in results){
                val numbers = "\\d{1,3}".toRegex().findAll(result).map { it.value.toInt() }.toList()
                sum += numbers[0] * numbers[1]
            }
        }
        return sum
    }
    fun part2(input: List<String>): Int {
        val regex = Regex("mul[(]\\d{1,3},\\d{1,3}[)]")
        val regexDo = Regex("do[(][)]")
        val regexDont = Regex("don't[(][)]")

        var sum = 0
        input.joinToString().also { line ->
            var doIndex = 0
            var dontIndex = 0
            var index = 0
            var result: MatchResult? = regex.find(line, index)
            var can = true

            while (result != null) {
                doIndex = regexDo.findAll(line.take(result.range.first), doIndex).lastOrNull()?.range?.first ?: 0
                dontIndex = regexDont.findAll(line.take(result.range.first), dontIndex).lastOrNull()?.range?.first ?: 0

                can = doIndex >= dontIndex
                if (can){
                    val numbers = "\\d{1,3}".toRegex().findAll(result.value).map { it.value.toInt() }.toList()
                    sum += numbers[0] * numbers[1]
                }

                index = result.range.last
                result = regex.find(line, index)
            }
        }
        return sum
    }
    
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(testInput) == 161)
    println(part1(input))

    check(part2(testInput) == 48)
    println(part2(input))
}
