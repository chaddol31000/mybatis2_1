package com.example.mybatis2_1.memo;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface MemoDao {
  // 컬럼을 제외하고 설정하려면 memo(mno, title, content, writer) 를 적용하면 됨
  @Insert("insert into memo(mno, title, content, writer) values(memo_seq.nextval, #{title}, #{content}, #{writer})")
  public int save(Memo memo);

  @Select("select mno, title, writer, reg_date as regDate from memo")
  // 여러 개 읽어올 때
  public List<Memo> findAll();

  // 한 개만 읽어올 때 (파라미터가 있음)
  // 그래서 where 절이 붙어야함
  // rownum=1 로 결과의 개수를 오라클에게 알려준다
  @Select("select * from memo where mno=#{mno} and rownum=1")
  Optional<Memo> findByMno(int mno);

  // 여러 개를 바꿀 거라면 Memo memo 로 불러오기
  // 원하는 것만 불러올 거라면 아래 코드도 가능 (둘 다 상관 없음)
  // 하지만 규칙을 정하는 게 좋음
  @Update("update memo set content=#{content} where mno=#{mno} and rownum=1")
  public int update(String content, int mno);

  @Delete("delete from memo where mno=#{mno} and rownum=1")
  public int delete(int mno);
}
