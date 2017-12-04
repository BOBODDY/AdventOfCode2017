package day4

import java.io.File
import java.io.InputStream


fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day4/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    val phrases = inputString.split("\r\n")
    print(countValidPassphrases(phrases))
}

fun countValidPassphrases(phrases: List<String>): Int {
    var count = 0

    for(phrase in phrases) {
        if(isValidPassphrase(phrase)) {
            count += 1
        }
    }

    return count
}

fun isValidPassphrase(phrase: String): Boolean {
    var wordMap: HashMap<String, Int> = HashMap()

    val words = phrase.split(" ")
    for(word in words) {
        if(wordMap.containsKey(word)) {
            return false
        } else {
            wordMap.put(word, 1)
        }
    }

    return true
}