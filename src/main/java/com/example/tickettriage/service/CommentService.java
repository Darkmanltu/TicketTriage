package com.example.tickettriage.service;

import java.util.UUID;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.tickettriage.model.Comment;
import com.example.tickettriage.model.CommentRequest;
import com.example.tickettriage.model.CommentResponse;
import com.example.tickettriage.repository.CommentRepository;

@Service
@Slf4j
@RequiredArgsConstructor

/*
* To do
* need model inference to create the summary and judge if the comment is supposed to be made into a ticket
* for a comment to be a ticket, we judge it if it has words like
* "found bug" / "had problems with" etc. (not too sure how to classify)
* */



public class CommentService {

    private final CommentRepository commentRepository;
//    private final


    public CommentResponse createComment(CommentRequest req){

        CommentResponse resp = new CommentResponse();
        Comment comment = new Comment();
        comment.setComment(req.comment);
        comment.setChannel(req.channel);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(comment.getCreatedAt());

        try{
            commentRepository.save(comment);

        }
        catch (Exception e)
        {
            return null; //for now
        }
        //call model through api? inference and create a ticket
//        ticketService.createTicket(Comment);
        resp.id = comment.getId();
        //resp.setTag()
        return resp;
    }
        public Comment getCommentById(UUID id){

            if (id == null)
                return null;
            try {
              Comment found = commentRepository.findById(id).orElseThrow(() -> new Exception("not found"));
              return found;
            }
            catch (Exception e){
                return null;
            }
        }
        public List<Comment> getComments(){
            return commentRepository.findAll();
        }
}