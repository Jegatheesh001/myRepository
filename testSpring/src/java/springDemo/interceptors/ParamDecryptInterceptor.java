package springDemo.interceptors;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.dispatcher.Parameter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ParamDecryptInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String excludeParams;

	private final String HINT = ":";
	private final String SEPERATOR = ",";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Parameter> params = ActionContext.getContext().getParameters();
		if (excludeParams == null)
			excludeParams = "";
		String excludeParams = analyseParams();
		for (Entry<String, Parameter> entry : params.entrySet()) {
			String header = entry.getKey();
			Parameter obj = entry.getValue();
			String value = obj.getValue();
			value = value.trim();

			String decrypted = "";
			decrypted = value.replaceAll(" ", "+");
			if (!excludeParams.contains(HINT + header + HINT)) {
				try {
					decrypted = AESAlgorithm.decrypt(decrypted);
				} catch (Exception e) {
					decrypted = null;
				}

				if (decrypted != null) {
					obj = new org.apache.struts2.dispatcher.Parameter.Request(header, decrypted);
					entry.setValue(obj);
				} else {
					throw (new Exception("Unable to Parse '" + header + "'. Encrypt the value properly."));
				}
			}
		}
		return invocation.invoke();
	}

	private String analyseParams() {
		String[] array = excludeParams.split(SEPERATOR);
		String excludeParams = "";
		for (String str : array) {
			excludeParams += HINT + str + HINT + SEPERATOR;
		}
		return excludeParams;
	}

	public String getExcludeParams() {
		return excludeParams;
	}

	public void setExcludeParams(String excludeParams) {
		this.excludeParams = excludeParams;
	}

}
