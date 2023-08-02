package com.vadim.springtask.controller;

import com.vadim.springtask.exception.DuplicateRecordException;
import com.vadim.springtask.exception.NotFoundException;
import com.vadim.springtask.model.dto.request.TagRequestDto;
import com.vadim.springtask.model.dto.response.ApiResponseDto;
import com.vadim.springtask.model.dto.response.PageResponseDto;
import com.vadim.springtask.model.dto.response.TagResponseDto;
import com.vadim.springtask.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vadim.springtask.util.constants.PaginationConstants.DEFAULT_PAGE_NUMBER;
import static com.vadim.springtask.util.constants.PaginationConstants.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    /**
     * GET /api/tags/{id} : Get a tag
     *
     * @param id Tag id (required)
     * @throws NotFoundException if tag with id doesn't exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<TagResponseDto> getTag(@PathVariable("id") UUID id) {
        TagResponseDto tagResponseDto = service.getById(id);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + id,
                tagResponseDto
        );
    }

    /**
     * GET /api/tags : Get all tags
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<PageResponseDto<TagResponseDto>> getAllTags(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        page = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        pageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
        PageResponseDto<TagResponseDto> tagResponseDtos = service.getAll(page, pageSize);

        return ApiResponseDto.successApiResponse(
                "All tags; page: " + page + "; page size: " + pageSize ,
                tagResponseDtos
        );
    }

    /**
     * POST /api/tags : Create a new tag
     *
     * @param requestDto Tag to be created (required)
     * @throws DuplicateRecordException if tag with such a name already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<TagResponseDto> postTag(@RequestBody @Valid TagRequestDto requestDto) {
        TagResponseDto tagResponseDto = service.save(requestDto);

        return ApiResponseDto.successApiResponse(
                "Tag with id = " + tagResponseDto.getId() + " was successfully created",
                tagResponseDto
        );
    }

    /**
     * PUT /api/tags/{id} : Update an existing tag
     *
     * @param id         Tag id (required)
     * @param requestDto Tag to be updated (required)
     * @throws NotFoundException if the Tag with id doesn't exist
     */
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

    /**
     * DELETE /api/tags/{id} : Delete a tag
     *
     * @param id Tag id (required)
     * @throws NotFoundException if Tag with id doesn't exist
     */
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
