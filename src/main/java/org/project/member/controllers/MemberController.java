package org.project.member.controllers;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @PostMapping
    public void join(@RequestBody @Valid RequestJoin form, Errors errors){

    }
}
