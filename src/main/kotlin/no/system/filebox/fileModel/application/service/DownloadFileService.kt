package no.system.filebox.fileModel.application.service

import no.system.filebox.fileModel.application.port.`in`.DownloadFileUseCase
import no.system.filebox.fileModel.application.port.`in`.DownloadFileValidation
import no.system.filebox.fileModel.application.port.out.DownloadFilePort
import no.system.filebox.fileModel.domain.FileModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DownloadFileService(private val downloadFilePort: DownloadFilePort, @Qualifier("redisTemplateUser") private val redisTemplate: RedisTemplate<String, String>) : DownloadFileUseCase, DownloadFileValidation {
    private val hashOperations: HashOperations<String, String, String> = redisTemplate.opsForHash<String, String>()

    override fun downloadFile(hash: String, fileID: Int): FileModel? {
        val file = downloadFilePort.downloadFile(fileID)
        doesFileExist(fileID)
        userIsOwner(hash,file!!.userID)
        return file
    }

    override fun doesFileExist(fileID: Int) {
        downloadFilePort.downloadFile(fileID)?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Fil med filID  finnes ikke")
    }

    override fun userIsOwner(hash: String, userID: Int){
        if(userID != hashOperations.get("session:$hash","userID")?.toInt()){
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Filen tilhører en annen bruker")
        }
    }
}