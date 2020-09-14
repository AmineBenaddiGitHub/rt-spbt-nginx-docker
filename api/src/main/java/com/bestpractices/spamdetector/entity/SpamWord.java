package com.bestpractices.spamdetector.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@Table(
        name = "SPAM_WORD",
        indexes = {@Index(name = "idx", columnList = "ID")}
)
public class SpamWord {
    @Id
    @NonNull
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "WORD")
    private String word;

    public SpamWord(String word) { this.word = word; }
    public SpamWord(long i,String word) { id = i; this.word = word; }

}
