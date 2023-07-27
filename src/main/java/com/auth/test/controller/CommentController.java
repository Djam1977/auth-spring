package com.auth.test.controller;

import com.auth.test.entity.Comment;
import com.auth.test.entity.User;
import com.auth.test.payload.response.MessageResponse;
import com.auth.test.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @GetMapping("")

    public List<Comment> getComments() { return commentRepository. findAll();
    }
    @PostMapping("")
    public ResponseEntity<?> postComment(@RequestBody Comment commentBody){
        Comment newComment = new Comment(commentBody.getFirstname(),
                commentBody.getLastname(),
                commentBody.getEmail(),
                commentBody.getMessage(),
                commentBody.getRating());
        newComment.setVerifiedByAdmin(false);
        commentRepository.save(newComment);
        return ResponseEntity.ok(new MessageResponse("Commentaire bien enregistré!"));
    }
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
   public  ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment commentBody){
        Comment commentToUpdate = commentRepository.findById(id).get();
        commentToUpdate.setVerifiedByAdmin(true);
        commentRepository.save(commentToUpdate);
        return ResponseEntity.ok(new MessageResponse("Commentaire bien validé!"));
        }




    @DeleteMapping("/{id}")
   //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Comment commentToDelete = commentRepository.findById(id).get();
        commentRepository.deleteById(commentToDelete.getId());
        return ResponseEntity.ok(new MessageResponse("Commentaire bien supprimé!"));
}
    }

