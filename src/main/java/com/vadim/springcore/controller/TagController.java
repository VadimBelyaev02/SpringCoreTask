package com.vadim.springcore.controller;

import com.vadim.springcore.model.dto.request.TagRequestDto;
import com.vadim.springcore.model.dto.response.ApiResponseDto;
import com.vadim.springcore.model.dto.response.TagResponseDto;
import com.vadim.springcore.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<TagResponseDto> getTag(@PathVariable("id") UUID id) {
        TagResponseDto tagResponseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + id,
                tagResponseDto
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<TagResponseDto>> getAllTags() {
        List<TagResponseDto> tagResponseDtos = service.getAll();

        return ApiResponseDto.successApiResponse(
                "All tags",
                tagResponseDtos
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<TagResponseDto> postTag(@RequestBody @Valid TagRequestDto requestDto) {
        TagResponseDto tagResponseDto = service.save(requestDto);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + tagResponseDto.getId() + " was successfully created",
                tagResponseDto
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<TagResponseDto> putTag(@PathVariable("id") UUID id,
                                                 @RequestBody TagRequestDto requestDto) {
        TagResponseDto tagResponseDto = service.update(id, requestDto);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + tagResponseDto.getId() + " was successfully updated",
                tagResponseDto
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponseDto<?> deleteTag(@PathVariable("id") UUID id) {
        service.deleteById(id);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + id + " was successfully deleted",
                Optional.empty()
        );
    }
}
