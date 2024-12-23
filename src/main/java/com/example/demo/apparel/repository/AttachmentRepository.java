package com.example.demo.apparel.repository;

import com.example.demo.apparel.dto.AttachmentDTO;
import com.example.demo.apparel.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    Optional<Attachment> findByFileUuidAndIsDeleted(String fileUuid,int isDeleted);
}
