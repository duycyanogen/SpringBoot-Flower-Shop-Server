package Response;

import com.google.gson.annotations.Expose;


public class ResponseObject<T> {
	@Expose
	protected String requestId;
	@Expose
	protected String requestAction;
	
	@Expose
	protected boolean error = false;
	
	@Expose
	protected Integer errorType;
	
	@Expose
	protected String errorReason = "";
	
	@Expose
	protected String toastMessage = "";
	
	
	@Expose
	protected T object;
	
	public ResponseObject() {
	}
	
	public ResponseObject(T object) {
		this.object = object; 
	}
	


	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	public String getRequestAction() {
		return requestAction;
	}

	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Integer getErrorType() {
		return errorType;
	}

	public void setErrorType(Integer errorType) {
		this.errorType = errorType;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getToastMessage() {
		return toastMessage;
	}

	public void setToastMessage(String toastMessage) {
		this.toastMessage = toastMessage;
	}


	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
}
