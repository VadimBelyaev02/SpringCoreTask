package com.vadim.springcore.controller;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<GiftCertificateResponseDto> getGiftCertificate(@PathVariable("id") UUID id) {
        GiftCertificateResponseDto responseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "Gift certificate with id = " + id,
                responseDto
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<GiftCertificateResponseDto>> getAllGiftCertificates() {
        List<GiftCertificateResponseDto> responseDtos = service.getAll();

        return ApiResponseDto.successApiResponse(
                "Here is all gift certificates",
                responseDtos
        );
    }

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<GiftCertificateResponseDto> postGiftCertificate(@RequestBody @Valid GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.save(requestDto);

        return ApiResponseDto.successApiResponse(
                "Gift certificate was created",
                responseDto
        );
    }

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

    @PatchMapping("/{id}")
    public ApiResponseDto<GiftCertificateResponseDto> patchGiftCertificate(@PathVariable("id") UUID id,
                                                                           @RequestBody GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.update(id, requestDto);
        return ApiResponseDto.successApiResponse(
                "Gift certificate with id = " + id + " was successfully partially updated",
                responseDto
        );
    }

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
