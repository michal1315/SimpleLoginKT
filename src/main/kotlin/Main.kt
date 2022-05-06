fun main(args: Array<String>) {

    val baseLogic = BaseLogic()
    val userInterface = UserInterface()
    val dbOperation = DataBaseOperations()
    var userChoice:Int

    fun welcome() {
        userInterface.welcomeSrc()
        userChoice = baseLogic.dataInput().toInt()
        if (userChoice == 1) {
            baseLogic.showTxt("zaloguj")
        }
        if (userChoice == 2) {
            baseLogic.programExit()

        }
    }
    welcome()

}