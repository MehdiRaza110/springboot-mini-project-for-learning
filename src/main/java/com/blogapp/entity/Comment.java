package com.blogapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //first for the table and second for the variable...
    @ManyToOne(fetch=FetchType.LAZY)  //load only when is need..(lazy)..
    @JoinColumn(name = "post_id", nullable=false) //join the column..
    private Post post;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "body", nullable = false)
    private String body;
}
