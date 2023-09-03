package ru.scarlet.company.dtos;

public record ErrorDetails(Long timestamp, String path, String message, Integer code, String correlationId) {
}
