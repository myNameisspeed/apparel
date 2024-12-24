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

            if (categoryByName.isPresent() && createCategoryList.getCategoryId().isEmpty()) {
                return ResponseEntity.ok().body(new ResponseDTO("The Category is Already Present"));
            }

            if (categoryExist.isPresent() && createCategoryList.getIsDeleted() == 1) {

                Category categoryData = categoryRepository.findByUniqueKeyAndIsDeleted
                        (createCategoryList.getCategoryId(), 0).orElseThrow(() -> new RuntimeException(""));

                Optional<CategoryAndSubcategoryMapping> categoryAndSubcategoryMappingData = categoryAndSubcategoryMappingRepository
                        .findByCategoryAndIsDeletedAndIsActive(categoryData, 0, 1);

                if (categoryAndSubcategoryMappingData.isPresent()) {

                    List<Integer> categoryAndSubcategoryMappingIds =
                            categoryAndSubcategoryMappingData.stream().map(CategoryAndSubcategoryMapping::getId).toList();

                    Optional<List<ProductDetail>> productDetailExistOrNot =
                            productDetailsRepository.
                                    findAllByCategoryAndSubcategoryMappingIdInAndIsDeletedAndIsActive
                                            (categoryAndSubcategoryMappingIds, 0, 1);

                    if (productDetailExistOrNot.isPresent()) {
                       return ResponseEntity.internalServerError().body(new ResponseDTO("PRODUCT CANNOT BE DELETED"));
                    }
                }
                try {
                    int categoryDelete = categoryRepository.UpdateByCategoryByCategoryId(categoryExist.get().getId());
                    int subCategoryDelete = subcategoryRepository.UpdateSubCategoryByCategoryId(categoryExist.get().getId());
                    int categoryAndSubCategoryDelete =
                            categoryAndSubcategoryMappingRepository.
                                    updateCategoryAndSubcategoryMappingByCategoryId(categoryExist.get().getId());
                } catch (Exception e) {
                    System.out.println("Exception " + e.getMessage());
                    return ResponseEntity.internalServerError().body(new ResponseDTO("Problem while deletion"));
                }

                return ResponseEntity.ok().body(new ResponseDTO("Deleted successfully."));

//                } else {
//                    try {
//                        int categoryDelete = categoryRepository.UpdateByCategoryByCategoryId(categoryExist.get().getId());
//                        int subCategoryDelete = subcategoryRepository.UpdateSubCategoryByCategoryId(categoryExist.get().getId());
//
//                    } catch (Exception e) {
//                        System.out.println("Exception " +e.getMessage());
//                        return ResponseEntity.internalServerError().body(new ResponseDTO("Problem while deletion"));
//
//                    }
//                    return ResponseEntity.ok().body(new ResponseDTO("Deleted successfully."));
//                }
            } else {
                category.setName(createCategoryList.getCategoryName());
                category.setDescription(createCategoryList.getDescription());

                if (categoryExist.isEmpty()) {
                    category.setUniqueKey(UUID.randomUUID().toString());
                    category.setCreatedOn(LocalDateTime.now());
                    category.setCreatedBy(1);
                } else {
                    category.setUniqueKey(categoryExist.get().getUniqueKey());
                    category.setUpdatedOn(LocalDateTime.now());
                    category.setCreatedOn(categoryExist.get().getCreatedOn());
                    category.setId(categoryExist.get().getId());
                    category.setIsActive(createCategoryList.getIsActive());
                    category.setUpdatedBy(1);
                    category.setCreatedBy(categoryExist.get().getCreatedBy());
                }
                Category result = categoryRepository.save(category);

                if (!createCategoryList.getAttachment().isEmpty()) {
                    ResponseEntity<ResponseDTO> attachmentResult = createOrUpdateAttachment(createCategoryList.getAttachment(), result.getId());
                }
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO(e.getMessage()));
        }

        if (createCategoryList.getCategoryId().isEmpty()) {
            return ResponseEntity.ok().body(new ResponseDTO("Category created Successfully"));
        } else if (createCategoryList.getIsDeleted() == 1) {
            return ResponseEntity.ok().body(new ResponseDTO("Category Deleted Successfully"));
        } else {
            return ResponseEntity.ok().body(new ResponseDTO("Category Updated Successfully"));
        }
    }

    public ResponseEntity<ResponseDTO> createOrUpdateAttachment(List<AttachmentDTO> attachDetails, Integer id) {
        try {
            System.out.println("attachDetails: " + attachDetails);

            List<Attachment> attachments = attachDetails.stream().map(data -> {

                // Check if an attachment with the same UUID exists and is not marked as deleted
                Optional<Attachment> attachment = attachmentRepository.findByFileUuidAndIsDeleted(data.getFileUuid(), 0);

                // Create a new Attachment object
                Attachment attachDetailsObj = new Attachment();

                // Get the EntityType using the isImageOrVideo flag
                EntityType entityType = entityTypeRepository.findById(data.getIsImageOrVideo())
                        .orElseThrow(() -> new RuntimeException("Entity type not found"));

                System.out.println("Entity: " + data.getEntityId());
                System.out.println("FileUuid: " + data.getFileUuid());
                System.out.println("MimeType: " + data.getMimeType());
                System.out.println("FileSize: " + data.getFileSize());

                // Set fields for the Attachment object
                attachDetailsObj.setEntityType(entityType);
                attachDetailsObj.setEntityId(data.getEntityId());
                attachDetailsObj.setFileUuid(data.getFileUuid());
                attachDetailsObj.setMimeType(data.getMimeType());
                attachDetailsObj.setFileSize(data.getFileSize());
                attachDetailsObj.setCoverImage(0); // Temporary zero

                if (attachment.isEmpty()) {
                    // Create new attachment
                    attachDetailsObj.setUniqueKey(UUID.randomUUID().toString());
                    attachDetailsObj.setCreatedOn(LocalDateTime.now());
                    attachDetailsObj.setCreatedBy(1); // You may want to pass this dynamically
                } else {
                    // Update existing attachment
                    attachDetailsObj.setId(attachment.get().getId());
                    attachDetailsObj.setUniqueKey(attachment.get().getUniqueKey());
                    attachDetailsObj.setUpdatedBy(1); // You may want to pass this dynamically
                    attachDetailsObj.setIsDeleted(data.getIsDeleted());
                    attachDetailsObj.setUpdatedOn(LocalDateTime.now());
                }

                return attachDetailsObj;

            }).collect(Collectors.toList());

            List<Attachment> att = attachmentRepository.saveAll(attachments);

            return ResponseEntity.ok().body(new ResponseDTO("Attachment list created/updated."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO("Problem while creation/updating"));
        }
    }

}
