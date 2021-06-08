package hello.posts.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostsRepository {

    @PersistenceContext
    private EntityManager em;

    //--------- 저장 메서드 --------//
    public Long save(Posts posts)
    {
        em.persist(posts);
        return posts.getId();
    }

    //------- 조회 메서드 -------//

    public List<Posts> findAll()
    {
        List<Posts> postsList = em.createQuery("select p from Posts p", Posts.class).getResultList();
        return new ArrayList<>(postsList);
    }

    public Posts findById(Long id)
    {
        return em.find(Posts.class, id);

    }

    public List<Posts> findByName(String author)
    {
        List<Posts> findByName = em.createQuery("select p from Posts p where p.author = :author", Posts.class)
                                   .getResultList();
        return new ArrayList<>(findByName);
    }

    //--------- 삭제 메서드 -----------//

    public void delete(Long id)
    {
        Posts posts = em.find(Posts.class, id);
        em.remove(posts);

    }

    //---------- 업데이트 메서드 ---------//

    public void update(Long id , PostsRequestDTO postsRequestDTO)
    {
        Posts posts = em.find(Posts.class, id);
        posts.update(postsRequestDTO);
    }

}
