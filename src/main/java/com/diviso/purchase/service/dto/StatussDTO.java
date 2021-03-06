package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Statuss entity.
 */
public class StatussDTO implements Serializable {

    private Long id;

    private String name;

    private String statusLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusLevel() {
        return statusLevel;
    }

    public void setStatusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatussDTO statussDTO = (StatussDTO) o;
        if(statussDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statussDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatussDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", statusLevel='" + getStatusLevel() + "'" +
            "}";
    }
}
