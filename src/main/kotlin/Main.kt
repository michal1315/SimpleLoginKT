fun main(args: Array<String>) {

    val basicLogic = BasicLogic()
    val userInterface = UserInterface()
    val dbOperation = DataBaseOperations()
    var userChoice:Int

    fun welcome() {
        userInterface.welcomeSrc()
        userChoice = basicLogic.dataInput().toInt()
        if (userChoice == 1) {
            basicLogic.showTxt("zaloguj")
        }
        if (userChoice == 2) {
            basicLogic.programExit()

        }
    }
    welcome()

}