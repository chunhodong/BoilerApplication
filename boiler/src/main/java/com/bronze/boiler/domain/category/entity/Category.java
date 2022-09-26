package com.bronze.boiler.domain.category.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 상품카테고리
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test-sequence-generator1")
    @GenericGenerator(
            name = "test-sequence-generator1",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = SequenceStyleGenerator.DEF_SEQUENCE_NAME),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lo")
            }
    )
    private Long id;


    @Column
    @NotBlank(message = "이름을 입력해야합니다")
    private String name;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Override
    public int hashCode() {
        return id.intValue();
    }

}
