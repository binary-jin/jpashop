package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("useA", "서울","1","1111");
            em.persist(member);

            Book book1 = createBook("jpa1", 2000, 20);
            em.persist(book1);

            Book book2 = createBook("jpa2", 1000, 10);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 2000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 1000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB" ,"진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("spring1", 2000, 20);
            em.persist(book1);

            Book book2 = createBook("spring2", 1000, 10);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 2000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 1000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
