package com.example.mybatis2_1.memo;

import lombok.*;

import java.time.*;
// 값을 받아오고 읽어야 하기에 Getter, Setter 가 필요함
// Getter 는 무조건 필요하고 사용자에게 값을 받아와야 하기에 Setter 가 필요함
// 스프링은 커맨드 객체를 기본생성자로 생성한 다음, setter 로 값을 집어넣는다
// 롬복에서 @Builder 를 사용하려면 @AllArgsConstructor 도 필요함
// 배포할 때는 테스트가 끝난 후라서 @AllArgsConstructor, @Builder,
// @ToString 이 필요하지 않기에 삭제
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Memo {
  private int mno;
  private String title;
  private String content;
  private String writer;
  // 빌더를 사용한 경우 필드에 값을 직접 지정하는 인스턴스 초기화를 무시
  @Builder.Default
  private LocalDate regDate = LocalDate.now();
}
