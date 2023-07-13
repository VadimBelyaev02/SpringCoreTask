package com.vadim.springcore.controller;

import com.vadim.springcore.dto.request.TagRequestDto;
import com.vadim.springcore.dto.response.TagResponseDto;
import com.vadim.springcore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponseDto getTag(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponseDto> getAllTags() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponseDto postTag(@RequestBody TagRequestDto requestDto) {
        return service.save(requestDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TagResponseDto putTag(@RequestBody TagRequestDto requestDto) {
        return service.update(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }
}
