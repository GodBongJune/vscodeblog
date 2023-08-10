package shop.mtcoding.blog.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 프엔이거봐
 * 회원가입 API
 * 1. URL : http://localhost:8080/join
 * 2. method : POST
 * 3. 요청body(request body) : username=값(String)&password=값(String)&email=값(String)
 * 4. MIME 타입 : x-www-form-unlencoded
 * 5. 응답 : view(html) 응답
 */

@Getter
@Setter
public class JoinDTO {
    private String username;
    private String password;
    private String email;
}
