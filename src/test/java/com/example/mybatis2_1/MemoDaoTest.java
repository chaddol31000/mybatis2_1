package com.example.mybatis2_1;

import com.example.mybatis2_1.memo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemoDaoTest {
    // MemoDao 파일을 사용하려면 @Autowired 가 필요함
    @Autowired
    private MemoDao memoDao;

    @Test
    public void saveTest() {
      // Memo memo = new Memo(0, "토익 접수", "5월 토익", "spring", LocalDate.now());
      // 위의 과정이 너무 복잡하기에 아래의 코드로 적는 게 더 효율적
      Memo m = Memo.builder().title("토익 접수").content("토익 접수 확인").writer("spring").build();
      int result = memoDao.save(m);
        assertEquals(1,result);
    }
    @Test
    public void findAllTest() {
      assertEquals(1, memoDao.findAll().size());
    }

    @Test
    public void findByMnoTest(){
      Optional<Memo> result = memoDao.findByMno(1);
      if(result.isPresent()) {
        Memo memo = result.get();
      }
      // assertNotNull(memoDao.findByMno(1));
      // assertNull(memoDao.findByMno(100));
      // 에러 : Memo memo = memoDao.findByMno(1);
      // Optional 을 전달 받으면 get() 으로 객체를 꺼낼 수 있다
      // 단, 없으면 NoSuchElementException 발생
      // 워낙 null 때문에 분쟁이 많이 발생해 Optional 이라는 메소드를 만듬
      // 아래 코드는 굉장히 위험한 코드 (오류 가능성 높아짐)
      // Optional<Memo> result = memoDao.findByMno(1);
      // Memo memo = result.get();
    }

    @Test
    public void updateTest() {
      int result = memoDao.update("토익접수했니", 1);
      assertEquals(1, result);
      // 실패하는 것도 테스트
      result = memoDao.update("귀찮아요", 100);
      assertEquals(0,result);
    }

    // 테스트에서 사용하면 자동 rollback
    @Transactional
    @Test
    public void deleteTest(){
      int result = memoDao.delete(1);
      assertEquals(1, result);
      // 실패하는 것도 테스트
      result = memoDao.delete(100);
      assertEquals(0,result);
    }
}
