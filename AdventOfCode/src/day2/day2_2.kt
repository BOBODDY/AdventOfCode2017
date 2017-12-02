package day2

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
//    filename = "test2Input"

    val inputStream: InputStream = File("src/day2/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    print(createChecksum2(inputString))
}

fun createChecksum2(file: String): Int {
    val rows = file.split("\r\n")
    var checksum = 0

    for(row in rows) {
        val rowAsNumbers = convertRow(row)

        val difference = findEvenlyDivisibleValues(rowAsNumbers)

        checksum += difference
    }

    return checksum
}

fun findEvenlyDivisibleValues(numbers: IntArray): Int {
    var dividend = 0
    var divisor = 0

    for(i in 0 until numbers.size) {
        for(j in 0 until numbers.size) {
            if(i != j) {
                if(numbers[i] % numbers[j] == 0) {
                    divisor = numbers[j]
                    dividend = numbers[i]
                } else if(numbers[j] % numbers[i] == 0) {
                    divisor = numbers[i]
                    dividend = numbers[j]
                }
            }
        }
    }

    return dividend / divisor
}