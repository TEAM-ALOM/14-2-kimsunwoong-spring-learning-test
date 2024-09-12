package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller// 컨트롤러는 웹 요청 처리 및 응답 반환 역할
public class MemberController {

    @GetMapping("/hello")//HTTP GET 요청이 /hello 경로로 들어올 때 static.html 페이지와 name 파라미터를 사용하게 만듦
    public String world(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        // 요청 파라미터에서 name 이라는 파라미터를 받게 설정
        // 요청에서 name이라는 파라미터를 찾게 만듦, 필수가 아니게 설정하고 기본값은 "World"로 설정
        // model에 정보를 담아서 통해 뷰에 데이터를 전달
        // TODO: /hello 요청 시 resources/templates/static.html 페이지가 응답할 수 있도록 설정하세요.
        // TODO: 쿼리 파라미터로 name 요청이 들어왔을 때 해당 값을 hello.html에서 사용할 수 있도록 하세요.
        model.addAttribute("name", name);//요청에서 "name" 이라는 속성으로 전달된 값을 뷰에 넘김
        return "hello";// hello.html에서 해당값을 받아서 사용
    }

    @GetMapping("/json") // /json으로 요청 시
    @ResponseBody //반환된 객체를 뷰로 렌더링하지 않고 JSON형식으로 직렬화하여 HTTP응답 본문에 직접 담아 보냄, 데이터 응답을 아래처럼
    public Person json() {// 이미 정의된 클래스이므로 형태 참고
        // TODO: /json 요청 시 {"name": "brown", "age": 20} 데이터를 응답할 수 있도록 설정하세요.
        return new Person("brown",20);// TODO와 같은 형태의 객체를 생성해서 반환
    }
}
