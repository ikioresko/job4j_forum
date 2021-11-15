package ru.job4j.forum.control;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void savePostShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-гранту. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).addPost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-гранту. Дорого."));
    }

    @Test
    @WithMockUser
    public void saveUserShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "Root").param("password", "pass"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(posts).addUser(argument.capture());
        assertThat(argument.getValue().getUsername(), is("Root"));
    }
}
