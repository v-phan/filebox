package no.system.filebox.fileModel.adapter.out.persistence

import no.system.filebox.fileModel.domain.FileModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FileRepository : JpaRepository<FileModel, Int> {

    @Query("select f from FileModel f where f.userID = :userID")
    fun getUsersFiles(@Param("userID") userID : Int) : List<FileModel>

}