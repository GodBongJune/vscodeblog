package shop.mtcoding.blog.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.WriteDTO;
import shop.mtcoding.blog.model.Board;

@Repository
public class BoardRepository {
    @Autowired
    private EntityManager em;

    public int count() {
        Query query = em.createNativeQuery("select count(*) from board_tb");
        BigInteger count = (BigInteger) query.getSingleResult();
        return count.intValue();
    }

    public int count2() {
        // Entity 타입이 (Board,User)아니어도, 기본 자료도 안됨
        Query query = em.createNativeQuery("select * from board_tb", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList.size();
    }

    // 페이징쿼리
    // localhost:8080?page=0
    public List<Board> findAll(int page) {
        final int SIZE = 3; // 상수라서 하나만바꾸면 오류가 발생하기때문에 이렇게 바꿔준다, 상수라서 대문자
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit :page, :size", Board.class);
        query.setParameter("page", page * SIZE); // 한페이지에 3개씩 나타내기위해
        query.setParameter("size", SIZE);
        return query.getResultList();
    }

    @Transactional
    public void save(WriteDTO writeDTO, Integer userId) {
        Query query = em.createNativeQuery(
                "insert into board_tb(title,content,user_id,created_at) values(:title,:content,:userId,now())");
        query.setParameter("title", writeDTO.getTitle());
        query.setParameter("content", writeDTO.getContent());
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    public Board findById(Integer id) {
        Query query = em.createNativeQuery("select * from board_tb where id= :id", Board.class);
        query.setParameter("id", id);
        Board board = (Board) query.getSingleResult();
        return board;
    }

    @Transactional
    public void deleteById(Integer id) {
        Query query = em.createNativeQuery("delete from board_tb where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
