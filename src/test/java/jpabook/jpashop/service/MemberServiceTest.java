package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) //junit이랑 할 때 스프링이랑 엮어서 돌릴래 하면 넣어주면
@SpringBootTest
@Transactional //테스트 코드에 붙었을 때 롤백
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        Assert.assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try{
            memberService.join(member2); //예외가 발생해야 함
        } catch (IllegalStateException e) {
            return;
        }
//        Assertions.assertThrows(IllegalStateException.class, () -> {
//            memberService.join(member2);
//        }); //위 코드처럼 try catch로 잡거나 람다식으로 풀어야함 junit5부터는 test 어노테이션 옆에 expected 붙이기 불가

        //then
        fail("예외 발생해야 함");

    }
}