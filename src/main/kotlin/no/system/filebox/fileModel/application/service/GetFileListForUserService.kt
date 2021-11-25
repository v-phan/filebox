package no.system.filebox.fileModel.application.service

import no.system.filebox.fileModel.domain.FileModel
import no.system.filebox.fileModel.application.port.`in`.GetFileListForUserUseCase
import no.system.filebox.fileModel.application.port.`in`.GetFileListForUserValidation
import no.system.filebox.fileModel.application.port.out.GetFileListForUserPort
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetFileListForUserService(private val getFileListForUserPort: GetFileListForUserPort, @Qualifier("redisTemplateUser") private val redisTemplate: RedisTemplate<String, String>) : GetFileListForUserUseCase, GetFileListForUserValidation {

    private val hashOperations: HashOperations<String, String, String> = redisTemplate.opsForHash()

    override fun getFiles(password: String, userID: Int): List<FileModel> {
        userIsOwner(password, userID)
        return getFileListForUserPort.getFileListForUser(userID)
    }

    override fun userIsOwner(password: String, userID: Int) {
        if(hashOperations.get("$password","userID")?.toInt()!=userID) throw ResponseStatusException(HttpStatus.FORBIDDEN, "Passord samsvarer ikke med brukerID")
    }
}