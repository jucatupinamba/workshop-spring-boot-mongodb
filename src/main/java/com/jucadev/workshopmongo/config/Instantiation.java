package com.jucadev.workshopmongo.config;

import com.jucadev.workshopmongo.domain.Post;
import com.jucadev.workshopmongo.domain.User;
import com.jucadev.workshopmongo.dto.AuthorDTO;
import com.jucadev.workshopmongo.dto.CommentDTO;
import com.jucadev.workshopmongo.repository.PostRepository;
import com.jucadev.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {




    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem",
                "Vou viajar para as Américas, abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("11/04/2019"), "Carro novo",
                "Corola preto, detalhes cromados.", new AuthorDTO(maria));

        CommentDTO comment = new CommentDTO("Boa viagem parsa!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Ta podendo!", sdf.parse("23/03/2018"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Belo carro!", sdf.parse("12/04/2019"), new AuthorDTO(maria));

        post1.getComments().addAll(Arrays.asList(comment, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
