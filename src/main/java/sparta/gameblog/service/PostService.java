package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.RequestPostDto;
import sparta.gameblog.dto.ResponsePostDto;
import sparta.gameblog.entity.Post;
import sparta.gameblog.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public ResponsePostDto createPost(RequestPostDto requestDto) {
        Post post = new Post(requestDto);
        return new ResponsePostDto(postRepository.save(post));
    }
}
