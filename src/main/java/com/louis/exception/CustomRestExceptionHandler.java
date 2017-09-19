package com.louis.exception;

import com.louis.errorsResponse.ErrorResponse;
import com.louis.errorsResponse.FieldErrorsResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

/**
 * Created by liuw on 16/11/8.
 * 
 * 全局异常处理
 */
@EnableWebMvc
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	// 400
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleBadRequest(
			final ConstraintViolationException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(
			final DataIntegrityViolationException ex, final WebRequest request) {
		logger.info(ex.getLocalizedMessage());
		ErrorResponse bodyOfResponse = new ErrorResponse(110,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * 自定义异常处理
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<Object> handleCustomRequest(final CustomException ex,
			final WebRequest request) {
		ErrorResponse bodyOfResponse = new ErrorResponse(120,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		ErrorResponse bodyOfResponse = new ErrorResponse(121,
				"Required parameter is missing");
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		BindingResult result = ex.getBindingResult();
		FieldErrorsResponse bodyOfResponse = new FieldErrorsResponse(122);
		for (ObjectError error : result.getAllErrors()) {
			bodyOfResponse.addError(error.getDefaultMessage());
		}
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		FieldErrorsResponse bodyOfResponse = new FieldErrorsResponse(123);
		for (ObjectError error : ex.getAllErrors()) {
			bodyOfResponse.addError(error.getDefaultMessage());
		}
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		ErrorResponse bodyOfResponse = new ErrorResponse(123,
				ex.getParameterName() + " is required");
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		System.err.println(ex);
		ErrorResponse bodyOfResponse = new ErrorResponse(124,
				ex.getRequestPartName() + " is missed");
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * 文件太大 异常处理
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ MaxUploadSizeExceededException.class })
	public ResponseEntity<Object> handleFileUploadRequest(
			final MaxUploadSizeExceededException ex, final WebRequest request) {
		ErrorResponse bodyOfResponse = new ErrorResponse(124, "上传文件不能超过20M");
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	// 403
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(
			final Exception ex, final WebRequest request) {
		System.err.println(ex.getLocalizedMessage());
		ErrorResponse response = new ErrorResponse(130, "Access denied");
		response.setPath(ex.getLocalizedMessage());
		return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
	}

	// 404
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorResponse bodyOfResponse = new ErrorResponse(140,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponse bodyOfResponse = new ErrorResponse(141,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	// 409
	@ExceptionHandler({ InvalidDataAccessApiUsageException.class,
			DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex,
			final WebRequest request) {
		logger.info(ex.getLocalizedMessage());
		ErrorResponse bodyOfResponse = new ErrorResponse(150,
				"Data Access Exception");
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
	}

	// 412
	// 500
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex,
			final WebRequest request) {
		logger.error("500 Status Code", ex);
		ErrorResponse bodyOfResponse = new ErrorResponse(1,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	// 419
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.info(this.getClass() + ex.getMessage());
		ErrorResponse bodyOfResponse = new ErrorResponse(160,
				ex.getLocalizedMessage());
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		bodyOfResponse.setPath(servletWebRequest.getRequest().getRequestURI());
		return handleExceptionInternal(ex, bodyOfResponse, headers,
				HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}
}
