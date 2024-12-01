import org.jetbrains.kotlin.incremental.createDirectory


plugins {
    kotlin("jvm") version "2.0.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }

    task("generateNextDay") {
        doLast {
            val prevDayNum = File(projectDir, "src").listFiles()
                    ?.mapNotNull { it.name.takeIf { name -> name.startsWith("day") } }
                    ?.mapNotNull { it.removePrefix("day").toIntOrNull() }
                    ?.maxOrNull() ?: 0

            val newDayNum = String.format("%02d", prevDayNum + 1)
            File("$projectDir/src/day$newDayNum").createDirectory()
            File("$projectDir/src/day$newDayNum", "Day$newDayNum.txt").createNewFile()
            File("$projectDir/src/day$newDayNum", "Day${newDayNum}_test.txt").createNewFile()
            File("$projectDir/src/day$newDayNum", "Day$newDayNum.kt").writeText(
"""package day$newDayNum

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }
    fun part2(input: List<String>): Int {
        return 0
    }
    
    val testInput = readInput("Day${newDayNum}_test")
    val input = readInput("Day$newDayNum")

    check(part1(testInput) == 0)
    println(part1(input))

    check(part2(testInput) == 0)
    println(part2(input))
}
"""
            )
        }
    }
}
