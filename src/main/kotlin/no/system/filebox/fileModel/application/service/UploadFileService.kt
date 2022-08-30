package no.system.filebox.fileModel.application.service

import no.system.filebox.fileModel.application.port.`in`.UploadFileUseCase
import no.system.filebox.fileModel.application.port.`in`.UploadFileValidation
import no.system.filebox.fileModel.application.port.out.UploadFilePort
import no.system.filebox.fileModel.domain.FileModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@Service
class UploadFileService(private val uploadFilePort: UploadFilePort, @Qualifier("redisTemplateUser") private val redisTemplate: RedisTemplate<String, String>) : UploadFileUseCase, UploadFileValidation {

    private val hashOperations: HashOperations<String, String, String> = redisTemplate.opsForHash()

    override fun uploadFile(file: MultipartFile, userID: Int): FileModel {
        file.contentType?: throw Exception("Filen mangler contentType, går ikke an å lagre")
        val filename = file.originalFilename?.let { StringUtils.cleanPath(it) }?: throw Exception("Fant ikke filnavnet")
        return uploadFilePort.uploadFile(FileModel(filename, file.contentType!!, file.bytes,userID))
    }

    override fun userIsOwner(password: String, userID: Int) {
        if(hashOperations.get("$password","userID")?.toInt()!=userID) throw ResponseStatusException(HttpStatus.FORBIDDEN, "Password samsvarer ikke med brukerID. $password + $userID")
    }
}