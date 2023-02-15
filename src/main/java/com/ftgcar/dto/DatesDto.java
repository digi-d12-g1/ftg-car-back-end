package com.ftgcar.dto;

import java.time.LocalDateTime;

public record DatesDto(
        LocalDateTime dateBegin,
        LocalDateTime dateEnd) {
}
