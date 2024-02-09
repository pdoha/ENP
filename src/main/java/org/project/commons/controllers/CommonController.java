package org.project.commons.controllers;

import org.project.commons.exceptions.CommonException;
import org.project.commons.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("commons.controllers")
public class CommonController {
    @ExceptionHandler(Exception.class) //범용적인 기능이니까 상위클래스
    public ResponseEntity<JSONData> errorHandler(Exception e){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; //기본상태코드 500 고정
        //메세지가 한개인 경우 Exception 객체에서 하나 가져오고
        Object message = e.getMessage();

        //e가 내가만든 예외 객체인지 체크
        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            //응답상태 교체
            status = commonException.getStatus();
            //commonException에 messages가 있는 경우는 대체 (메세지가 여러개인 경우)
            if(commonException.getMessages()!= null) message = commonException.getMessages();
        }

        JSONData data = new JSONData();
        //실패
        data.setSuccess(false);
        data.setStatus(status);
        data.setMessage(message);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data);
    }
}
