package day3

fun main(args: Array<String>) {

}

fun createSpiral(number: Int, n: Int) {
    var spiral: Array<Array<Int>> = Array(n, {
        Array(n, {
            0
        })
    })

    val center = n / 2
    var i = 0
    var j = 0
    var largestValue = 0

    while(largestValue < number) {
        if(j == 0 && i == 0) {
            spiral[center][center] = 1
            largestValue = 1
        } else {

        }
    }
}