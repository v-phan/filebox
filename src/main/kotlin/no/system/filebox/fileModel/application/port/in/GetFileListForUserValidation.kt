package no.system.filebox.fileModel.application.port.`in`

interface GetFileListForUserValidation {
    fun userIsOwner(password: String, userID: Int)
}