package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입일 경우 적어줌
    private Address address;

    @JsonIgnore //양방향 연관관계면 한쪽은 이걸 달아줘야함
    @OneToMany(mappedBy = "member") //order 테이블에 있는 member에 의해 바뀜
    private List<Order> orders = new ArrayList<>();
}
