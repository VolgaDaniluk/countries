package com.danliuk.countries.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionBody {

    private HttpStatus code;
    private String message;
}
