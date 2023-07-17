package com.vadim.springcore.controller;

import com.vadim.springcore.model.criteria.GiftCertificateCriteria;
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
        GiftCertificateResponseDto giftCertificateResponseDto = service.getById(id);

        return ApiResponseDto.<GiftCertificateResponseDto>builder()
                .data(giftCertificateResponseDto)
                .timestamp(Instant.now())
                .message("Gift certificate with id = " + id)
                .color(ApiResponseDto.Color.SUCCESS)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<GiftCertificateResponseDto>> getAllGiftCertificates() {
        List<GiftCertificateResponseDto> giftCertificateResponseDtos = service.getAll();

        return ApiResponseDto.<List<GiftCertificateResponseDto>>builder()
                .data(giftCertificateResponseDtos)
                .timestamp(Instant.now())
                .message("Here is all gift certificates")
                .color(ApiResponseDto.Color.SUCCESS)
                .build();
    }

    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<GiftCertificateResponseDto>> getAllGiftCertificatesByCriteria(
        @RequestParam(value = "tagName", required = false) String tagName,
        @RequestParam(value = "sortByName", required = false) String sortByName,
        @RequestParam(value = "sortByDate", required = false) String sortByDate,
        @RequestParam(value = "partOfDescription", required = false) String partOfDescription,
        @RequestParam(value = "partOfTagName", required = false) String partOfTagName
    ) {
        GiftCertificateCriteria criteria = GiftCertificateCriteria.builder()
                .tagName(tagName)
                .sortByDate(sortByDate)
                .partOfDescription(partOfDescription)
                .partOfTagName(partOfTagName)
                .sortByName(sortByName)
                .build();
        List<GiftCertificateResponseDto> responseDtos = service.getAllByCriteria(criteria);

        return ApiResponseDto.<List<GiftCertificateResponseDto>>builder()
                .color(ApiResponseDto.Color.SUCCESS)
                .message("All gift certificates by provided criteria")
                .timestamp(Instant.now())
                .data(responseDtos)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<GiftCertificateResponseDto> postGiftCertificate(@RequestBody @Valid GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.save(requestDto);

        return ApiResponseDto.<GiftCertificateResponseDto>builder()
                .message("Gift certificate was created")
                .timestamp(Instant.now())
                .data(responseDto)
                .color(ApiResponseDto.Color.SUCCESS)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<GiftCertificateResponseDto> putGiftCertificate(@PathVariable("id") UUID id,
                                                         @RequestBody GiftCertificateRequestDto requestDto) {
        GiftCertificateResponseDto responseDto = service.update(id, requestDto);
        return ApiResponseDto.<GiftCertificateResponseDto>builder()
                .data(responseDto)
                .color(ApiResponseDto.Color.SUCCESS)
                .timestamp(Instant.now())
                .message("Gift certificate with id = " + id + " was successfully updated")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponseDto<?> deleteGiftCertificate(@PathVariable("id") UUID id) {
        service.deleteById(id);

        return ApiResponseDto.builder()
                .color(ApiResponseDto.Color.SUCCESS)
                .timestamp(Instant.now())
                .message("Gift certificate with id = " + id + " was successfully deleted")
                .build();
    }
}
