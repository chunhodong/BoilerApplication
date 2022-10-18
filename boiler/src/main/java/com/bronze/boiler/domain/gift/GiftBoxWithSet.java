package com.bronze.boiler.domain.gift;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GiftBoxWithSet extends BaseDate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boxSet")
    private Set<Gift> gifts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boxSet")
    private Set<Tag> tags;

}
