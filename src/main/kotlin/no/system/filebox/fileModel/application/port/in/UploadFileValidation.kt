package no.system.filebox.fileModel.application.port.`in`

interface UploadFileValidation {
    fun userIsOwner(password: String, userID: Int)
}