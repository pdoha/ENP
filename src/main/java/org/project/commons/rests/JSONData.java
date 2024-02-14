package org.project.commons.rests;

import lombok.*;
import org.springframework.http.HttpStatus;



@Data
@RequiredArgsConstructor
@NoArgsConstructor
//생성자 오류 생김
public class JSONData {
    //성공했을때
    private boolean success = true;
    //성공시 응답코드
    private HttpStatus status = HttpStatus.OK;
    //성공시 데이터
    @NonNull
    private Object data;
    //실패시 문제 메세지 ( 한개일수도, 여러개일 수도 있으니까 object)
    private Object message;
}
