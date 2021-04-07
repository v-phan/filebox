package no.system.filebox.fileModel.application.port.`in`

import no.system.filebox.fileModel.domain.FileModel


interface DownloadFileUseCase {
    fun downloadFile(hash: String, fileID : Int) : FileModel?
}