package org.project.member.repositories;

import org.project.member.entities.Member;
import org.project.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,
        QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);

    default boolean exists(String email){
        return exists(QMember.member.email.eq(email));
    }
}
