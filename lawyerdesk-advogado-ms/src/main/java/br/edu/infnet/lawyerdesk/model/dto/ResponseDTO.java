package br.edu.infnet.lawyerdesk.model.dto;

public class ResponseDTO {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDTO() {

	}

	public ResponseDTO(String message) {
		this.message = message;
	}

}
