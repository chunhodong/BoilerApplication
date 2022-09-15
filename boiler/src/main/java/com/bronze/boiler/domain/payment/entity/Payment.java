package com.bronze.boiler.domain.payment.entity;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;

@Builder
@Getter
@Entity
@AllArgsConstructor
public class Payment extends BaseDate {
}
