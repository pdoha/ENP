package org.project.commons;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


import java.util.*;

public class Utils {
    private static ResourceBundle validationsBundle;
    private static ResourceBundle errorsBundle;

    static {
        //validations 메세지 코드 추가
        validationsBundle = ResourceBundle.getBundle("messages.validations");
        errorsBundle = ResourceBundle.getBundle("messages.errors");
    }

    //메세지 코드로 에러관리
    public static String getMessage(String code, String bundleType) {
        bundleType = Objects.requireNonNullElse(bundleType, "validation");
        ResourceBundle bundle = bundleType.equals("error")? errorsBundle:validationsBundle;
        try {
            return bundle.getString(code);
        } catch (Exception e) {
            return null;
        }
    }

    //string 필드명과 해당 에러들은 list
    public static Map<String, List<String>> getMessage(Errors errors){
        try{

            Map<String, List<String>> data = new HashMap<>(); //반환값 데이터
            for(FieldError error : errors.getFieldErrors()) {
                String field = error.getField();

                //에러코드가 나왔을때
                //우선순위가 높은것부터 reverseOrder 역순
                List<String> messages = Arrays.stream(error.getCodes()).sorted(Comparator.reverseOrder())
                        //메세지코드 찾아서 가공 (넘어온값 code
                        .map(c -> getMessage(c, "validation")) //실제 메세지코드에서 조회헤보고
                        //메세지 못찾으면 null값 (넘어온값 확인하고 이게 null아이나면
                        .filter(c -> c != null)
                        .toList();

                data.put(field, messages);
            }
            return data;

        } catch (Exception e){
            return null;
        }
    }
}
