//package com.example.baseballtalk.Controller;
//
//import com.example.baseballtalk.DTO.PostDTO;
//import com.example.baseballtalk.DTO.PostResponseDTO;
//import com.example.baseballtalk.DTO.TeamSmallDTO;
//import com.example.baseballtalk.DTO.UserResponseDTO;
//import com.example.baseballtalk.JWT.TokenProvider;
//import com.example.baseballtalk.Repository.PostRepository;
//import com.example.baseballtalk.Service.PostService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nimbusds.jose.shaded.gson.Gson;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
//
//
//import java.time.LocalDateTime;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@MockBean(JpaMetamodelMappingContext.class)
//@WebMvcTest(PostController.class)
//public class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    PostService postService;
//    @MockBean
//    PostRepository postRepository;
//    @MockBean
//    TokenProvider tokenProvider;
//
//
//    @Test
//    @DisplayName("Post Detail 테스트")
//    void getPostTest() throws Exception {
//
//        int postId = 8;
//
//        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
//        given(postService.getPost(postId)).willReturn(
//                new PostResponseDTO(
//                        postId,
//                        "ㅇㅁㄴ",
//                        "ㅇㅁㄴ",
//                        new TeamSmallDTO(2, "LG", "LG Twins"), "기타",
//                        new UserResponseDTO(3, "황현이", "hwanghyun2@kakao.com", "http://k.kakaocdn.net/dn/XkRha/btsaw6qyROa/lFemBro1QwKhKKKVnJHUU1/img_640x640.jpg", null, null),
//                        0,
//                        LocalDateTime.of(2024, 5, 9, 14, 51, 26, 908498000),
//                        LocalDateTime.of(2024, 5, 9, 14, 51, 26, 908498000)
//                )
//        );
//
//        // andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
//        mockMvc.perform(
//                get("http://localhost:8080/post/" + postId)
//                        .with(oauth2Login()))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.id").exists())
//            .andExpect(jsonPath("$.title").exists())
//            .andExpect(jsonPath("$.content").exists())
//            .andExpect(jsonPath("$.team").exists())
//            .andExpect(jsonPath("$.category").exists())
//            .andExpect(jsonPath("$.author").exists())
//            .andExpect(jsonPath("$.like_count").exists())
//            .andExpect(jsonPath("$.createdAt").exists())
//            .andExpect(jsonPath("$.updatedAt").exists())
//            .andDo(print());
//
//        // verify : 해당 객체의 메소드가 실행되었는지 체크해주는 메소드
//        verify(postService).getPost(postId);
//    }
//
//    @Test
//    @DisplayName("Post Create 테스트")
//    @WithMockUser(roles = "USER")
//    void createPostTest() throws Exception {
//
//        PostDTO postDTO = new PostDTO("hello", "hello-content", 1, "팀/선수", 1);
//        Gson gson = new Gson();
//        String content = gson.toJson(postDTO);
//
//        String json = new ObjectMapper().writeValueAsString(postDTO);
//
//        mockMvc.perform(
//                post("http://localhost:8080/post")
//                        .with(csrf())
//                        .with(oauth2Login())
//                        .content(content)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andDo(print());
//
//        verify(postService).createPost(new PostDTO("hello", "hello-content", 1, "팀/선수", 1));
//    }
//}
