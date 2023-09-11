package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em; //엔티티 매니저 주입

    public void save(Member member) {
        em.persist(member);
    } //persist : 영속성 컨텍스트에 일단 member 객체를 넣죠 그럼 나중에 트랜잭션이 커밋되는 시점에 디비에 반영

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }//jpa의 find메서드를 사용, 단건 조회, ()안에 첫 번째는 타입이고 두 번째는 pk를 넣어주면 됨

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    } //findAll은 JPQL사용, sql 문법이랑 거의 유사한데 from의 대상이 테이블이 아니라 엔티티가 됨

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = : name", Member.class)
                .setParameter("name", name)
                .getResultList();
    } //파라미터 바인딩 해서 특정 회원을 이름으로 찾음
}
