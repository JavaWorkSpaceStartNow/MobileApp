package com.book.store.BookStore.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AjaxResponseBody {

	@ApiModelProperty(notes = "Message related to API")
	String message;

	@ApiModelProperty(notes = "Code related to API")
	String code;

	@ApiModelProperty(notes = "message flag")
	Object result;

	@ApiModelProperty(notes = "response data")
	Map<?, ?> responseData;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Map<?, ?> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<?, ?> responseData) {
		this.responseData = responseData;
	}

}
