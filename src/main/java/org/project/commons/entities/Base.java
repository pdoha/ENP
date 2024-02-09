package org.project.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //공통속성화
@Getter @Setter
@EntityListeners(AuditingEntityListener.class) //이벤트 감지
public abstract class Base {
    //날짜와 시간 관련 공통 부분
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; //등록일자

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt; //수정일자
}
