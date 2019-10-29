package pers.jason.example.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pers.jason.example.exception.BadRequestException;
import pers.jason.example.exception.ForbiddenException;
import pers.jason.example.exception.NotFoundException;
import pers.jason.example.exception.UnauthorizedException;
import pers.jason.example.dto.response.ErrorResponse;

/**
 * @Author 姜治昊
 * @Description 统一异常处理
 * @Date 2019/10/28 14:11
 */
@ResponseBody
@ControllerAdvice
public class WebExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(ForbiddenException.class)
  ErrorResponse handleForbidden(Exception e) {
    logger.error(e.getMessage(), e);
    return ErrorResponse.createErrorResponse(e, null);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  ErrorResponse handleNotFound(Exception e) {
    logger.error(e.getMessage(), e);
    return ErrorResponse.createErrorResponse(e, null);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  ErrorResponse handleBadRequest(Exception e) {
    logger.error(e.getMessage(), e);
    return ErrorResponse.createErrorResponse(e, null);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  ErrorResponse handleUnauthorized(Exception e) {
    logger.error(e.getMessage(), e);
    return ErrorResponse.createErrorResponse(e, null);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  ErrorResponse handleError(Exception e) {
    logger.error(e.getMessage(), e);
    return ErrorResponse.createErrorResponse(e, null);
  }

}
