package no.system.filebox.fileModel.domain

import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
class FileModel(@Column(nullable = false) val fileName: String,
                @Column(nullable = false) val fileType: String,
                @Column(nullable = false) val filedata: ByteArray,
                @Column(nullable = false) val userID: Int) {


    val uploadedDate: String = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
    @Id
    @GeneratedValue(generator = "file_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "file_id_seq",
        sequenceName = "file_id_seq",
        allocationSize = 50)
    var fileID: Int? = null
}