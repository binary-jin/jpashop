package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {

    } //기본 생성자를 가지고 있어야 함(JPA에서 그렇게 하래)

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    } //Setter 대신 생성자를 만들어 값을 초기화해 변경 불가능한 클래스를 만듬
}
