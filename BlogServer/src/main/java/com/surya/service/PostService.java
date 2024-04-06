package com.surya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.dto.PostDto;
import com.surya.exception.PostNotFoundException;
import com.surya.model.Post;
import com.surya.repository.PostRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

	/*
	 * @Autowired private AuthService authService;
	 */
	/*
	 * @Autowired private JwtService jwtService;
	 * 
	 * @Autowired private Authentication authenticatedUser;
	 */

	// @Autowired private AuthenticationManager authenticationManager;

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapFromPostToDto).collect(toList());
	}

	/*
	 * @Transactional public Post createPost(PostDto postDto) { Post post =
	 * mapFromDtoToPost(postDto); return postRepository.save(post); }
	 */

	/*
	 * @Transactional public Post createPost(PostDto postDto) {
	 * 
	 * Authentication authentication = authenticationManager.authenticate(null);
	 * String loggedInUsername = authentication.getName();
	 * 
	 * postDto.setUsername(loggedInUsername);
	 * 
	 * Post post = mapFromDtoToPost(postDto);
	 * 
	 * return postRepository.save(post); }
	 */

	/*
	 * @Transactional public Post createPost(PostDto postDto) {
	 * 
	 * // Retrieve authenticated username from SecurityContextHolder
	 * //Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); //String
	 * loggedInUsername = authentication.getName();
	 * //postDto.setUsername(loggedInUsername); String token =
	 * request.getHeader("Authorization").substring(7); String username =
	 * jwtService.extractUsername(token);
	 * 
	 * 
	 * postDto.setUsername(username);
	 * 
	 * Post post = mapFromDtoToPost(postDto);
	 * 
	 * return postRepository.save(post); }
	 */

	@Transactional
	public Post createPost(PostDto postDto) {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		    if (authentication == null) {
		        throw new IllegalStateException("Authentication object is null");
		    }

		    System.out.println("Principal: " + authentication.getPrincipal());
		    System.out.println("Authorities: " + authentication.getAuthorities());

		if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			// Extract the username from the authentication object
			String username = ((UsernamePasswordAuthenticationToken) authentication).getPrincipal().toString();

			postDto.setUsername(username);

			// Rest of the logic to create and save the post
			Post post = mapFromDtoToPost(postDto);
			return postRepository.save(post);
		} else {
			throw new IllegalStateException("Authentication information not available");
		}
	}

	@Transactional
	public PostDto readSinglePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
		return mapFromPostToDto(post);
	}

	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		return postDto;
	}

	private Post mapFromDtoToPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				post.setUsername(((UserDetails) principal).getUsername());
			} else if (principal instanceof String) {
				post.setUsername((String) principal);
			} else {
				throw new IllegalArgumentException("User Not Found");
			}
		} else {
			throw new IllegalArgumentException("User Not Found");
		}

		post.setCreatedOn(Instant.now());
		post.setUpdatedOn(Instant.now());
		return post;
	}

}
