package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Access;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;
}
