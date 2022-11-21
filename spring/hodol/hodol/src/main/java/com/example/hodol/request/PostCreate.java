package com.example.hodol.request;

import com.example.hodol.exception.InvalidRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString()
@NoArgsConstructor
@Setter
@Getter
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요.") // Null 도 체크해준다.
    public String title;
    @NotBlank(message = "콘텐츠를 입력해주세요.")
    public String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public PostCreate changeTitle(String title) {
        return PostCreate
                .builder()
                .title(title)
                .content(this.content)
                .build();
    }

    public void validate() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }
    }

    //    빌더의 장점
    // 1. 가독성이 좋다. (값 생성에 대한 유연함)
    // 2. 필요한 값만 받을 수 있다. // -> (오버로딩 가능한 조건 찾아보기)
    // 3. 객체의 불변성!!


/*    // 필수 값 있는 빌더 직접 만들기
        public static class MyBuilderPattern {
        private String title;
        private String content;

        public MyBuilderPattern(String title) {
            this.title = title;
        }
        public MyBuilderPattern(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public MyBuilderPattern content(String content) {
            this.content = content;
            return this;
        }
        public PostCreate myBuild() {
            return new PostCreate(this.title, this.content);
        }
    public static MyBuilderPattern myBuilder(String title) {
        return new MyBuilderPattern(title);
    }
}*/
}
