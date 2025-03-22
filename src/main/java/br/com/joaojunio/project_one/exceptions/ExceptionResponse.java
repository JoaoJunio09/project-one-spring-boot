package br.com.joaojunio.project_one.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String dateils) {
}
