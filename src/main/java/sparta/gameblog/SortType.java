package sparta.gameblog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    CREATED_AT("createdAt"),
    LIKE("like");

    private final String column;

    public static SortType fromColumn(String column) {
        for (SortType type : SortType.values()) {
            if (type.column.equals(column)) {
                return type;
            }
        }
        return SortType.CREATED_AT;
    }
}
