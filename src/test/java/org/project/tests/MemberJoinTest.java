package org.project.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.commons.contants.MemberType;
import org.project.member.controllers.RequestJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//mocMvc
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@SpringBootTest
@Transactional //test 후 DB 비우기
@AutoConfigureMockMvc
public class MemberJoinTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원 가입 테스트")
    void joinTest() throws Exception {
        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .userPw("_aA123456")
                .confirmPw("_aA123456")
                .userName("사용자01")
                .mobile("010-0000-0000")
                .agree(true)
                .build();

        ObjectMapper om = new ObjectMapper();
        String params = om.writeValueAsString(form); //문자열로 json형태로 바꾸기

        //변환된 데이터 확인
        //System.out.println(params);

        //요청시 데이터 보내기
        mockMvc.perform(post("/api/v1/member")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8") //인코딩
                .content(params)  //요청데이터는 바디에 실어서 전송
                .with(csrf().asHeader()) //토큰추가
        ).andDo(print());

    }

}
