package org.project.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class CommonException extends RuntimeException{
    //상태코드 조회해서 쓸 수 있게
    @Getter
    private HttpStatus status; //상태코드 추가
    private Map<String, List<String>> messages; //필드명과 에러메세지


    //필드명, 오류 메세지
    public CommonException(Map<String, List<String>> messages, HttpStatus status){
        super();
        this.status = status;
        this.messages = messages;


    }

    //기본생성자 ( 메세지와 상태코드를 같이 받아볼 수 있는)
    public CommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    //메세지 조회
    public Map<String, List<String>> getMessages(){
        return messages;
    }

}
