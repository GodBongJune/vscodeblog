package shop.mtcoding.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.dto.UserUpdateDTO;
import shop.mtcoding.blog.model.User;

// BoardController, UserController, UserRepository
// EntityManager, HttpSession
@Repository
public class UserRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void update(UserUpdateDTO userUpdateDTO, Integer id) {
        Query query = em.createNativeQuery("update user_tb set password=:password where id = :id");
        query.setParameter("id", id);
        String encPassword = BCrypt.hashpw(userUpdateDTO.getPassword(), BCrypt.gensalt());
        query.setParameter("password", encPassword);
        query.executeUpdate();
    }

    public User findByUsername(String username) {
        try {
            Query query = em.createNativeQuery("select * from user_tb where username=:username",
                    User.class);
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public User findByUsernameAndPassword(LoginDTO loginDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=:username and password=:password",
                User.class);
        query.setParameter("username", loginDTO.getUsername());
        query.setParameter("password", loginDTO.getPassword());
        return (User) query.getSingleResult();
    }

    @Transactional
    public void save(JoinDTO joinDTO) {
        System.out.println("테스트 :" + 1);
        Query query = em
                .createNativeQuery(
                        "insert into user_tb(username, password, email) values(:username, :password, :email)");
        System.out.println("테스트 :" + 2);
        query.setParameter("username", joinDTO.getUsername());
        query.setParameter("password", joinDTO.getPassword());
        query.setParameter("email", joinDTO.getEmail());
        System.out.println("테스트 :" + 3);
        query.executeUpdate(); // 쿼리를 전송 (DBMS)
        System.out.println("테스트 :" + 4);
    }

    public User findById(Integer id) {
        Query query = em.createNativeQuery("select * from user_tb where id = :id", User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

}