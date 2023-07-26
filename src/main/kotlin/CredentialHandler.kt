import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter
import kotlin.random.Random

class CredentialHandler {

    fun hashData(dataToHash: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(dataToHash.toByteArray())
        return DatatypeConverter.printHexBinary(bytes).lowercase()
    }

    fun credentialPassCheck(login: String, password: String): Boolean {
        var passUser = false
        for (line in credentialsArray) {
            val loginFormDb = line[0]
            val salt = line[1]
            val passwordFromDb = line[2]
            if (login == loginFormDb && hashData(password + salt) == passwordFromDb) {
                passUser = true
            }
        }
        return passUser
    }

    fun genRandomData(length: Int = 12): String {
        var iteration = 0
        var randomString = ""
        val charset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        while (iteration < length) {
            val randomNumber = Random.nextInt(0, 61)
            randomString += charset[randomNumber]
            iteration++
        }
        return randomString
    }
}