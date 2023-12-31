package com.example.CustomerService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TemplateDTO {
    private int id;
    private String msg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemplateDTO that)) return false;
        return getId() == that.getId() && getMsg().equals(that.getMsg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMsg());
    }
}