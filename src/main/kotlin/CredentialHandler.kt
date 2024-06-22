import java.security.MessageDigest
import java.util.Base64
import kotlin.random.Random

class CredentialHandler {

    fun hashData(dataToHash: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(dataToHash.toByteArray())
        return Base64.getEncoder().encodeToString(bytes).lowercase()
    }

    fun credentialPassCheck(login: String, password: String): Boolean {
        var passUser = false
        for (line in credentialsArray) {
            val loginFromDb = line[0]
            val salt = line[1]
            val passwordFromDb = line[2]
            if (login == loginFromDb && hashData(password + salt) == passwordFromDb) {
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

    fun passwordValidator(password: String): Boolean {
        var passLong = false
        var digit = false
        var smallChar = false
        var bigChar = false
        if (password.length >= 8) {
            passLong = true
        }
        for (char in password) {
            when (char) {
                in "0123456789" -> digit = true
                in "abcdefghijklmnopqrstuvwxyz" -> smallChar = true
                in "ABCDEFGHIJKLMNOPQRSTUVWXYZ" -> bigChar = true
            }
        }
        return passLong && digit && smallChar && bigChar
    }

    fun loginFreeCheck(login: String): Boolean {
        var loginFree = true
        for (line in credentialsArray) {
            val loginFromDb = line[0]
            if (login == loginFromDb) {
                loginFree = false
            }
        }
        return loginFree
    }
}