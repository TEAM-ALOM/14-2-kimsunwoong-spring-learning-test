package cholog;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    private final List<Member> members = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(1);

    @PostMapping("/members")
    public ResponseEntity<Void> create(@RequestBody Member member) {
        // TODO: member 정보를 받아서 생성한다.
        // 새로 생성되는 회원에게 고유 ID부여
        Member newMember = Member.toEntity(member, index.getAndIncrement());
        members.add(newMember);
        //회원의 리소스 위치 URI 반환
        return ResponseEntity.created(URI.create("/members/" + newMember.getId())).build();
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> read() {
        // TODO: 저장된 모든 member 정보를 반환한다.
        // ResponseEntity를 사용해 상태 코드 200과 함께 회원 리스트를 JSON 형식으로 반환
        return ResponseEntity.ok(members);

    }

    //HTTP PUT 요청을 받아 기존 회원 정보를 업데이트
    @PutMapping("/members/{id}")
    // url에서 제공된 회원 id 와 클라이언트가 보낸 JSON 데이터를 Member 객체로 역직렬화하여 수정된 회원 정보를 가져옴
    public ResponseEntity<Void> update(@RequestBody Member newMember, @PathVariable Long id) {
        // TODO: member의 수정 정보와 url 상의 id 정보를 받아 member 정보를 수정한다.
        // ID로 회원을 검색하여 업데이트
        // 회원 ID로 리스트에서 해당 회원을 찾고 없으면 RuntimeException 발생
        Member member = members.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElseThrow(RuntimeException::new);

        member.update(newMember);// 회원 정보의 업데이트 수행
        return ResponseEntity.ok().build();// 상태 코드 200로 응답
    }

    //HTTP DELETE 요청을 받을 시 회원을 삭제
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO: url 상의 id 정보를 받아 member를 삭제한다.
        Member member = members.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElseThrow(RuntimeException::new);// 없으면 RuntimeException을 발생

        // member 삭제
        members.remove(member);

        return ResponseEntity.noContent().build();
    }
}
