package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // customer 클래스가 jpa엔티티임
public class Customer {

    @Id // 기본 키로 사용될 필드 지정
    // 기본 키가 데이터베이스에서 자동으로 생성되는 것을 나타냄, IDENTITY
    // 전략을 통해 데이터베이스에서 자동 증가된 값을 할당 받음
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
    }
}
