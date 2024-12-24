package com.example.demo.apparel.serviceImplementation;

import com.example.demo.apparel.dto.AttachmentDTO;
import com.example.demo.apparel.dto.CreateCategoryDTO;
import com.example.demo.apparel.dto.CreateCategoryListDTO;
import com.example.demo.apparel.dto.ResponseDTO;
import com.example.demo.apparel.entity.*;
import com.example.demo.apparel.repository.*;
import com.example.demo.apparel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class createCategoryDetailsImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private EntityTypeRepository entityTypeRepository;

    @Autowired
    private CategoryAndSubcategoryMappingRepository categoryAndSubcategoryMappingRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public ResponseEntity<ResponseDTO> createCategoryDetails(CreateCategoryDTO createCategory) {

        Optional<Category> categoryOptional = categoryRepository.findByUniqueKeyAndIsDeleted(createCategory.getUniqueKey(), 0);
        Category category = new Category();

        if (categoryOptional.isEmpty()) {
            category.setUniqueKey(UUID.randomUUID().toString());
            category.setCreatedOn(LocalDateTime.now());
            category.setCreatedBy(1);
        } else {
            category.setId(categoryOptional.get().getId());
            category.setUpdatedBy(1);
            category.setUpdatedOn(LocalDateTime.now());
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "Category Created Successfully"));
    }

    @Override
    public ResponseEntity<ResponseDTO> createCategoryListDetails(CreateCategoryListDTO createCategoryList) {
        try {

            Optional<Category> categoryExist = categoryRepository.findByUniqueKeyAndIsDeleted(createCategoryList.getCategoryId(), 0);
            Optional<Category> categoryByName = categoryRepository.findByNameAndIsDeleted(createCategoryList.getCategoryName(), 0);
            Category category = new Category();

            if (categoryByName.isPresent()) {
                return ResponseEntity.ok().body(new ResponseDTO("The Category is Already Present"));
            }

            if (categoryExist.isPresent() && createCategoryList.getIsDeleted() == 1) {
                Category categoryData =
                        categoryRepository.findByUniqueKeyAndIsDeleted
                                (createCategoryList.getCategoryId(), 0).orElseThrow(() -> new RuntimeException(""));

                Optional<CategoryAndSubcategoryMapping>
                        categoryAndSubcategoryMappingData =
                        categoryAndSubcategoryMappingRepository
                                .findByCategoryAndIsDeletedAndIsActive(categoryData, 0, 1);

                if (categoryAndSubcategoryMappingData.isPresent()) {
                    List<Integer> categoryAndSubcategoryMappingIds =
                            categoryAndSubcategoryMappingData.stream().map(CategoryAndSubcategoryMapping::getId).toList();

                    Optional<List<ProductDetail>> productDetailExistOrNot =
                            productDetailsRepository.
                                    findAllByCategoryAndSubcategoryMappingIdInAndIsDeletedAndIsActive
                                            (categoryAndSubcategoryMappingIds, 0, 1);

                    if (productDetailExistOrNot.isPresent()) {
                        ResponseEntity.internalServerError().body(new ResponseDTO("PRODUCT CANNOT BE DELETED"));
                    }
                    try {
                        int categoryDelete = categoryRepository.UpdateByCategoryByCategoryId(categoryExist.get().getId());
                        int subCategoryDelete = subcategoryRepository.UpdateSubCategoryByCategoryId(categoryExist.get().getId());
                        int categoryAndSubCategoryDelete =
                                categoryAndSubcategoryMappingRepository.
                                        updateCategoryAndSubcategoryMappingByCategoryId(categoryExist.get().getId());
                    }
                    catch (Exception e){
                        return ResponseEntity.internalServerError().body(new ResponseDTO("Problem While Deletion"));
                    }

                    return ResponseEntity.ok().body(new ResponseDTO("Deleted successfully."));
                }
                else{
                    try {
                        int categoryDelete = categoryRepository.UpdateByCategoryByCategoryId(categoryExist.get().getId());
                        int subCategoryDelete = subcategoryRepository.UpdateSubCategoryByCategoryId(categoryExist.get().getId());
                    }
                    catch (Exception e){
                        return ResponseEntity.internalServerError().body(new ResponseDTO("Problem While Deletion"));
                    }
                    return ResponseEntity.ok().body(new ResponseDTO("Deleted successfully."));
                }
            } else {

                category.setName(createCategoryList.getCategoryName());
                category.setDescription(createCategoryList.getDescription());

                if (categoryExist.isEmpty()) {
                    category.setUniqueKey(UUID.randomUUID().toString());
                    category.setCreatedOn(LocalDateTime.now());
                    category.setCreatedBy(1);
                } else {
                    category.setUniqueKey(createCategoryList.getCategoryId());
                    category.setCreatedOn(LocalDateTime.now());
                    category.setUpdatedBy(1);
                }
                Category result = categoryRepository.save(category);

                if (!createCategoryList.getAttachment().isEmpty()) {
                    ResponseEntity<ResponseDTO> attachmentResult = createOrUpdateAttachment(createCategoryList.getAttachment(), result.getId());
                }
            }
        } catch (Exception e) {

        }

        return null;
    }


    public ResponseEntity<ResponseDTO> createOrUpdateAttachment(List<AttachmentDTO> attachDetails, Integer id) {
        try {


            List<Attachment> attachments = attachDetails.stream().map(data -> {

                Optional<Attachment> attachment = attachmentRepository.findByFileUuidAndIsDeleted(data.getFileUuid(), 0);
                Attachment attachDetailsObj = new Attachment();

//            Optional<ProductItem> productItem=productItemRepository.findByUniqueKeyAndIsDeleted(data.getEntityId(),0);

                EntityType entityType = entityTypeRepository.findById(data.getIsImageOrVideo()).orElseThrow(() -> new RuntimeException(""));
                attachDetailsObj.setEntityType(entityType);
                attachDetailsObj.setEntityId(data.getEntityId());
                attachDetailsObj.setFileUuid(data.getFileUuid());
                attachDetailsObj.setMimeType(data.getMimeType());
                attachDetailsObj.setFileSize(data.getFileSize());
                attachDetailsObj.setCoverImage(0); // Temporary Zero

                if (attachment.isEmpty()) {
                    attachDetailsObj.setUniqueKey(UUID.randomUUID().toString());
                    attachDetailsObj.setCreatedOn(LocalDateTime.now());
                    attachDetailsObj.setCreatedBy(1);
                }

                if (attachment.isPresent() && data.getIsDeleted() == 1) {
                    attachDetailsObj.setId(attachment.get().getId());
                    attachDetailsObj.setUniqueKey(attachment.get().getUniqueKey());
                    attachDetailsObj.setUpdatedBy(1);
                    attachDetailsObj.setIsDeleted(1);
                    attachDetailsObj.setUpdatedOn(LocalDateTime.now());
                }
                return attachDetailsObj;
            }).toList();
            attachmentRepository.saveAll(attachments);

            return ResponseEntity.ok().body(new ResponseDTO("Attachment list created."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO("Problem While creation"));
        }
    }
}
