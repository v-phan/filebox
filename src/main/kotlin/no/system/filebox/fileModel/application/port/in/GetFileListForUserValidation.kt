package no.system.filebox.fileModel.application.port.`in`

interface GetFileListForUserValidation {
    fun userIsOwner(hash: String, userID: Int)
}