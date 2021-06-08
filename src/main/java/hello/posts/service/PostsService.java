package hello.posts.service;

import hello.posts.domain.Posts;
import hello.posts.domain.PostsRepository;
import hello.posts.domain.PostsRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;


    //--------- 저장 서비스 -----------//

    @Transactional
    public Long save(Posts posts)
    {
        return postsRepository.save(posts);

    }

    //------------ 조회 서비스 ----------//

    public List<Posts> findAll()
    {
        List<Posts> findAllList = postsRepository.findAll();
        return findAllList;


    }

    public List<Posts> findByName(String name)
    {
        List<Posts> byName = postsRepository.findByName(name);
        return byName;
    }

    public Posts findById(Long id)
    {
        Posts byId = postsRepository.findById(id);
        return byId;
    }

    //----------- 업데이트 서비스 ----------//

    @Transactional
    public void update(Long id , PostsRequestDTO postsRequestDTO)
    {
        postsRepository.update(id, postsRequestDTO);
    }


    //-------------- 삭제 메서드 -----------//

    @Transactional
    public void delete(Long id)
    {
        postsRepository.delete(id);
    }





}
