package org.project.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestJoin(
        //리액트에서 넘어올 수 있는 항목
        @NotBlank @Email
        String email,

        @NotBlank @Size(min=8)
        String userPw,
        @NotBlank
        String confirmPw,
        @NotBlank
        String userName,
        String mobile,
        @AssertTrue
        Boolean agree
) {

}
