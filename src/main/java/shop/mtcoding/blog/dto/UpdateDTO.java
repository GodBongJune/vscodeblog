package shop.mtcoding.blog.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 프엔이거봐
 * 글쓰기 API
 * 1. URL : http://localhost:8080/board/{id}/update
 * 2. method : POST
 * 3. 요청body(request body) : title=값(String)&content=값(String)
 * 4. MIME 타입 : x-www-form-unlencoded
 * 5. 응답 : view(html) 응답
 */

@Getter
@Setter
public class UpdateDTO {
    private int id;
    private String title;
    private String content;
}
