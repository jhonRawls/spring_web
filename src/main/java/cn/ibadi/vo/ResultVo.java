package cn.ibadi.vo;

public class ResultVo {

	private int resultCode=0;
	private String resultMsg;
	private Object resultContent;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultContent() {
		return resultContent;
	}

	public void setResultContent(Object resultContent) {
		this.resultContent = resultContent;
	}
}
