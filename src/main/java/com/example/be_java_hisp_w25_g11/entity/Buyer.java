package com.example.be_java_hisp_w25_g11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {
    private Long id;
    private String name;
    private Set<Long> followed;

    public Buyer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
