package com.example.demo.apparel.serviceImplementation;

import com.example.demo.apparel.dto.AttachmentDTO;
import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.CreateCategoryListDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.Attachment;
import com.example.demo.apparel.entity.Category;
import com.example.demo.apparel.repository.AttachmentRepository;
import com.example.demo.apparel.repository.CategoryRepository;
import com.example.demo.apparel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class createCategoryDetailsImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;



    @Override
    public ResponseEntity<ResponseDTO> createCategoryDetails(CreateCategoryDTO createCategory) {

       Optional<Category> categoryOptional= categoryRepository.findByUniqueKeyAndIsDeleted(createCategory.getUniqueKey(),0);
       Category category = new Category();

       if(categoryOptional.isEmpty()){
           category.setUniqueKey(UUID.randomUUID().toString());
           category.setCreatedOn(LocalDateTime.now());
           category.setCreatedBy(1);
       }
       else {
           category.setId(categoryOptional.get().getId());
           category.setUpdatedBy(1);
           category.setUpdatedOn(LocalDateTime.now());
       }
       categoryRepository.save(category);
       return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,"Category Created Successfully"));
    }

    @Override
    public ResponseEntity<ResponseDTO> createCategoryListDetails(CreateCategoryListDTO createCategoryList) {

        Optional<Category> categoryExist=categoryRepository.findByUniqueKeyAndIsDeleted(createCategoryList.getCategoryId(),0);
        Optional<Category> categoryByName=categoryRepository.findByNameAndIsDeleted(createCategoryList.getCategoryName(),0);

        Category category =new Category();


        if(categoryExist.isPresent() && createCategoryList.getIsDeleted()==1){

        }
        else{

            category.setName(createCategoryList.getCategoryName());
            category.setDescription(createCategoryList.getDescription());

            if(categoryExist.isEmpty()){
                category.setUniqueKey(UUID.randomUUID().toString());
                category.setCreatedOn(LocalDateTime.now());
                category.setCreatedBy(1);
            }else{
                category.setUniqueKey(createCategoryList.getCategoryId());
                category.setCreatedOn(LocalDateTime.now());
                category.setUpdatedBy(1);
            }
           Category result= categoryRepository.save(category);

            if(!createCategoryList.getAttachment().isEmpty()){
                ResponseEntity<ResponseDTO> attachmentResult= createOrUpdateAttachment(createCategoryList.getAttachment(),result.getId());
            }
        }

        return null;
    }

    public ResponseEntity<ResponseDTO>  createOrUpdateAttachment(List<AttachmentDTO> AttachDetails, Integer id){

//        for(AttachmentDTO data:AttachDetails){
//
//            Attachment attachment = attachmentRepository.findByFileUuidAndIsDeleted(AttachmentDTO)
//        }
        return null;
    }
}
