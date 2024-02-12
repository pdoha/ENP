package org.project.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.commons.Utils;
import org.project.commons.exceptions.BadRequestException;
import org.project.commons.rests.JSONData;
import org.project.member.service.MemberSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSaveService saveService;

    @PostMapping
    public ResponseEntity<JSONData> join(@RequestBody @Valid RequestJoin form, Errors errors){
        saveService.save(form, errors);

        if(errors.hasErrors()){
            throw new BadRequestException(Utils.getMessage(errors));

        }
        JSONData data = new JSONData();
        data.setStatus(HttpStatus.CREATED); //응답코드 변경

        return ResponseEntity.status(data.getStatus()).body(data);

    }
}
