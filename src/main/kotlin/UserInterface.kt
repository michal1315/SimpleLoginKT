class UserInterface {

    private val basicLogic = BasicLogic()
    private val message = Texts()

    fun welcomeSrc() {
        basicLogic.showTxt(message.helloTxt)
        basicLogic.showTxt("1: " + message.signInTxt)
        basicLogic.showTxt("0: " + message.exitProgram + "\n")
        basicLogic.showTxt(message.userHint, false)

    }

    fun loginSrc() {
        basicLogic.showTxt(message.signInHint)

    }

    fun accountSrc() {
        basicLogic.showTxt(message.createAccountYes)
    }
}