package day04

import println
import readInput


fun main() {
    fun List<List<String>>.transpose(): List<String> {
        return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] }.joinToString("") }.filter { it.length > 1 }
    }

    fun part1(input: List<String>): Int {
        val regex1 = Regex("XMAS")
        val regex2 = Regex("SAMX")

        var sum = 0
        input.forEach { line ->
            val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
            sum += count
        }
        input.map { it.split("") }.transpose().forEach { line ->
            val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
            sum += count
        }


        var x = 0
        var y = 0
        // Diagonal Left to Right bottom
        for (i in input.indices) {
            var line = ""
            while (y < input.size) {
                val c = input[x][y]
                line += c
                x ++
                y ++
            }
            val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
            sum += count

            x = 0
            y = i+1
        }


        x = 0
        y = 0
        // Diagonal Left to Bottom right
        for (i in input.indices) {
            var line = ""
            while (x < input.size) {
                val c = input[x][y]
                line += c
                x ++
                y ++
            }
            if (i > 0){
                val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
                sum += count
            }
            x = i + 1
            y = 0
        }

        y = input.size -1
        x = 0
        // Diagonal Right to Left bottom
        for (i in input.indices.reversed()) {
            var line = ""
            while (y >= 0) {
                val c = input[x][y]
                line += c
                x ++
                y --
            }
            val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
            sum += count

            x = 0
            y = i-1
        }


        y = input.size -1
        x = 0
        // Diagonal Right to Bottom left
        for (i in input.indices) {
            var line = ""
            while (x < input.size) {
                val c = input[x][y]
                line += c
                x ++
                y --
            }
            if (i > 0){
                val count = regex1.findAll(line).toList().size + regex2.findAll(line).toList().size
                sum += count
            }

            x = i+1
            y = input.size -1
        }

        return sum
    }
    fun part2(input: List<String>): Int {
        var sum = 0

        for (i in input.indices){
            var y = 0
            while (y < input.size-2){
                if (i < input.size-2){
                    val slice1 = input[i].slice(y..y+2)
                    val slice2 = input[i+1].slice(y..y+2)
                    val slice3 = input[i+2].slice(y..y+2)

                    if (slice2[1] == 'A'){
                        val x1 = if (slice1[0] == 'M' && slice3[2] == 'S') 1 else 0
                        val x2 = if (slice1[2] == 'M' && slice3[0] == 'S') 1 else 0
                        val x3 = if (slice3[0] == 'M' && slice1[2] == 'S') 1 else 0
                        val x4 = if (slice3[2] == 'M' && slice1[0] == 'S') 1 else 0

                        if ((x1 + x2 + x3 + x4) >= 2){
                            sum ++
                        }
                    }
                } else{
                    break
                }
                y +=1
            }
        }

        return sum
    }
    
    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(testInput) == 18)
    println(part1(input))

    check(part2(testInput) == 9)
    println(part2(input))
}
