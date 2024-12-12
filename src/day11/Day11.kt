package day11

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val line = input[0].split(" ").map { it.toLong() }.toMutableList()

        for(i in 0..24){
            val tempLine = line.map { it }.toMutableList()
            var index = 0

            tempLine.forEach { number:Long ->
                if(number == 0L){
                    line[index] = 1
                } else if (number.toString().length % 2 == 0){
                    val strNum = number.toString()

                    line[index] = strNum.take(strNum.length/2).toLong()
                    line.add(index+1, strNum.takeLast(strNum.length/2).toLong())
                    index++

                } else{
                    line[index] = number * 2024
                }
                index++
            }
        }

        return line.size
    }
    
    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day11_test")
    val input = readInput("Day11")

    check(part1(testInput) == 55312)
    println(part1(input))

    check(part2(testInput) == 0)
    println(part2(input))
}
