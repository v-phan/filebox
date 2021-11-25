package no.system.filebox.fileModel.application.port.`in`

import no.system.filebox.fileModel.domain.FileModel

interface DownloadFileValidation {
    fun doesFileExist(fileID: Int)
    fun userIsOwner(password: String, userID: Int)
}