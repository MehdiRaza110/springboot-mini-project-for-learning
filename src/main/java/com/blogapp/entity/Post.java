package com.blogapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity //entity anotation to make the class entity
@Data //add getters and setters throught lambok
@Table(name="Posts")// name of the table
@AllArgsConstructor //gen all arguments constructure through lambock
@NoArgsConstructor //gen no arguments constructure through lambock
public class Post { // entity class name

    @Id //unique identity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique identity generation strategy
    private long id; //private identity

    @Column(name="title",nullable=false) //column name with null value not able to take, also we can use unique=true here
    private String title; //private title

    @Column(name="description", nullable = false)//column name with null value not able to take
    private String description;//private description

    @Column(name="content", nullable = false)//column name with null value not able to take
    private String content;// private content

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")//CascadeType.ALL delete the post and the comment will gone..
    private List<Comment> comments;			    //mappedBy map to comment entity class based on post variable..
}
