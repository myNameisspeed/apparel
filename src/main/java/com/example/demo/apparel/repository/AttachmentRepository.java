package com.example.demo.apparel.repository;

import com.example.demo.apparel.dto.AttachmentDTO;
import com.example.demo.apparel.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

    @Query("SELECT a from Attachment a where a.fileUuid= :fileUuid and a.isDeleted =0")
    Optional<Attachment> findByFileUuidAndIsDeleted(@Param("fileUuid")String fileUuid,@Param("isDeleted") int isDeleted);
}
