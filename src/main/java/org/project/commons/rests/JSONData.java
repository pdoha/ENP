package org.project.commons.rests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor @RequiredArgsConstructor
public class JSONData {
    //성공했을때
    private boolean success = true;
    //성공시 응답코드
    private HttpStatus status = HttpStatus.OK;
    //성공시 데이터
    @NotNull
    private Object data;
    //실패시 문제 메세지
    private Object message;
}
