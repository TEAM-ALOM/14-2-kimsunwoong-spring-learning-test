package cholog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonCreator와 @JsonProperty 어노테이션을 사용하여 Jackson이 생성자를 통해
// 객체를 만들도록 해서 역직렬화 처리
public class Person {
    private String name;
    private int age;

    @JsonCreator
    public Person(@JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
