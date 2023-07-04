package isys.practice.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ApiResponseWithPages<T> {
    private String errCode;
    private T content;
    private long totalItems;
    private int totalPages;
    private int currentPage;
}
