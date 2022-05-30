fun main(args: Array<String>) {

    val basicLogic = BasicLogic()
    val userInterface = UserInterface()
    val dbOperation = DataBaseOperations()
    val message = Texts()
    var userChoice: Int


    fun startup() {
        if (!dbOperation.dbCorruptionCheck()) {
            dbOperation.dbRead()
        } else {
            //db rebuild
        }
    }

    fun program() {
        dbOperation.dbRead()
        while (true) {
            userInterface.welcomeSrc()
            userChoice = basicLogic.dataInput().toInt()
            if (userChoice == 1) {
                while (true) {
                    userInterface.loginSrc()
                    basicLogic.showTxt(message.loginTxt, false)
                    val login = basicLogic.dataInput()
                    basicLogic.showTxt(message.passwordTxt, false)
                    val password = basicLogic.dataInput()
                    if (basicLogic.credentialParser(login, password)) {
                        basicLogic.showTxt(message.goodCredential)
                        basicLogic.programExit()
                    } else {
                        basicLogic.showTxt(message.badCredential)
                        basicLogic.attemptCounter("badCredentials")
                    }
                }
            }
            if (userChoice == 0) {
                basicLogic.programExit()
            } else {
                basicLogic.attemptCounter("mainMenuBadChoose")
            }
        }
    }

    startup()
    program()
}