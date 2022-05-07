import java.io.File

val sharedData = SharedData()

class DataBaseOperations {

    private val dbFile = File(sharedData.dbFile)

    private fun dbLenCount(): Int {
        var lineCount = 0
        dbFile.forEachLine { lineCount++ }
        return lineCount
    }

    fun dbRead() {
        dbFile.forEachLine { line ->
            val arrayElement = line.split(", ")
            sharedData.credentialsArray.add(arrayElement)
        }
    }

    fun writeToDb(dataToWrite: String) {
        dbFile.appendText(dataToWrite + System.getProperty("line.separator"))
    }

    fun createFile(nameCreatedFile: String) {
        val fileToCreate = File(nameCreatedFile)
        fileToCreate.createNewFile()
    }

    fun dbDataFormatBuilder(login: String, salt: String, password: String): String {
        return "$login, $salt, $password"
    }

    fun dbCorruptionCheck(): Boolean {
        var dbCorrupted = false
        var elementsAmount = 0
        for (line in sharedData.credentialsArray) {
            elementsAmount += line.size
        }
        if (elementsAmount % 3 != 0 || dbLenCount() == 0) {
            dbCorrupted = true
        }
        return dbCorrupted
    }
}