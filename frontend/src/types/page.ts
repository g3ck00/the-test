// Forma real en la que Spring Data serializa un Page<T>
export interface PageResponse<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
}
