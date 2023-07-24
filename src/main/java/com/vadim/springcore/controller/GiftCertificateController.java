package com.vadim.springcore.controller;

import com.vadim.springcore.exception.NotFoundException;
import com.vadim.springcore.model.criteria.GiftCertificateCriteria;
import com.vadim.springcore.model.criteria.enums.SortField;
import com.vadim.springcore.model.criteria.enums.SortType;
import com.vadim.springcore.model.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.model.dto.response.ApiResponseDto;
import com.vadim.springcore.model.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.service.GiftCertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService service;

    /**
     * GET /api/giftCertificates/{id} : Find gift certificate
     *
     * @param id Gift Certificate id (required)
     * @throws NotFoundException if the Gift Certificate with id is not found
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<GiftCertificateResponseDto> getGiftCertificate(@PathVariable("id") UUID id) {
        GiftCertificateResponseDto responseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "Gift certificate with id = " + id,
                responseDto
        );
    }

    /**
     * GET /api/giftCertificates : Get all gift certificates
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<GiftCertificateResponseDto>> getAllGiftCertificates() {
        List<GiftCertificateResponseDto> responseDtos = service.getAll();

        return ApiResponseDto.successApiResponse(
                "Here is all gift certificates",
                responseDtos
        );
    }

    /**
     * GET /api/giftCertificates : Find Gift Certificates info by criteria
     *
     * @param tagName Gift Certificate tag's name (not required)
     * @param firstSortBy First field to sort by (name/description, not required)
     * @param secondSortBy Second field to sort by (name/description, not required)
     * @param firstSortType First sort type (ASC/DESC, not required)
     * @param secondSortType Second sort type (ASC/DESC, not required)
     * @param partOfDescription Part of description (not required)
     * @param partOfName Part of name (not required)
     */
    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<GiftCertificateResponseDto>> getAllGiftCertificatesByCriteria(
            @RequestParam(value = "tagName", required = false) String tagName,
            @RequestParam(value = "firstSortBy", required = false, defaultValue = "NONE") String firstSortBy,
            @RequestParam(value = "secondSortBy", required = false, defaultValue = "NONE") String secondSortBy,
            @RequestParam(value = "firstSortType", required = false, defaultValue = "NONE") String firstSortType,
            @RequestParam(value = "secondSortType", required = false, defaultValue = "NONE") String secondSortType,
            @RequestParam(value = "partDescription", required = false) String partOfDescription,
            @RequestParam(value = "partName", required = false) String partOfName
    ) {
        GiftCertificateCriteria criteria = GiftCertificateCriteria.builder()
                .tagName(tagName)
                .partOfDescription(partOfDescription)
                .partOfName(partOfName)
                .firstSortType(SortType.valueOf(firstSortType))
                .firstSortBy(SortField.valueOf(firstSortBy))
                .secondSortBy(SortField.valueOf(secondSortBy))
                .secondSortType(SortType.valueOf(secondSortType))
                .build();
        List<GiftCertificateResponseDto> responseDtos = service.getAllByCriteria(criteria);

        return ApiResponseDto.successApiResponse(
                "All gift certificates by provided criteria",
                responseDtos
        );
    }

    /**
     * POST /api/giftCertificates : Create a new gift certificate
     *
     * @param requestDto Gift certificate object to be created (required)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<GiftCertificateResponseDto> postGiftCertificate(@RequestBody @Valid GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.save(requestDto);

        return ApiResponseDto.successApiResponse(
                "Gift certificate was created",
                responseDto
        );
    }

    /**
     * PUT /api/giftCertificates/{id} : Update an existing gift certificate
     *
     * @param id Gift certificate id (required)
     * @param requestDto Gift certificate object to be updated (required)
     * @throws NotFoundException if gift certificate with id doesn't exist
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<GiftCertificateResponseDto> putGiftCertificate(@PathVariable("id") UUID id,
                                                                         @RequestBody @Valid GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.update(id, requestDto);
        return ApiResponseDto.successApiResponse(
                "Gift certificate with id = " + id + " was successfully updated",
                responseDto
        );
    }

    /**
     * PATCH /api/giftCertificates/{id} : Partially updating an existing gift certificate
     *
     * @param id Gift Certificate id to return (required)
     * @param requestDto gift certificate to be updated (required)
     * @throws NotFoundException if gift certificate with id doesn't exist
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<GiftCertificateResponseDto> patchGiftCertificate(@PathVariable("id") UUID id,
                                                                           @RequestBody GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.update(id, requestDto);
        return ApiResponseDto.successApiResponse(
                "Gift certificate with id = " + id + " was successfully partially updated",
                responseDto
        );
    }

    /**
     * DELETE /api/giftCertificates/{id} : Delete a gift certificate
     *
     * @param id gift certificate id (required)
     * @throws NotFoundException if gift certificate with id doesn't exist
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponseDto<?> deleteGiftCertificate(@PathVariable("id") UUID id) {
        service.deleteById(id);

        return ApiResponseDto.builder()
                .timestamp(Instant.now())
                .message("Gift certificate with id = " + id + " was successfully deleted")
                .build();
    }
}
