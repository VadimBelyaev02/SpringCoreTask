package com.vadim.springcore.controller;

import com.vadim.springcore.dto.request.GiftCertificateRequestDto;
import com.vadim.springcore.dto.response.ApiResponseDto;
import com.vadim.springcore.dto.response.GiftCertificateResponseDto;
import com.vadim.springcore.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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
    public List<GiftCertificateResponseDto> getAllGiftCertificates() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateResponseDto postGiftCertificate(@RequestBody GiftCertificateRequestDto requestDto) {
        return service.save(requestDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateResponseDto putGiftCertificate(@RequestBody GiftCertificateRequestDto requestDto) {
        return service.update(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificate(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }
}
